/*
i) Write a JAVA Program to return all index pairs [i,j] given a text string and words (a
list), so that the substring text[i]…text[j] is in the list of words
Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring
text[i]...text[j] is in the list of words.
Note:
• All strings contains only lowercase English letters.
• It's guaranteed that all strings in words are different.
• Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them
by their second coordinate).
Example 1:
Input: text = "thestoryofleetcodeandme", words =
["story","fleet","leetcode"] Output: [[3,7],[9,13],[10,17]]
Example 2:
Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation:
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].

 */
import java.util.*;
class Trie{
    Trie[] children=new Trie[26];
    boolean isEnd=false;
    void insert(String word){ 
        Trie node =this; 
        for(char c:word.toCharArray()){
            c-='a';
            if(node.children[c]==null){
                node.children[c]=new Trie();
            }
            node=node.children[c];
        }
        node.isEnd=true;
    }
}
class IndexPairs{
    public static int[][] indexPairs(String text,String[] words){
        Trie trie=new Trie();
        for(String w:words){
            trie.insert(w);
        }
        int n=text.length();
        List<int[]> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            Trie node=trie;
            for(int j=i;j<n;j++){
                int idx=text.charAt(j)-'a';
                if(node.children[idx]==null){
                    break;
                }
                node=node.children[idx];
                if(node.isEnd){
                    ans.add(new int[]{i,j});
                }
            }
        }
        // System.out.println(ans);
        return ans.toArray(new int[ans.size()][2]);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String text=sc.nextLine();
        String[] words=sc.nextLine().trim().split(" ");
        int[][] ans=indexPairs(text,words);
        for(int i=0;i<ans.length;i++){
            for(int j=0;j<ans[0].length;j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
 }
}
}
