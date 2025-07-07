/*
 * d) Write a JAVA Program to implement a segment tree with its operations In Hyderabad
after a long pandemic gap, the Telangana Youth festival Is Organized at HITEX.
In HITEX, there are a lot of programs planned. During the festival in order to maintain the rules
of Pandemic, they put a constraint that one person can only attend any one of the programs in one
day according to planned days. Now it’s your aim to implement the "Solution" class in such a way
that you need to return the maximum number of programs you can attend according to given
constraints.
Explanation: You have a list of programs ‘p’ and days ’d’, where you can attend only one program
on one day. Programs [p] = [first day, last day], p is the program's first day and the last day.
Input Format:
Line-1: An integer N, number of programs.
Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by
space. Output Format:
An integer, the maximum number of programs you can attend.
Sample Input-1:
4
1 2,2 4,2 3,2 2
Sample Output-1:
4
Sample Input-2:
6
1 5,2 3,2 4,2 2,3 4,3 5
Sample Output-2:
5
 */
import java.util.*;
class SegmentTree {
    int start, end, sum;
    SegmentTree left, right;
    SegmentTree(int start, int end) {
        this.start = start;
        this.end = end;
        this.sum = 0;
    }
    static SegmentTree build(int start, int end) {
        SegmentTree node = new SegmentTree(start, end);
        if (start == end) {
            node.sum = 0;
        } else {
            int mid = start + (end - start) / 2;
            node.left = build(start, mid);
            node.right = build(mid + 1, end);
            node.sum = node.left.sum + node.right.sum;
        }
        return node;
    }
    static boolean update(SegmentTree root, int index) {
        if (root.start == root.end) {
            if (root.sum == 1) return false; // already booked
            root.sum = 1; 
            return true;
        } 
        boolean booked = false;
        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) booked = update(root.left, index);
        else booked = update(root.right, index);

        if (booked) root.sum = root.left.sum + root.right.sum;
        return booked;
    }
    static int findAvailable(SegmentTree root, int start, int end) {
        if (root == null || root.sum == (root.end - root.start + 1) || end < root.start || start > root.end)
            return -1;
        if (root.start == root.end && root.sum == 0) return root.start;
        int leftRes = findAvailable(root.left, start, end);
        if (leftRes != -1) return leftRes;
        return findAvailable(root.right, start, end);
    }
}

public class SegmentSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        String[] input = sc.nextLine().split(",");
        int[][] programs = new int[n][2];
        int maxDay = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = input[i].trim().split(" ");
            programs[i][0] = Integer.parseInt(parts[0]);
            programs[i][1] = Integer.parseInt(parts[1]);
            maxDay = Math.max(maxDay, programs[i][1]);
        }
        Arrays.sort(programs, (a, b) -> a[1] - b[1]);
        SegmentTree root = SegmentTree.build(1, maxDay);
        int count = 0;
        for (int[] prog : programs) {
            int availableDay = SegmentTree.findAvailable(root, prog[0], prog[1]);
            if (availableDay != -1) {
                SegmentTree.update(root, availableDay);
                count++;
            }
        }
        System.out.println(count);
    }
}
