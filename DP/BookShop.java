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

public class BookShop {
    public static int f(int i, int price, int prices[], int pages[]) {
        if(i >= prices.length) {
            return 0;
        }
        if(price <= 0) {
            return 0;
        }
        int notPick = f(i+1, price, prices, pages);
        int pick = 0;
        if(prices[i] <= price) {
            pick = pages[i] + f(i+1, price-prices[i], prices, pages);
        }
        return Math.max(pick, notPick);
    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int x = sc.nextInt();
        int prices[] = new int[n];
        int pages[] = new int[n];
        for(int i=0; i<n; i++) {
            prices[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++) {
            pages[i] = sc.nextInt();
        }
        int prev[] = new int[x+1];
        int curr[] = new int[x+1];

        prev[0] = curr[0] = 0;

        for(int i=1; i<n+1; i++) {
            for(int j=1; j<x+1; j++) {
                int notPick = prev[j];
                int pick = 0;
                if(prices[i-1] <= j) {
                    pick = pages[i-1] + prev[j-prices[i-1]];
                }
                curr[j] = Math.max(pick, notPick);
            }
            for (int j = 0; j <= x; j++) {
                prev[j] = curr[j];
            }
        }
        System.out.println(prev[x]);

    }
}
