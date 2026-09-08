class Solution:
    def maxProduct(self, nums):
        n = len(nums)

        pre = 1
        suff = 1
        maxProd = float('-inf')

        for i in range(n):

            if pre == 0:
                pre = 1

            if suff == 0:
                suff = 1

            pre *= nums[i]
            suff *= nums[n - 1 - i]

            maxProd = max(maxProd, pre, suff)

        return maxProd