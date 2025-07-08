/*
 * b) Write a JAVA Program to find shortest sub array with sum at least K
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K. If
there is no non-empty subarray with sum at least K, return -1.
Example 1:
Input: A = [1], K = 1
Output: 1
Example 2:
Input: A = [1,2], K = 4
Output: -1
Example 3:
Input: A = [2,-1,2], K = 3
Output: 3
Note:
1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
 */
import java.util.*;
public class subarrayKSum {
     public static int shortestSubarray(int[] nums, int k){
        int left=0;
        int min=Integer.MAX_VALUE;
        int temp=0;
        for(int right=0;right<nums.length;right++){
            left=right;
            temp=0;
            while(left<nums.length){
                temp+=nums[left];
                left++;
                if(temp>=k){ 
                    min=Math.min(min,left-right);
                }
            }
        }
        return min==Integer.MAX_VALUE? -1:min;
    }

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int k = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
            System.out.println(shortestSubarray(arr, k));
        }
    
}

/*
 * import java.util.*;

public class subarrayKSum {
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int minLen = n + 1;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < prefix.length; i++) {
            while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
                minLen = Math.min(minLen, i - dq.pollFirst());
            }
            while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.addLast(i);
        }
        return minLen <= n ? minLen : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.println(shortestSubarray(arr, k));
    }
}
 */