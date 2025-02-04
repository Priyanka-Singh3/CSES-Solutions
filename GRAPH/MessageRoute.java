import java.io.*;
import java.lang.reflect.Array;
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

public class  MessageRoute {
    static FastIO sc = new FastIO();

    public static void main(String args[]){
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer> graph[] = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        while(m -- > 0) {
            int s = sc.nextInt() - 1;
            int d = sc.nextInt() - 1;
            graph[s].add(d);
            graph[d].add(s);
        }
        boolean vis[] = new boolean[n];
        int par[] = new int[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        Arrays.fill(par, -1);
        ArrayList<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()) {
            int curr = q.poll();
            vis[curr] = true;
            if(curr == n-1) {
                while(par[curr] != -1) {
                    ans.add(0, par[curr] + 1);
                    curr = par[curr];
                }
                ans.add(n);
                int size = ans.size();
                System.out.println(size);
                for(int i = 0; i < size; i++) {
                    System.out.print(ans.get(i) + " ");
                }
                return;
            }
            for(int i = 0; i < graph[curr].size(); i++) {
                int node = graph[curr].get(i);
                if(!vis[node]) {
                    q.add(node);
                    par[node] = curr;
                    vis[node] = true;
                }
            }
        }
        System.out.println("IMPOSSIBLE");

        sc.flush();
    }
}