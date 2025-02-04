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

public class GridPaths {
    public static int MOD = 1000000007;
    public static int f(int x, int y, String grid[], int n, int dp[][]) {
        if(x < 0 || x >= n || y < 0 || y >= n || grid[x].charAt(y) == '*') {
            return 0;
        }
        if(x == n-1 && y == n-1) {
            return 1;
        }
        if(dp[x][y] != -1) {
            return dp[x][y];
        }
        int way1 = f(x, y+1, grid, n, dp);
        int way2 = f(x+1, y, grid, n, dp);
        return dp[x][y] = (way1 + way2) % MOD;
    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        String grid[] = new String[n];
        for(int i=0; i<n; i++) {
            grid[i] = sc.next();
        }
        int dp[][] = new int[n][n];
        for(int row[]: dp) {
            Arrays.fill(row, -1);
        }
        int ways = f(0, 0, grid, n, dp);
        System.out.println(ways);
        
    }
}
