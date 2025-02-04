// package DP;
import java.io.*;
import java.util.*;
class FastIO extends PrintWriter {
    private InputStream stream;
    private byte[] buf = new byte[1 << 16];
    private int curChar, numChars;

    public FastIO() {
        this(System.in, System.out);
    }

    public FastIO(InputStream i, OutputStream o) {
        super(o);
        stream = i;
    }

    public FastIO(String i, String o) throws IOException {
        super(new FileWriter(o));
        stream = new FileInputStream(i);
    }

    private int nextByte() {
        if (numChars == -1) throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars == -1) return -1;
        }
        return buf[curChar++];
    }

    public String nextLine() {
        int c;
        do {
            c = nextByte();
        } while (c <= '\n');
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = nextByte();
        } while (c > '\n');
        return res.toString();
    }

    public String next() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = nextByte();
        } while (c > ' ');
        return res.toString();
    }

    public int nextInt() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextByte();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            res = 10 * res + c - '0';
            c = nextByte();
        } while (c > ' ');
        return res * sgn;
    }

    public long nextLong() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        long sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextByte();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            res = 10 * res + c - '0';
            c = nextByte();
        } while (c > ' ');
        return res * sgn;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}
public class CoinCombination2 {

    public static int mod = 1000000007;

    public static int f(int i, int t, int coins[], int n, int dp[][]) {
        if(i < 0 || t < 0) {
            return 0;
        }
        if(t == 0) {
            return 1;
        }
        if(dp[i][t] != -1) {
            return dp[i][t];
        }
        int pick = 0, notPick = 0;
        if(coins[i] <= t) {
            int take = f(i, t-coins[i], coins, n, dp);
            int notTake = f(i-1, t, coins, n, dp);
            pick = take + notTake;
            
        }
        else {
            notPick = f(i-1, t, coins, n, dp);
        }
        return dp[i][t] =  pick + notPick;
    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int x = sc.nextInt();
        int coins[] = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
        }
        // int dp[][] = new int[n+1][x+1];
        int prev[] = new int[x+1];
        int curr[] = new int[x+1];

        //base case
        
        prev[0] = curr[0] = 1;
        
        for(int i=1; i<=n; i++) {
            for(int t=1; t<=x; t++) {
                int pick = 0, notPick = 0;
                if(coins[i-1] <= t) {
                    int take = curr[t-coins[i-1]];
                    int notTake = prev[t];
                    pick = take + notTake;
                }
                else {
                    notPick = prev[t];
                }
                curr[t] =  (pick + notPick)%mod;
            }
            prev = curr;
        }
        System.out.println(prev[x]);
        // for(int row[]: dp) {
        //     Arrays.fill(row, -1);
        // }
        // int ans = f(n-1, x, coins, n, dp);
        // System.out.println(ans);
        // long dp[] = new long[x+1];
        // dp[0] = 1;
        // for(int i=0; i<n; i++) {
        //     for(int t = coins[i]; t <= x; t++) {
        //         dp[t] = (dp[t] + dp[t-coins[i]]) % mod;
        //     }
        // }
        // System.out.println(dp[x]);
        sc.flush();

    }
}
