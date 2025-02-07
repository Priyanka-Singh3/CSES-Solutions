import java.io.*;
import java.util.*;


public class  ForestQueries {
    static FastIO sc = new FastIO();

    public static void main(String args[]){
        int n = sc.nextInt();
        int q = sc.nextInt();

        String mat[] = new String[n];
        for (int i = 0; i < n; i++) {
            mat[i] = sc.next();
        }

        long cnt[][] = new long[n][n];

        cnt[0][0] = mat[0].charAt(0) == '*' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            cnt[0][i] = cnt[0][i-1] + (mat[0].charAt(i) == '*' ? 1 : 0);
        }
        for (int i = 1; i < n; i++) {
            cnt[i][0] = cnt[i-1][0] + (mat[i].charAt(0) == '*' ? 1 : 0);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                cnt[i][j] = cnt[i][j-1] + cnt[i-1][j] - cnt[i-1][j-1] + (mat[i].charAt(j) == '*' ? 1 : 0);
            }
        }
        
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         sc.print(cnt[i][j] + " ");
        //     }
        //     sc.println();
        // }
        for (int i = 0; i < q; i++) {
            int x1 = sc.nextInt() - 1;
            int y1 = sc.nextInt() - 1;
            int x2 = sc.nextInt() - 1;
            int y2 = sc.nextInt() - 1;

            long ans = cnt[x2][y2];
            if(x1 - 1 >= 0) {
                ans -= cnt[x1 - 1][y2];
            }
            if(y1 - 1 >= 0) {
                ans -= cnt[x2][y1 - 1];
            }
            if(x1 - 1 >= 0 && y1 - 1 >= 0) {
                ans += cnt[x1 - 1][y1 - 1];
            }
            sc.println(ans);
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