class Solution(object):
    def containsNearbyDuplicate(self, nums, k):
        window_set = set()
        
        for i in range(len(nums)):
            # Remove element that is no longer in the window
            if i > k:
                window_set.remove(nums[i - k - 1])
            
            # Check for duplicate in the current window
            if nums[i] in window_set:
                return True
            
            window_set.add(nums[i])
            
        return False