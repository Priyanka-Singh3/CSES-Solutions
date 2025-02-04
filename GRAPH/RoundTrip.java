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

public class  RoundTrip {
    static FastIO sc = new FastIO();

    public static ArrayList<Integer> path = new ArrayList<>();
    public static int start = -1;
    public static boolean dfs(int i, int par, boolean vis[], ArrayList<Integer> adj[]) {
        vis[i] = true;
        boolean check = false;
        for(int adjNode: adj[i]) {
            if(!vis[adjNode]) {
                if (dfs(adjNode, i, vis, adj)) {
                    if (start != -1) {
                        path.add(i);
                        if (i == start) start = -1; // Stop adding once full cycle is captured
                    }
                    return true;
                }
            }
            else if(par != adjNode) {
                path.add(0, i);
                start = adjNode;
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]){
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer> adj[] = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        while(m-- > 0) {
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;

            adj[a].add(b);
            adj[b].add(a);
        }
        boolean vis[] = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!vis[i]) {
                if(dfs(i, -1, vis, adj)) {
                    break;
                }
            }
        }

        if (path.isEmpty()) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        // Reconstruct the cycle path
        int cycleStart = path.get(0);
        ArrayList<Integer> cycle = new ArrayList<>();
        cycle.add(cycleStart);
        for (int i = 1; i < path.size(); i++) {
            cycle.add(path.get(i));
            if (path.get(i) == cycleStart) break;
        }
        cycle.add(cycleStart);

        System.out.println(cycle.size());
        for (int node : cycle) {
            System.out.print((node + 1) + " ");
        }
        System.out.println();
        // for(int i = 0; i < path.size(); i++) {
        //     System.out.print(path.get(i) + 1 + " ");
        // }
        // System.out.print(path.get(0)+1);
        // sc.flush();
    }
}