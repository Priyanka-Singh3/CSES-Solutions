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
public class CoinCombination1 {
    public static int f(int i, int target, int coins[], int n) {
        if(i > n || target < 0) {
            return 0;
        }
        if(target == 0) {
            return 1;
        }
        int ways = 0;
        for(int j=0; j<n; j++) {
            if(target >= coins[j]) {
                ways = ways + f(j, target-coins[j], coins, n);
            }
        }
        return ways;
    }
    public static int mod = 1000000007;
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        
        int n = sc.nextInt();
        int x = sc.nextInt();
        int coins[] = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
        }
        // long dp[] = new long[x+1];
        // dp[0] = 1;
        // for(int t=1; t<=x; t++) {
        //     for(int i=0; i<n; i++) {
        //         if(t >= coins[i]) {
        //             dp[t] = (dp[t] + dp[t-coins[i]]);
        //         }
        //     }
        //     dp[t] %= mod;
        // }
        // System.out.println(dp[x]);
        System.out.println(f(0, x, coins, n));
        sc.flush();
    }
}
