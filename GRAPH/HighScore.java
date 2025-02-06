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

public class  HighScore {
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

        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Edge> adj[] = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long x = sc.nextLong();
            edges.add(new Edge(a, b, x));
            adj[a].add(new Edge(a, b, x));

        }

        long dist[] = new long[n+1];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[1] = 0;

        for (int i = 0; i < n-1; i++) {
            for(Edge e: edges) {
                int u = e.u;
                int v = e.v;
                long wt = e.wt;
                if(dist[u] != Long.MIN_VALUE && dist[u] + wt > dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }
        for(Edge e: edges) {
            int u = e.u;
            int v = e.v;
            long wt = e.wt;
            if(dist[u] != Long.MIN_VALUE && dist[u] + wt > dist[v]) {
                //bfs to check if from this node can we visit the last node , if yes then answer will keep on increasing because 
                Queue<Integer> q = new LinkedList<>();
                q.add(u);
                boolean vis[] = new boolean[n+1];
                while(!q.isEmpty()) {
                    int curr = q.remove();
                    if(curr == n) {
                        System.out.println(-1);
                        return;
                    }
                    
                    vis[curr] = true;
                    
                    for(Edge edge: adj[curr]) {
                        int adjNode = edge.v;
                        if(!vis[adjNode]) {
                            q.add(adjNode);
                        }
                    }
                    
                }
            }
        }
        System.out.println(dist[n]);

        sc.flush();
    }
}