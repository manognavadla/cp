/*2) Develop a java program to find the Lexicographically smallest equivalent string.
Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters. For
example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'. Equivalent characters
follow the usual rules of any equivalence relation:
▪ Reflexivity: 'a' == 'a'
▪ Symmetry: 'a' == 'b' implies 'b' == 'a'
▪ Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are
equivalent strings, and "aab" is the lexicographically smallest equivalent string of S. Return the
lexicographically smallest equivalent string of S by using the equivalency information from A and B.
Example 1:
Input: A = "parker", B = "morris", S = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in A and B, we can group their characters as [m,p],
[a,o], [k,r,s], [e,i]. The characters in each group are equivalent and sorted in lexicographical order. So the
answer is "makkek". */
import java.util.*;
class Solution{
    private int find(int[] graph, int idx){
    while (graph[idx] != idx){
        idx = find(graph, graph[idx]);
    }
        return idx;
    }
    public String smallestEquivalentString(String A, String B, String S){
        int[] graph = new int[26];
        for (int i = 0; i < 26; i++){
            graph[i] = i;
        }
        for (int i = 0; i < A.length(); i++){
            int a = A.charAt(i) - 'a';
            int b = B.charAt(i) - 'a';
            int end1 = find(graph, b);
            int end2 = find(graph, a);
            if (end1 < end2){
                graph[end2] = end1;
            }
            else{
                graph[end1] = end2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++)
        {
        char c = S.charAt(i);
        sb.append((char)('a' + find(graph, c - 'a')));

        }
        return sb.toString();
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String A = sc.next(); String B = sc.next(); String substr = sc.next();
        System.out.println(new Solution().smallestEquivalentString(A,B,substr));
    }
}
