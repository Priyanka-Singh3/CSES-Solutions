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
public class TwoSets {
    public static int mod = 1000000007;
    public static long f(int t, int n, long dp[][]) {
        if(t == 0) {
            return 1;
        }
        if(n <= 0 || t < 0) {
            return 0;
        }
        if(dp[t][n] != -1) {
            return dp[t][n];
        }
        long notPick = f(t, n-1, dp);
        long pick = 0;
        if(t >= n) {
            pick = f(t-n, n-1, dp);
        }
        return dp[t][n] = (pick + notPick) % mod;
    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int sum = (n * (n+1))/2;
        
        if(sum % 2 != 0) {
            System.out.println(0);
        }
        else {
            int target = sum/2;
            // long dp[][] = new long[target+1][n+1];
            // for(long row[]: dp) {
            //     Arrays.fill(row, -1);
            // }
            // long ans = f(target, n, dp);
            // long inverseOfTwo = (mod + 1) / 2;
            // ans = (ans * inverseOfTwo) % mod;
            // // ans = (ans * ((mod + 1) / 2)) % mod;
            // System.out.println(ans);
            long dp[][] = new long[n+1][target+1];

            //base case
            //for target = 0 , there's one way that is do not take any choice
            for(int i=0; i<=n; i++) {
                dp[i][0] = 1;
            }
            for(int i=1; i<=n; i++) {
                for(int t=1; t<=target; t++) {
                    long notPick = dp[i-1][t];
                    long pick = 0;
                    if(t >= i) {
                        pick = dp[i-1][t-i];
                    }
                    dp[i][t] = (pick + notPick) % mod;
                }
            }
            long ans = dp[n][target];
            long inverseOfTwo = (mod + 1) / 2;
            ans = (ans * inverseOfTwo) % mod;
            System.out.println(ans);
        }

        
    }
}