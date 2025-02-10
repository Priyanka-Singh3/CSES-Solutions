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

public class  ListRemovals {
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
            int mid = (low + high) >> 1;
            build(2 * ind + 1, low, mid, a);
            build(2 * ind + 2, mid + 1, high, a);

            seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
        }

        public static int findAndUpdate(int ind, int low, int high, int x) {
            if(low == high) {
                seg[ind] = 0;
                return low;
            }
            int mid = (low + high) >> 1;
            if(seg[2 * ind + 1] < x) {
                int idx = findAndUpdate(2 * ind + 2, mid + 1, high, x - seg[2 * ind + 1]);
                seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
                return idx;
            }
            else {
                int idx = findAndUpdate(2 * ind + 1, low, mid, x);
                seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
                return idx;
            }
        }
    }
    public static void main(String args[]){
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int a[] = new int[n];
        Arrays.fill(a, 1);
        SGTree st = new SGTree(n);
        st.build(0, 0, n-1, a);
        for (int i = 0; i < n; i++) {
            int pos = sc.nextInt();
            System.out.print(arr[st.findAndUpdate(0, 0, n-1, pos)] + " ");
        }
        sc.flush();
    }
}