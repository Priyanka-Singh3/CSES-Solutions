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
class Pair {

    int rides;
    int wt;
    public Pair(int rides, int w) {
        this.rides = rides;
        this.wt = w;
    }

}
public class ElevatorRides {
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int x = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        Pair dp[] = new Pair[1 << n];
        int size = 1<<n;
        
        for(int i=1; i<size; i++) {
            dp[i] = new Pair(1000000000, 1000000000);
        }
        dp[0] = new Pair(1, 0);
        for(int subset=1; subset<size; subset++) {
            for(int bit=0; bit<n; bit++) {
                if((subset & (1<<bit)) != 0) {
                    int lastWeight = dp[(1<<bit)^subset].wt;
                    int lastRides = dp[(1<<bit)^subset].rides;
                    int currWeight = arr[bit];
                    int currRides = dp[subset].rides;
                    if(lastWeight + currWeight <= x) {
                        if(lastRides < currRides) {
                            dp[subset] = new Pair(lastRides, lastWeight + currWeight);
                        }
                        else if(lastRides == currRides && (lastWeight + currWeight < dp[subset].wt)) {
                            dp[subset] = new Pair(lastRides, lastWeight + currWeight);
                        }
                        
                    }
                    else {
                        if(lastRides+1 < currRides) {
                            dp[subset] = new Pair(lastRides+1, currWeight);

                        }
                        else if(lastRides+1 == currRides && (currWeight < dp[subset].wt)) {
                            dp[subset] = new Pair(lastRides+1, currWeight);
                        }
                    }
                }
            }
            // System.out.println(dp[subset].rides + " " + dp[subset].wt);

        }
        System.out.println(dp[size-1].rides);
    }
}