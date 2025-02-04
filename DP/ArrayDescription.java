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



public class ArrayDescription {
    public static int mod = 1000000007;

    public static int f(int i, int x, int arr[], int n, int m) {
        if(x < 1 || x > m) {
            return 0;
        }
        if(i == 0) {
            if(arr[i] == 0 || arr[i] == x) {
                return 1;
            }
            else {
                return 0;
            }
        }
        int total = 0;
        if(arr[i] == 0 || arr[i] == x) {
            total = (total + f(i-1, x+1, arr, n, m)) % mod;
            total = (total + f(i-1, x-1, arr, n, m)) % mod;
            total = (total + f(i-1, x, arr, n, m)) % mod;
        }
        return total;
    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        int totalArrays = 0;
        int dp[][] = new int[n+1][m+2];
        for(int x=1; x<=m; x++) {
            if(arr[0] == 0 || arr[0] == x) {
                dp[1][x] = 1;
            }
        }
        for(int i=1; i<=n; i++) {
            for(int x=1; x<=m; x++) {
                if(arr[i-1] == 0 || arr[i-1] == x) {
                    dp[i][x] = ((dp[i][x] +  dp[i-1][x-1]) % mod + (dp[i-1][x+1] + dp[i-1][x]) % mod) % mod;
                }
                else {
                    dp[i][x] = 0;
                }
            }
        }
        for(int i=1; i<=m; i++) {
            totalArrays = (totalArrays + dp[n][i]) % mod;
        }

        System.out.println(totalArrays);
    }
}
