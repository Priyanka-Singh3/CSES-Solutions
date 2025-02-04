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

public class  BuildingRoads {
    static FastIO sc = new FastIO();

    static class Edge {
        int s;
        int d;

        public Edge(int s, int d) {
            this.s = s;
            this.d = d;
        }
    }
    public static void dfs(int curr, boolean vis[], ArrayList<Integer> graph[], int n) {
        vis[curr] = true;
        Stack<Integer> st = new Stack<>();
        st.push(curr);
        while(!st.isEmpty()) {
            int s = st.pop();
            for(int i = 0; i < graph[s].size(); i++) {
                int d = graph[s].get(i);
                if(!vis[d]) {
                    vis[d] = true;
                    st.push(d);
                }
            }
        }

    }
    public static void solve() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer> graph[] = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        while(m-- > 0) {
            int s = sc.nextInt() - 1;
            int d = sc.nextInt() - 1;

            graph[s].add(d);
            graph[d].add(s);

        }
        ArrayList<Integer> list = new ArrayList<>();
        boolean vis[] = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!vis[i]) {
                list.add(i+1);
                dfs(i, vis, graph, n);
            }
        }
        int size = list.size();
        if(size > 1) {
            System.out.println(size - 1);
            for(int i = 0; i < size - 1; i++) {
                System.out.println((list.get(i)) + " " + (list.get(i+1)));
            }
        }
        else {
            System.out.println(0);
        }
    }
    public static void main(String args[]){
        solve();
        sc.flush();
    }
}