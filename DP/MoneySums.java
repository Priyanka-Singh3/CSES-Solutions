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
public class MoneySums {
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += arr[i];
        }
        int dp[][] = new int[n+1][sum+1];
        for(int i=0; i<=n; i++) {
            dp[i][0] = 1;
        }
        int mod = 1000000007;
        for(int i=1; i<=n; i++) {
            for(int target = 1; target <= sum; target++) {
                int notPick = dp[i-1][target];
                int pick = 0;
                if(target >= arr[i-1]) {
                    pick = dp[i-1][target-arr[i-1]];
                }
                dp[i][target] = (pick + notPick) % mod;
            }
        }
        // for(int i=0; i<=n; i++) {
        //     for(int j=0; j<=sum; j++) {
        //         System.out.print(dp[i][j] + " ");
                
        //     }
        //     System.out.println();
        // }
        for(int i=1; i<=sum; i++) {
            if(dp[n][i] > 0) {
                list.add(i);
            }
        }
        System.out.println(list.size());
        for(int i: list) {
            System.out.print(i + " ");
        }
    }
}
