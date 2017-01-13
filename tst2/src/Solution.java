public class Solution {
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;
        if(nums.length == 1)    return nums[0];
        if(nums.length == 2){
        	int ans = 0;
            if(nums[0] > nums[1])
                ans = nums[0] * nums[1] + nums[0];
            else
                ans = nums[0] * nums[1] + nums[1]; 
            System.out.println(nums[0]);
            System.out.println(nums[1]);
            System.out.println(ans);
            return ans;
        }else{
            // find the least;
            int least = 1;
            for(int i = 2; i < nums.length-1; i++){
                if(nums[i] < nums[least])
                    least = i;
            }
            // construct new nums
            int[] n = new int[nums.length - 1];
            int count = 0;
            for(int i = 0; i < nums.length; i++){
                if(i == least) 
                    continue;
                n[count] = nums[i];
                count++;
            }
            //System.out.println(least);
            int ans = maxCoins(n) + nums[least-1]*nums[least]*nums[least+1];
            System.out.println(ans);
            return ans;
        }
    }
    public static void main(String[] args){
    	Solution s = new Solution();
    	int[] n = {3,1,5,8};
    	System.out.println(s.maxCoins(n));
    }
}