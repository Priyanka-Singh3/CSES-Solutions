// import java.io.*;
// import java.util.*;

// class FastIO extends PrintWriter{
//     private InputStream stream;private byte[]buf=new byte[1<<16];
//     private int curChar,numChars;public FastIO(){this(System.in,System.out);}
//     public FastIO(InputStream i,OutputStream o){super(o);stream=i;}
//     public FastIO(String i,String o)throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}
//     private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return -1;}return buf[curChar++];}
//     public String nextLine(){int c;do{c=nextByte();}while(c<='\n');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>'\n');return res.toString();}
//     public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}
//     public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res * sgn;}
//     public long nextLong(){int c;do{c=nextByte();}while(c<=' ');long sgn=1;if(c=='-'){sgn=-1;c=nextByte();}long res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res * sgn;}
//     public double nextDouble(){return Double.parseDouble(next());
//     }
// }

// public class  StaticRangeSumQueries {
//     static FastIO sc = new FastIO();
//     public static void build(int ind, int low, int high, long[] seg, int a[]) {
//         if(low == high) {
//             seg[ind] = a[low];
//             return;
//         }
//         int mid = (low + high) >> 1;

//         build(2 * ind + 1, low, mid, seg, a);
//         build(2 * ind + 2, mid + 1, high, seg, a);

//         seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
 
//     }

//     public static long query(int ind, int low, int high, int l, int r, long seg[]) {
//         if(l <= low && high <= r) {
//             return seg[ind];
//         }
//         if(high < l || r < low) {
//             return 0L;
//         }

//         int mid = (low + high) >> 1;

//         long left = query(2 * ind + 1, low, mid, l, r, seg);
//         long right = query(2 * ind + 2, mid + 1, high, l, r, seg);

//         return left + right;
//     }
//     public static void solve() {
//         int n = sc.nextInt();
//         int q = sc.nextInt();
        
//         int arr[] = new int[n];
//         for(int i = 0; i < n; i++) {
//             arr[i] = sc.nextInt();
//         }
//         long seg[] = new long[4 * n];
//         build(0, 0, n - 1, seg, arr);
//         while(q-- > 0) {
//             int l = sc.nextInt() - 1;
//             int r = sc.nextInt() - 1;

//             long ans = query(0, 0, n - 1, l, r, seg);
//             System.out.println(ans);
//         }

//     }

//     public static void main(String args[]){
//         solve();

//         sc.flush();
//     }
// }

import java.io.*;
import java.util.*;

public class StaticRangeSumQueries {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(System.out);

    public static void build(int ind, int low, int high, long[] seg, int a[]) {
        if (low == high) {
            seg[ind] = a[low];
            return;
        }
        int mid = (low + high) >> 1;

        build(2 * ind + 1, low, mid, seg, a);
        build(2 * ind + 2, mid + 1, high, seg, a);

        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }

    public static long query(int ind, int low, int high, int l, int r, long seg[]) {
        // Fully within range
        if (l <= low && high <= r) {
            return seg[ind];
        }
        // Completely outside range
        if (high < l || r < low) {
            return 0L;
        }

        // Partial overlap
        int mid = (low + high) >> 1;

        long left = query(2 * ind + 1, low, mid, l, r, seg);
        long right = query(2 * ind + 2, mid + 1, high, l, r, seg);

        return left + right;
    }

    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int arr[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long seg[] = new long[4 * n];
        build(0, 0, n - 1, seg, arr);

        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1; // 0-based index
            int r = Integer.parseInt(st.nextToken()) - 1;

            long ans = query(0, 0, n - 1, l, r, seg);
            sb.append(ans).append("\n");
        }
        pw.print(sb.toString());
        pw.flush();
    }

    public static void main(String args[]) throws IOException {
        solve();
    }
}
