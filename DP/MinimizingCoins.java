// package DP;
import java.util.*;
public class MinimizingCoins {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int coins[] = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
        }
        // int dp[][] = new int[n+1][x+1];
        // for(int[] row : dp) {
        //     Arrays.fill(row, Integer.MAX_VALUE);
        // }
        // for(int i=0; i<=n; i++) {
        //     dp[i][0] = 0;
        // }
        int prev[] = new int[x+1];
        int curr[] = new int[x+1];
        Arrays.fill(prev, Integer.MAX_VALUE);
        // Arrays.fill(curr, -1);
        prev[0] = 0;
        curr[0] = 0;
        for(int i=1; i<=n; i++) {
            for(int t=1; t<=x; t++) {
                if(coins[i-1] <= t) {
                    int res = curr[t-coins[i-1]];
                    if(res != Integer.MAX_VALUE) {
                        curr[t] = Math.min(prev[t], 1+res);
                    }
                    else {
                        curr[t] = prev[t];
                    }
                }
                else {
                    curr[t] = prev[t];
                }
            }
            prev = curr;
        }
        System.out.println(prev[x] == Integer.MAX_VALUE ? -1 : prev[x]);
    }
    // public static int f(int i, int target, int coins[], int n, int dp[][]) {
    //     if(i < 0 || target < 0) {
    //         return Integer.MAX_VALUE;
    //     }
    //     if(target == 0) {
    //         return 0;
    //     }
    //     if(dp[i][target] != -1) {
    //         return dp[i][target];
    //     }
    //     int notPick = f(i-1, target, coins, n, dp);
    //     int pick = Integer.MAX_VALUE;
    //     if(coins[i] <= target) {
    //         int res = f(i, target - coins[i], coins, n, dp);
    //         if (res != Integer.MAX_VALUE) {
    //             pick = 1 + res;
    //         }
    //     }
    //     return dp[i][target] = Math.min(pick, notPick);
    // }

}
