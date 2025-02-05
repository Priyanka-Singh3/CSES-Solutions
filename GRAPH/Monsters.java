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

public class  Monsters {
    static FastIO sc = new FastIO();

    public static void dfs(int i, int j, boolean vis[][], String[] graph) {
        vis[i][j] = true;
        
    }
    public static void main(String args[]){
        int n = sc.nextInt();
        int m = sc.nextInt();

        Character graph[][] = new Character[n][m];
        Queue<int[]> q = new LinkedList<>();

        int aX = -1;
        int aY = -1;

        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                graph[i][j] = s.charAt(j);
                if(s.charAt(j) == 'M') {
                    q.add(new int[]{i, j});
                }
                if(s.charAt(j) == 'A') {
                    aX = i;
                    aY = j;
                }
            }
        }
        q.add(new int[]{aX, aY});
       
        while(!q.isEmpty()) {
            int[] curr = q.remove();
            int i = curr[0];
            int j = curr[1];

            int dx[] = {1, -1, 0, 0};
            int dy[] = {0, 0, 1, -1};
            char d[] = {'D', 'U', 'R', 'L'};

            for(int k = 0; k < 4; k++) {
                int x = i + dx[k];
                int y = j + dy[k];
                if(x < 0 || y < 0 || x >= n || y >= m || graph[x][y] != '.') continue;
                if(graph[i][j] == 'M') {
                    graph[x][y] = 'M';
                }
                else{
                    graph[x][y] = d[k];
                }
                q.add(new int[]{x, y});
            }
        }
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         System.out.print(graph[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        int bX = -1, bY = -1;
        for (int i = 0; i < m; i++) {
            if(graph[0][i] != 'M' && graph[0][i] != '#' && graph[0][i] != '.') {
                bX = 0;
                bY = i;
            }
            if(graph[n-1][i] != 'M' && graph[n-1][i] != '#' && graph[n-1][i] != '.') {
                bX = n-1;
                bY = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if(graph[i][0] != 'M' && graph[i][0] != '#' && graph[i][0] != '.') {
                bX = i;
                bY = 0;
            }
            if(graph[i][m-1] != 'M' && graph[i][m-1] != '#' && graph[i][m-1] != '.') {
                bX = i;
                bY = m-1;
            }
        }
        if(bX == -1 || bY == -1) {
            System.out.println("NO");
            return;
        }
        StringBuilder st = new StringBuilder();
        
        while(graph[bX][bY] != 'A') {
            st.append(graph[bX][bY]);
            if(graph[bX][bY] == 'U') bX++;
            else if(graph[bX][bY] == 'D') bX--;
            else if(graph[bX][bY] == 'R') bY--;
            else bY++;
        }
        System.out.println("YES");
        System.out.println(st.length());
        System.out.println(st.reverse());

        sc.flush();
    }
}