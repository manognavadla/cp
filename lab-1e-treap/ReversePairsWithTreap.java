/*
 * e) Write a JAVA Program to implement TREAP with its operations
Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a
pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
Example 1:
Input: nums = [1,3,2,3,1]
Output: 2
Example 2:
Input: nums = [2,4,3,5,1]
Output: 3
Constraints: 
1 <= nums.length <= 5 * 104
-2^31 <= nums[i] <= 2^31 – 1
 */
import java.util.*;
class TreapNode {
    long key;
    int priority, count, size;
    TreapNode left, right;

    TreapNode(long key) {
        this.key = key;
        this.priority = (int)(Math.random() * 100);
        this.count = 1;
        this.size = 1;
    }
    void updateSize() {
        this.size = this.count + getSize(left) + getSize(right);
    }

    static int getSize(TreapNode node) {
        return (node == null) ? 0 : node.size;
    }
}
class Treap {
    static TreapNode rotateRight(TreapNode y) {
        TreapNode x = y.left;
        y.left = x.right;
        x.right = y;
        y.updateSize();
        x.updateSize();
        return x;
    }
    static TreapNode rotateLeft(TreapNode x) {
        TreapNode y = x.right;
        x.right = y.left;
        y.left = x;
        x.updateSize();
        y.updateSize();
        return y;
    }
    static TreapNode insert(TreapNode root, long key) {
        if (root == null) return new TreapNode(key);
        if (key == root.key) {
            root.count++;
        } else if (key < root.key) {
            root.left = insert(root.left, key);
            if (root.left.priority > root.priority)
                root = rotateRight(root);
        } else {
            root.right = insert(root.right, key);
            if (root.right.priority > root.priority)
                root = rotateLeft(root);
        }
        root.updateSize();
        return root;
    }
    static int countLessThan(TreapNode root, long val) {
        if (root == null) return 0;

        if (val <= root.key) {
            return countLessThan(root.left, val);
        } else {
            return TreapNode.getSize(root.left) + root.count + countLessThan(root.right, val);
        }
    }
}
public class ReversePairsWithTreap {
    public static int reversePairs(int[] nums) {
        TreapNode root = null; 
        int count = 0;
        // Traverse from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            count += Treap.countLessThan(root, nums[i]);
            root = Treap.insert(root, 2L * nums[i]);
        }
    

        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array length: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int result = reversePairs(nums);
        System.out.println("Reverse Pairs: " + result);
    }
}
