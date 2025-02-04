import java.io.*;
import java.util.*;
import java.math.BigInteger;
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
public class CountingNumbers {

    public static BigInteger solve(String number, int n, int x, int leadingZeroes, int tight, BigInteger dp[][][][]) {
        if(n == 0) {
            return BigInteger.ONE;
        }
        if(x != -1 && dp[n][x][leadingZeroes][tight].compareTo(BigInteger.valueOf(-1)) != 0) {
            return dp[n][x][leadingZeroes][tight];
        }

        int lowerBound = 0;
        int upperBound = tight == 1 ? number.charAt((number.length()-n))-'0' : 9;
        BigInteger ans = BigInteger.ZERO;
        for(int dig = lowerBound; dig <= upperBound; dig++) {
            if(dig == x && leadingZeroes == 0) {
                continue;
            }
            int nextLeadingZeroes = (dig == 0 && leadingZeroes == 1) ? 1 : 0;
            int nextTight = (tight == 1 && dig == upperBound) ? 1 : 0;
            ans = ans.add(solve(number, n-1, dig, nextLeadingZeroes, nextTight, dp));
        }
        if(x != -1) {
            dp[n][x][leadingZeroes][tight] = ans;
        }
        return ans;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        BigInteger a = sc.nextBigInteger();
        BigInteger b = sc.nextBigInteger();
        String A = a.subtract(BigInteger.ONE).toString();
        String B = b.toString();
        int lenA = A.length();
        int lenB = B.length();
        BigInteger dp1[][][][] = new BigInteger[20][10][2][2];
        BigInteger dp2[][][][] = new BigInteger[20][10][2][2];
        for(BigInteger[][][] r1: dp1) {
            for(BigInteger[][] r2: r1) {
                for(BigInteger[] r3: r2) {
                    Arrays.fill(r3, BigInteger.valueOf(-1));
                }
            }
        }
        for(BigInteger[][][] r1: dp2) {
            for(BigInteger[][] r2: r1) {
                for(BigInteger[] r3: r2) {
                    Arrays.fill(r3, BigInteger.valueOf(-1));
                }
            }
        }
        BigInteger ans = solve(B, lenB, -1, 1, 1, dp1).subtract(solve(A, lenA, -1, 1, 1, dp2));
        System.out.println(ans);
    }


}
