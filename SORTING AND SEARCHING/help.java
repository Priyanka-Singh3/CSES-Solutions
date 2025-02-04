import java.util.*;
public class help {
    public static void main(String args[]) {
        char txt[] = {'B', 'A', 'C', 'D', 'G', 'A', 'B', 'C', 'D', 'A'};
        char pat[] = {'A', 'B', 'C', 'D'};
        int freq[] = new int[26];
        int n = 10;
        int m = 4;
        for(int i=0; i<m; i++) {
            freq[pat[i]-'A']++;
        }
        int left = 0;
        int right = 0;
        int count = m;
        int ans = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(right < n) {
            boolean check = false;
            char ch = txt[right];
            if(count == 0) {
                list.add(left);
                ans++;
                freq[txt[left]-'A']++;
                count++;
                left++;
                continue;
            }
            while(left < right && right < n && count < 0) {
                freq[txt[left]-'A']++;
                count++;
                left++;
            }
            if(freq[ch-'A'] > 0) {
                freq[ch-'A']--;
                count--;
            }
            else {
                right++;
                left = right;
                count = m;
                check = true;
            }
            if(check == false) {
                right++;
            }

        }
        System.out.println(list);

    }
}
