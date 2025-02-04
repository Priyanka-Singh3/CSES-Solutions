import java.io.*;
import java.util.*;



public class  DynamicRangeMinimumQueries {
    static FastIO sc = new FastIO();
    public static void build(int ind, int low, int high, long[] seg, int a[]) {
        if (low == high) {
            seg[ind] = a[low];
            return;
        }
        int mid = (low + high) >> 1;

        build(2 * ind + 1, low, mid, seg, a);
        build(2 * ind + 2, mid + 1, high, seg, a);

        seg[ind] = Math.min(seg[2 * ind + 1],  seg[2 * ind + 2]);
    }

    public static long query(int ind, int low, int high, int l, int r, long seg[]) {
        // Fully within range
        if (l <= low && high <= r) {
            return seg[ind];
        }
        // Completely outside range
        if (high < l || r < low) {
            return Long.MAX_VALUE;
        }

        // Partial overlap
        int mid = (low + high) >> 1;

        long left = query(2 * ind + 1, low, mid, l, r, seg);
        long right = query(2 * ind + 2, mid + 1, high, l, r, seg);

        return Math.min(left, right);
    }

    public static void update(int ind, int low, int high, int k, int val, long seg[]) {
        if(low == high) {
            seg[ind] = val;
            return;
        }
        int mid = (low + high) >> 1;
        if(k <= mid) {
            update(2 * ind + 1, low, mid, k, val, seg);
        }
        else {
            update(2 * ind + 2, mid + 1, high, k, val, seg);
        }
        seg[ind] = Math.min(seg[2 * ind + 1],  seg[2 * ind + 2]);
    }

    public static void main(String args[]){
        int n = sc.nextInt();
        int q = sc.nextInt();

        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        long seg[] = new long[4 * n];
        build(0, 0, n-1, seg, arr);
        while(q-- > 0) {
            int type = sc.nextInt();
            if(type == 1) {
                int k = sc.nextInt() - 1;
                int val = sc.nextInt();
                update(0, 0, n-1, k, val, seg);
            }
            else {
                int low = sc.nextInt()-1;
                int high = sc.nextInt()-1;
                long ans = query(0, 0, n-1, low, high, seg);
                System.out.println(ans);
            }
        }
        sc.flush();
    }
}
class FastIO extends PrintWriter{
    private InputStream stream;private byte[]buf=new byte[1<<16];
    private int curChar,numChars;public FastIO(){this(System.in,System.out);}
    public FastIO(InputStream i,OutputStream o){super(o);stream=i;}
    public FastIO(String i,String o)throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}
    private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return -1;}return buf[curChar++];}
    public String nextLine(){int c;do{c=nextByte();}while(c<='\n');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>'\n');return res.toString();}
    public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}
    public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res * sgn;}
    public long nextLong(){int c;do{c=nextByte();}while(c<=' ');long sgn=1;if(c=='-'){sgn=-1;c=nextByte();}long res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res * sgn;}
    public double nextDouble(){return Double.parseDouble(next());
    }
}