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

public class  BuildingTeams {
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
        int team[] = new int[n];
        Arrays.fill(team, -1);
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(team[i] == -1) {
                q.offer(i);
                team[i] = 1;
                while(!q.isEmpty()) {
                    int curr = q.poll();
                    for(int j = 0; j < graph[curr].size(); j++) {
                        int node = graph[curr].get(j);
                        if(team[node] == -1) {
                            int t = team[curr] == 1 ? 2 : 1;
                            team[node] = t;
                            q.offer(node);
                        }
                        else if(team[node] == team[curr]) {
                            System.out.println("IMPOSSIBLE");
                            return;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < n; i++) {
            System.out.print(team[i] + " ");
        }

        sc.flush();
    }
}