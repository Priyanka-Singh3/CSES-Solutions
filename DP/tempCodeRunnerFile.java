package DP;
import java.util.*;

public class CoinCombination1 {
    public static int mod = 1000000007;
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        
        int n = sc.nextInt();
        int x = sc.nextInt();
        int coins[] = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
        }
        long dp[] = new long[x+1];
        dp[0] = 1;
        for(int t=1; t<=x; t++) {
            for(int i=0; i<n; i++) {
                if(t >= coins[i]) {
                    dp[t] = (dp[t] + dp[t-coins[i]]);
                }
            }
            dp[t] %= mod;
        }
        System.out.println(dp[x]);
        sc.flush();
    }
}
