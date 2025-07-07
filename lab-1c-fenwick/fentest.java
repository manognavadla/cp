import java.util.*;
public class fentest {
    int[] BIT;
    int n;
    int[] nums;
    fentest(int[] nums){
        this.nums=nums;
        this.n= nums.length;
        BIT= new int[n+1];
        for(int i=0;i<n;i++) init(i,nums[i]);
    }
    void init(int i, int val){
        i++;
        while(i<n){
            BIT[i]+=val;
            i+=(i&-i);
        }
    }
    void update(int i, int val){
        int d=val-nums[i];
        nums[i]=val;
        init(i, val);
    }
    int sumrange(int i, int j){
        return sum(j)-sum(i-1);
    }
    int sum(int i){
        int sum=0;
        i++;
        while(i>0){
            sum+=BIT[i];
            i-=(i&-i);
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n= sc.nextInt();
        int[] nums= new int[n];
        for(int i=0;i<n;i++) nums[i]=sc.nextInt();
        fentest ft= new fentest(nums);
        System.out.println(ft.sumrange(sc.nextInt(), sc.nextInt()));
        
    }
}
