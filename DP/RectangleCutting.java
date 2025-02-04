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

public class RectangleCutting {

    public static int f(int a, int b) {
        if(a == b) {
            return 0;
        }
        int minCuts = Integer.MAX_VALUE;
        for(int i=1; i<=b-1; i++) {
            int ans = 1;
            int rect1 = f(a, b-i);
            int rect2 = f(a, i);
            if(rect1 != Integer.MAX_VALUE) {
                ans += rect1;
            }
            if(rect2 != Integer.MAX_VALUE) {
                ans += rect2;
            }
            minCuts = Math.min(minCuts, ans);
        }
        for(int i=1; i<=a-1; i++) {
            int ans = 1;
            int rect1 = f(a-i, b);
            int rect2 = f(i, b);
            if(rect1 != Integer.MAX_VALUE) {
                ans += rect1;
            }
            if(rect2 != Integer.MAX_VALUE) {
                ans += rect2;
            }
            minCuts = Math.min(minCuts, ans);
        }
        return minCuts;
    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int dp[][] = new int[a+1][b+1];
        for(int height=1; height <= a; height++) {
            for(int width=1; width <= b; width++) {
                if(height == width) {
                    dp[height][width] = 0;
                }
                else {
                    int minCuts = Integer.MAX_VALUE;
                    for(int i=1; i<width; i++) {
                        minCuts = Math.min(minCuts, 1+dp[height][width-i]+dp[height][i]);
                    }
                    for(int i=1; i<height; i++) {    
                        minCuts = Math.min(minCuts, 1 + dp[height-i][width] + dp[i][width]);
                    }
                    dp[height][width] = minCuts;
                }
            }
        }
        // int ways = f(a, b);
        System.out.println(dp[a][b]);    
    }
}