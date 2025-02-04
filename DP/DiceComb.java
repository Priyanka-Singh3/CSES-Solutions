import java.util.*;
public class DiceComb {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long dp[] = new long[n+1];
        dp[1] = 1; 
        dp[0] = 1;
        int mod = (int)Math.pow(10, 9) + 7;
        // long ways = f(n, dp) % mod;
        for(int t=2; t<=n; t++) {
            if(t-1 >= 0) {
                dp[t] = (dp[t]%mod + dp[t-1]%mod)%mod;
            }
            if(t-2 >= 0) {
                dp[t] = (dp[t]%mod + dp[t-2]%mod)%mod;
            }
            if(t-3 >= 0) {
                dp[t] = (dp[t]%mod + dp[t-3]%mod)%mod;
            }
            if(t-4 >= 0) {
                dp[t] = (dp[t]%mod + dp[t-4]%mod)%mod;
            }
            if(t-5 >= 0) {
                dp[t] = (dp[t]%mod + dp[t-5]%mod)%mod;
            }
            if(t-6 >= 0) {
                dp[t] = (dp[t]%mod + dp[t-6]%mod)%mod;
            }
        }

        System.out.println(dp[n] % mod);
    }

    public static long f(int target, long dp[]) {
        int mod = (int)Math.pow(10, 9) + 7;
        if(target == 0) {
            return 1;
        }
        if(target < 0) {
            return 0;
        }
        if(dp[target] != -1) {
            return dp[target] % mod;
        }
        long way1 = f(target-1, dp) % mod;
        long way2 = f(target-2, dp) % mod;
        long way3 = f(target-3, dp) % mod;
        long way4 = f(target-4, dp) % mod;
        long way5 = f(target-5, dp) % mod;
        long way6 = f(target-6, dp) % mod;
        return dp[target] = ((way1)  + (way2)  + (way3) + (way4) + (way5) + (way6)) % mod;
    }
}