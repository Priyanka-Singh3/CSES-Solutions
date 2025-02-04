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

public class CountingTowers {
    public static int mod = 1000000007;
    // public static int f(int height, int type, int n) {
    //     if(height >= n) {
    //         return 1;
    //     }
    //     //type == 0 -> one - one 2 tiles
    //     //type == 1 -> one tile of w = 2
    //     int ways = 0;
    //     if(type == 0) {
    //         ways = ((f(height + 1, 1, n) + f(height + 1, 0, n)) % mod + (f(height + 1, 0, n) + 2 * f(height + 1, 0, n)) % mod) % mod;
    //     }
    //     else {
    //         ways = ((f(height + 1, 1, n) + f(height + 1, 0, n)) % mod + f(height + 1, 1, n)) % mod;
    //     }
    //     return ways;
    // }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int t = sc.nextInt();
        while(t > 0) {
            int n = sc.nextInt();
            // long dp[][] = new long[n+1][2];
            // dp[n][0] = dp[n][1] = 1;

            long next[] = new long[2];
            long curr[] = new long[2]; 
            next[1] = 1;
            next[0] = 1;
            
            for(int i=n-1; i>=1; i--) {
                long op1 = (next[1] + next[0]) ;
                long op2 = (next[0] + (2 * next[0]));
                
                curr[0] = (op1 + op2) % mod;
                curr[1] = (op1 + next[1]) % mod;
                
                next[0] = curr[0];
                next[1] = curr[1];
            }
            long ways = (next[0] + next[1]) % mod;
            System.out.println(ways);
            sc.flush();
            // f(1, 0, n) + f(1, 1, n)
            t--;
        }
    }
}
