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

public class  Labyrinth {
    static FastIO sc = new FastIO();
    public static void main(String args[]){
        int n = sc.nextInt();
        int m = sc.nextInt();

        String grid[] = new String[n];

        for(int i = 0; i < n; i++) {
            grid[i] = sc.next();
        }

        boolean vis[][] = new boolean[n][m];
        int d[][] = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        char dir[] = {'D', 'U', 'L', 'R'};

        int startX = -1, startY = -1;
        int endX = -1, endY = -1;

        // Find positions of 'A' and 'B'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i].charAt(j) == 'A') {
                    startX = i;
                    startY = j;
                }
                if (grid[i].charAt(j) == 'B') {
                    endX = i;
                    endY = j;
                }
            }
        }
        vis[startX][startY] = true;

        int par[][] = new int[n][m];
        for(int row[]: par) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startX, startY});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            if(x == endX && y == endY) {
                StringBuilder ans = new StringBuilder();
                while(par[x][y] != -1) {
                    int p = par[x][y];
                    ans.append(dir[p]);
                    x -= d[p][0];
                    y -= d[p][1];
                }
                System.out.println("YES");
                System.out.println(ans.length());
                System.out.println(ans.reverse());
                return;
            }

            for(int k = 0; k < 4; k++) {
                int nx = x + d[k][0];
                int ny = y + d[k][1];

                if(nx >= 0 && ny >= 0 && nx < n && ny < m && !vis[nx][ny] && grid[nx].charAt(ny) != '#') {
                    q.offer(new int[] {nx, ny});
                    par[nx][ny] = k;
                    vis[nx][ny] = true;
                }
            }
        }
        System.out.println("NO");

        sc.flush();
    }
}