import java.io.*;
import java.util.*;

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

public class  RangeUpdateQueries {
    static FastIO sc = new FastIO();
    public static void build(int ind, int low, int high, long seg[], int a[]) {
        if(low == high) {
            seg[ind] = a[low];
            return;
        }
        
        int mid = (low + high) >> 1;
        build(2 * ind + 1, low, mid, seg, a);
        build(2 * ind + 2, mid + 1, high, seg, a);

        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }
    public static long lazyQuery(int ind, int low, int high, long seg[], long lazy[], int l, int r) {
        if(lazy[ind] != 0) {
            seg[ind] += lazy[ind] * (high - low + 1);
            if(low != high) {
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }
        if(r < low || high < l) return 0;
        if(l <= low && high <= r) {
            return seg[ind];
        }
        int mid = (low + high) >> 1;
        return lazyQuery(2 * ind + 1, low, mid, seg, lazy, l, r) + lazyQuery(2 * ind + 2, mid + 1, high, seg, lazy, l, r);

    }
    public static void lazyUpdate(int ind, int low, int high, long seg[], long lazy[], int l, int r, int val) {
        if(lazy[ind] != 0) {
            seg[ind] += (lazy[ind]) * (high - low + 1);
            if(low != high) {
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }
        if(r < low || high < l) return;
        if(l <= low && high <= r) {
            seg[ind] += (high - low + 1) * val;
            if(low != high) {
                lazy[2 * ind + 1] += val;
                lazy[2 * ind + 2] += val;
            }
            return;
        }
        int mid = (low + high) >> 1;
        lazyUpdate(2 * ind + 1, low, mid, seg, lazy, l, r, val);
        lazyUpdate(2 * ind + 2, mid + 1, high, seg, lazy, l, r, val);
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }
    public static void main(String args[]){
        int n = sc.nextInt();
        int q = sc.nextInt();

        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        long seg[] = new long[4 * n];
        long lazy[] = new long[4 * n];

        build(0, 0, n-1, seg, arr);
        while(q-- > 0) {
            int type = sc.nextInt();
            if(type == 1) {
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;
                int val = sc.nextInt();
                lazyUpdate(0, 0, n-1, seg, lazy, l, r, val);
            }
            else {
                int k = sc.nextInt() - 1;
                long ans = lazyQuery(0, 0, n-1, seg, lazy, k, k);
                System.out.println(ans);
            }
        }
        sc.flush();
    }
}