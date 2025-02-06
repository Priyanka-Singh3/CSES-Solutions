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

public class  ShortestRoutesII {
    static FastIO sc = new FastIO();
    static class Pair {
        int node;
        long weight;
    
        Pair(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
    
        // @Override
        // public int compareTo(Pair o) {
        //     return Long.compare(this.weight, o.weight);
        // }
    }
    public static void main(String args[]){
        int n = sc.nextInt(), m = sc.nextInt(), q = sc.nextInt();

        long mat[][] = new long[n+1][n+1];
        for(long r[]: mat) {
            Arrays.fill(r, Long.MAX_VALUE);
        }
        for (int i = 1; i <= n; i++) {
            mat[i][i] = 0L;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            long c = sc.nextInt();
            mat[a][b] = Math.min(mat[a][b], c);
            mat[b][a] = Math.min(mat[b][a], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(mat[i][k] == Long.MAX_VALUE || mat[k][j] == Long.MAX_VALUE) continue;
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }

        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(mat[a][b] == Long.MAX_VALUE) {
                sc.println(-1);
            }
            else {
                sc.println(mat[a][b]);
            }
        }

        sc.flush();
    }
}