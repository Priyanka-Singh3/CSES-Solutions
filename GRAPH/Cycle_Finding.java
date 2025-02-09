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

public class  Cycle_Finding {
    static FastIO sc = new FastIO();

    static class Edge {
        int u;
        int v;
        long wt;
        
        public Edge(int u, int v, long wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }
    }
    public static void main(String args[]){
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            adj[a].add(new Edge(a, b, c));
        }

        long dist[] = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        for(int i = 0; i < n-1; i++) {
            for(int j = 1; j <= n; j++) {
                for(Edge e: adj[j]) {
                    int adjNode = e.v;
                    long w = e.wt;
                    if(dist[j] != Long.MAX_VALUE && dist[j] + w < dist[adjNode]) {
                        dist[adjNode] = dist[j] + w;
                    }
                }
            }
        }

        //to find negative cycle
        for(int j = 1; j < n; j++) {
            for(Edge e: adj[j]) {
                int adjNode = e.v;
                long w = e.wt;
                if(dist[j] != Long.MAX_VALUE && dist[j] + w < dist[adjNode]) {
                    // there is a negative cycle
                    // find the negative cycle and print it 
                    
                    sc.println("YES");
                    return;
                }
            }
        }
        sc.println("NO");

        sc.flush();
    }
}