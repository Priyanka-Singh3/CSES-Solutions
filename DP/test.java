import java.util.*;
public class test {
    public static void main(String args[]) {
        int arr[] = {1, 10, 100, 1000, 10000};
        String s = "AAAA";
        Stack<Character> st = new Stack<>();
        char array[] = s.toCharArray();
        int n = s.length();
        char change = ' ';
        int idx = 0;
        boolean check[] = new boolean[n];
        for(int i=n-1; i>=0; i--) {
            char ch = array[i];
            while(!st.isEmpty() && array[(st.peek() - 'A')] <= array[ch-'A']) {
                st.pop();
            }
            if(st.isEmpty()) {
                check[i] = false;
            }
            else {
                check[i] = true;
                change = st.peek();
                idx = i;
            }
            st.push(ch);
        }
        if(change == ' ') {
            array[idx] = change;
            check[idx] = false;
        }
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum = sum + (check[i] == true ?  (-1) * arr[array[i]-'A'] : arr[array[i]-'A']);
        }
        System.out.println(sum);


    }
}
