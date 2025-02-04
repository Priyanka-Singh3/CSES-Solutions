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

public class  CountingRooms1 {
    static FastIO sc = new FastIO();
    public static void dfs(int i, int j, String[] grid, boolean vis[][], int n, int m, int X[], int Y[]) {
        Stack<int[]> st = new Stack<>();
        st.push(new int[] {i, j});
        
        vis[i][j] = true;
        while(!st.isEmpty()) {
            int[] info = st.pop();
            int x_ = info[0];
            int y_ = info[1];
            for(int k = 0; k < 4; k++) {
                int x = X[k] + x_;
                int y = Y[k] + y_;
                if(x < n  && y < m && x >= 0 && y >= 0 && !vis[x][y] && grid[x].charAt(y) == '.') {
                    // dfs(x, y, grid, vis, n, m, X, Y);
                    vis[x][y] = true;
                    st.push(new int[] {x, y});
                }
            }
        }
        
    }



    public static void main(String args[]){
        int n = sc.nextInt();
        int m = sc.nextInt();

        String grid[] = new String[n];

        for(int i = 0; i < n; i++) {
            grid[i] = sc.next();
        }

        boolean vis[][] = new boolean[n][m];
        int X[] = {0, 0, 1, -1};
        int Y[] = {1, -1, 0, 0};
        
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!vis[i][j] && grid[i].charAt(j) == '.') {
                    cnt++;
                    dfs(i, j, grid, vis, n, m, X, Y);
                }
            }
        }

        System.out.println(cnt);

        sc.flush();
    }
}