import java.io.*;
import java.lang.reflect.Array;
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
public class Towers {

    public static int upperBound(ArrayList<Integer> list, int x, int size) {
        int si = 0;
        int ei = size-1;
        int ans = size;
        while(si <= ei) {
            int mid = (si + ei)/2;
            if(list.get(mid) > x) {
                ans = mid;
                ei = mid - 1;
            }
            else {
                si = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int arr[] = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if(size == 0) {
                list.add(arr[i]);
                size++;
            }
            else {
                int ub = upperBound(list, arr[i], size);
                if(ub == size) {
                    list.add(arr[i]);
                    size++;
                }
                else {
                    list.set(ub, arr[i]);
                }
            }
        }
        System.out.println(size);

    }
}
