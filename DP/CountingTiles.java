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

public class CountingTiles {
    public static int mod = 1000000007;
    public static void f(int i, int mask, int next_mask, int n, int m, ArrayList<Integer> list) {
        if(i == n) {
            list.add(next_mask);
            return;
        }
        int x = mask & (1 << i);
        int y = mask & (1 << (i+1));

        if(x == 0) {
            f(i+1, mask, next_mask | (1 << i), n, m, list);
            if(y == 0 && i+1 < n) {
                f(i+2, mask, next_mask, n, m, list);
            }
        }
        else {
            f(i+1, mask, next_mask, n, m, list);
        }

    }
    public static int solve(int col, int mask, int m, int n, int[][]dp) {
        if(col == m) {
            return mask == 0 ? 1 : 0;
        }
        if(dp[mask][col] != -1) {
            return dp[mask][col];
        }
        int ans = 0;
        ArrayList<Integer> list = new ArrayList<>();
        f(0, mask, 0, n, m, list);
        for(int masks: list) {
            ans = (ans + solve(col+1, masks, m, n, dp)) % mod;
        }
        return dp[mask][col] = ans;
    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int dp[][] = new int[1<<n][m];
        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }
        int ans = solve(0, 0, m, n, dp);
        System.out.println(ans);
    }
    
}
