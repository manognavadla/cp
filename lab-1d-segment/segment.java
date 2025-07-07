import java.util.Scanner;
class node{
    int start;
    int end;
    node left;
    node right;
    int sum;
    node(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = null;
        this.right = null;
    }
}
public class segment {
    node root;
    static node buildSegmentTree(int i, int j){
        node newNode = new node(i, j);
        if (i == j) {
            newNode.sum = 0; // Initialize sum to 0 for leaf nodes
            return newNode;
        }
        int mid = i + (j - i) / 2;
        newNode.left = buildSegmentTree(i, mid);
        newNode.right = buildSegmentTree(mid + 1, j);
        newNode.sum = newNode.left.sum + newNode.right.sum; // Initialize sum for internal nodes
        return newNode;
    }
    static boolean updateSegmentTree(node root, int index) {
        if (root.start == root.end) {
            if (root.sum == 1) return false; // Already booked
            root.sum = 1; // Mark as booked
            return true;
        }
        int mid = root.start + (root.end - root.start) / 2;
        boolean booked = false;
        if (index <= mid) {
            booked = updateSegmentTree(root.left, index);
        } else {
            booked = updateSegmentTree(root.right, index);
        }
        if (booked) {
            root.sum = root.left.sum + root.right.sum; // Update sum after booking
        }
        return booked;
    }
    static int findAvailableSegment(node root, int start, int end) {
        if (root == null || root.sum == (root.end - root.start + 1) || end < root.start || start > root.end)
            return -1; // No available segment
        if (root.start == root.end && root.sum == 0) return root.start; // Found available segment
        int leftRes = findAvailableSegment(root.left, start, end);
        if (leftRes != -1) return leftRes; // Found in left subtree
        return findAvailableSegment(root.right, start, end); // Search in right subtree
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline
        String[] input = sc.nextLine().split(",");
        int[][] days= new int[n][2];
        int maxDay=0;
        for(int i=0;i<n;i++){
            String[] parts = input[i].trim().split(" ");
            days[i][0] = Integer.parseInt(parts[0]);
            days[i][1] = Integer.parseInt(parts[1]);
            maxDay = Math.max(maxDay, days[i][1]);
        }
        segment seg = new segment();
        seg.root = buildSegmentTree(1, maxDay);
        for (int i = 0; i < n; i++) {
            int availableDay = findAvailableSegment(seg.root, days[i][0], days[i][1]);
            if (availableDay != -1) {
                updateSegmentTree(seg.root, availableDay);
            }
        }
    }
}
