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

public class  Salary_Queries {
    static FastIO sc = new FastIO();
    static class Pair {
        int min;
        int max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    static class SGTree {
        private static Pair seg[];

        public SGTree(int n) {
            seg = new Pair[4 * n + 1];
        }

        public void build(int ind, int low, int high, int a[]) {

            if(low == high) {
                seg[ind] = new Pair(a[low], a[low]);
                return;
            }

            int mid = (low + high)/2;

            build(2*ind+1, low, mid, a);
            build(2*ind+2, mid+1, high, a);

            int mini = Math.min(seg[2 * ind + 1].min, seg[2 * ind + 2].min);
            int maxi = Math.max(seg[2 * ind + 1].max, seg[2 * ind + 2].max);

            seg[ind] = new Pair(mini, maxi);
        }

        public static void update(int ind, int low, int high, int i, int val) {

            if(low == high) {
                seg[ind].min = val;
                seg[ind].max = val;
                return;
            }

            int mid = (low + high)/2;
            if(i <= mid) {
                update(2 * ind + 1, low, mid, i, val);
            }
            else {
                update(2 * ind + 2, mid + 1, high, i, val);
            }

            int mini = Math.min(seg[2 * ind + 1].min, seg[2 * ind + 2].min);
            int maxi = Math.max(seg[2 * ind + 1].max, seg[2 * ind + 2].max);

            seg[ind] = new Pair(mini, maxi);
        }
        public static int count(int ind, int low, int high, int a, int b) {

            if(seg[ind].min >= a && seg[ind].max <= b) {
                return high - low + 1;
            }
            
            if(seg[ind].max < a || b < seg[ind].min) {
                return 0;
            }

            int mid = (low + high)/2;
            int left = count(2 * ind + 1, low, mid, a, b);
            int right = count(2 * ind + 2, mid + 1, high, a, b);

            return left + right;
        }

        
    }
    public static void main(String args[]){
        int n = sc.nextInt();
        int q = sc.nextInt();

        int sal[] = new int[n];
        for (int i = 0; i < n; i++) {
            sal[i] = sc.nextInt();
        }
        SGTree st = new SGTree(n);
        st.build(0, 0, n-1, sal);
        for (int i = 0; i < q; i++) {
            char c = sc.next().charAt(0);
            if(c == '!') {
                int idx = sc.nextInt() - 1;
                int x = sc.nextInt();
                st.update(0, 0, n-1, idx, x);
                sal[idx] = x; 
            }
            else {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int ans = st.count(0, 0, n-1, a, b);
                System.out.println(ans);
            }
        }

        sc.flush();
    }
}