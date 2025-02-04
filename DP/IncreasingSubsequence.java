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
public class IncreasingSubsequence {

    public static int lowerBound(ArrayList<Integer> list, int curr, int size) {
        int start = 0;
        int end = size-1;
        int ans = size;
        while(start <= end) {
            int mid  = (start + end)/2;
            if(list.get(mid) >= curr) {
                ans = mid;
                end = mid-1;
            }
            else {
                start = mid+1;
            }
        }
        return ans;
    }

// 7 3 5 3 6 2 9 8

    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        int size = 1;
        for(int i=1; i<n; i++) {
            if(list.get(size-1) < arr[i]) {
                list.add(arr[i]);
                size++;
            }
            else {
                int lbIdx = lowerBound(list, arr[i], size);
                list.set(lbIdx, arr[i]);
            }
        }
        System.out.println(size);
        
        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(4);
        // list.add(5);
        // list.add(6);
        // list.add(7);
        // list.add(10);
        // System.out.println(lowerBound(list, 1, 5));
        

        
        // int dp[] = new int[n];
        // Arrays.fill(dp, 1);
        // int ans = 1;
        // for(int i=0; i<n; i++) {
        //     for(int prev=0; prev<i; prev++) {
        //         if(arr[prev] < arr[i]) {
        //             dp[i] = Math.max(dp[i], 1 + dp[prev]);
        //         }
        //         ans = Math.max(ans, dp[i]);
        //     }
        // }
        // System.out.println(ans);
    }
}