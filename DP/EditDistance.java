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

public class EditDistance {
    public static int f(int i, int j, String s1, String s2) {
        
        if( i < 0) {
            return j+1;
        }
        if(j < 0) {
            return i+1;
        }

        if(s1.charAt(i) == s2.charAt(j)) {
            return f(i-1, j-1, s1, s2);
        }
        //add
        int way1 = 1 + f(i, j-1, s1, s2);
        
        //replace
        int way2 = 1 + f(i-1, j-1, s1, s2);

        //remove
        int way3 = 1 + f(i-1, j, s1, s2);

        return Math.min(Math.min(way1, way2), way3);

    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        
        
            String s1 = sc.next();
            String s2 = sc.next();

            int n = s1.length();
            int m = s2.length();

            int dp[][] = new int[n+1][m+1];
            for(int i=0; i<=n; i++) {
                dp[i][0] = i;
            }
            for(int j=0; j<=m; j++) {
                dp[0][j] = j;
            }

            for(int i=1; i<=n; i++) {
                for(int j=1; j<=m; j++) {
                    if(s1.charAt(i-1) == s2.charAt(j-1)) {
                        dp[i][j] =  dp[i-1][j-1];
                    }
                    else {
                        //add
                        int way1 = 1 + dp[i][j-1];
                    
                        //replace
                        int way2 = 1 + dp[i-1][j-1];
                
                        //remove
                        int way3 = 1 + dp[i-1][j];
                        dp[i][j] =  Math.min(Math.min(way1, way2), way3);
                    }
                    
                }
            }
            int dist = dp[n][m];
            System.out.println(dist);

    }
}
