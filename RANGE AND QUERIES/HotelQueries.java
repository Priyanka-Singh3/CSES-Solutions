import java.io.*;
import java.util.*;


public class  HotelQueries {
    static FastIO sc = new FastIO();
    static class SGTree {
        private static int seg[];
    
        public SGTree(int n) {
            seg = new int[4 * n + 1];
        }
    
        public static void build(int ind, int low, int high, int a[]) {
    
            if(low == high) {
                seg[ind] = a[low];
                return;
            }
    
            int mid = (low + high)/2;
    
            build(2*ind+1, low, mid, a);
            build(2*ind+2, mid+1, high, a);
            seg[ind] = Math.max(seg[2 * ind + 1], seg[2 * ind + 2]);
        }
    
        public static int query(int ind, int low, int high, int l, int r) {
    
            //complete overlap -> [l  low   high   r]
            if(l <= low && high <= r) {
                return seg[ind];
            }
    
            //no overlap -> [low  high] [l  r] || [l  r] [low  high]
            if(high < l || r < low) {
                return Integer.MIN_VALUE;
            }
            
            //partial overlap
            int mid = (low + high)/2;
            int left = query(2 * ind + 1, low, mid, l, r);
            int right = query(2 * ind + 2, mid + 1, high, l, r);
    
            return Math.max(left, right);
    
        }
    
        public static int find(int ind, int low, int high, int x) {
            if(seg[ind] < x) return -1;
            if(low == high) {
                return low;
            }
            int mid = (low + high)/2;
            int left = find(2*ind+1, low, mid, x);
            if(left != -1) return left;
            return find(2*ind+2, mid+1, high, x);
        }
    
        public static void update(int ind, int low, int high, int i, int val) {
    
            if(low == high) {
                seg[ind] = val;
                return;
            }
    
            int mid = (low + high)/2;
            if(i <= mid) {
                update(2 * ind + 1, low, mid, i, val);
            }
            else {
                update(2 * ind + 2, mid + 1, high, i, val);
            }
            seg[ind] = Math.max(seg[2 * ind + 1], seg[2 * ind + 2]);
        }
    
    }

    public static void main(String args[]){
        int n = sc.nextInt();
        int m = sc.nextInt();

        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        SGTree st = new SGTree(n);
        st.build(0, 0, n-1, a);
        for (int i = 0; i < m; i++) {
            int grp = sc.nextInt();
            int ans = st.find(0, 0, n-1, grp);
            if(ans != -1) {
                st.update(0, 0, n-1, ans, a[ans]-grp);
                a[ans] -= grp;
                System.out.print(ans+1 + " ");
            }
            else {
                System.out.print(0 + " ");
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
