public ListNode sortList(ListNode head) {
  ListNode dummy = new ListNode(0);
  dummy.next = head;

  ListNode [] sublists = new ListNode[2];
  ListNode [] sublists_tail = new ListNode[2];

  // Grab sublists of size 1, then 2, then 4, etc, until fully merged
  for (int steps = 1;; steps *= 2) {
    // Record the progress of the current pass into a single semi sorted list by updating
    // the next of the previous node (or the dummy on the first loop)
    ListNode prev = dummy;

    // Keep track of how much is left to process on this pass of the list
    ListNode remaining = prev.next;

    int num_loops = 0;
    for (; null != remaining; ++num_loops) {
      // Split 2 sublists of steps length from the front
      for (int i = 0; i < 2; ++i) {
        sublists[i] = remaining;
        sublists_tail[i] = null;
        for (int j = 0; null != remaining && j < steps; ++j) {
          sublists_tail[i] = remaining;
          remaining = remaining.next;
        }
        // Ensure the subslist (if one was made) is terminated
        if (null != sublists_tail[i]) {
          sublists_tail[i].next = null;
        }
      }

      // We have two sublists of (upto) length step that are sorted, merge them onto the end into a single list of (upto) step * 2
      while (null != sublists[0] && null != sublists[1]) {
        if (sublists[0].val <= sublists[1].val) {
          prev.next = sublists[0];
          sublists[0] = sublists[0].next;
        } else {
          prev.next = sublists[1];
          sublists[1] = sublists[1].next;
        }
        prev = prev.next;
      }   

      // One list has been finished, attach what ever is left of the other to the end
      if (null != sublists[0]) {
        prev.next = sublists[0];
        prev = sublists_tail[0];
      } else {
        prev.next = sublists[1];
        prev = sublists_tail[1];
      }
    }

    // If the entire list was fully processed in a single loop, it means we've completely sorted the list and are done
    if (1 >= num_loops) {
      return dummy.next;
    }
  }
}