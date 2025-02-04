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

public class  CountingRooms {
    static FastIO sc = new FastIO();

    public static void dfs(int i, int j, String[] grid, boolean vis[][], int n, int m) {
        vis[i][j] = true;
        int X[] = {0, 0, 1, -1};
        int Y[] = {1, -1, 0, 0};

        for(int k = 0; k < 4; k++) {
            int x = X[k] + i;
            int y = Y[k] + j;
            if(x < n  && y < m && x >= 0 && y >= 0 && !vis[x][y] && grid[x].charAt(y) == '.') {
                dfs(x, y, grid, vis, n, m);
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

        //first row
        for(int i = 0; i < m; i++) {
            if(grid[0].charAt(i) == '.' && !vis[0][i]) {
                dfs(0, i, grid, vis, n, m);
            }
        }

        //last row
        for(int i = 0; i < m; i++) {
            if(grid[n-1].charAt(i) == '.' && !vis[n-1][i]) {
                dfs(n-1, i, grid, vis, n, m);
            }
        }

        //first col
        for(int i = 0; i < n; i++) {
            if(grid[i].charAt(0) == '.' && !vis[i][0]) {
                dfs(i, 0, grid, vis, n, m);
            }
        }

        //last col
        for(int i = 0; i < n; i++) {
            if(grid[i].charAt(m-1) == '.' && !vis[i][m-1]) {
                dfs(i, m-1, grid, vis, n, m);
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!vis[i][j] && grid[i].charAt(j) == '.') {
                    cnt++;
                    dfs(i, j, grid, vis, n, m);
                }
            }
        }

        System.out.println(cnt);
        sc.flush();
    }
}