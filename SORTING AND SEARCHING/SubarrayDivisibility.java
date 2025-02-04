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

public class  SubarrayDivisibility {
    static FastIO sc = new FastIO();

    public static void main(String args[]){
        int n = sc.nextInt();
        long arr[] = new long[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }
        long sum = 0;
        HashMap<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);
        long cnt = 0;
        for(int i = 0; i < n; i++) {
            sum = ((sum + arr[i]) % n + n) % n;

            // If sum == 0, then increment the result by 1
            // to count subarray arr[0...i]
            // if (sum == 0)
            //     cnt += 1;

            // Add count of all starting points for index i
            cnt += map.getOrDefault(sum, 0L);
            map.put(sum, map.getOrDefault(sum, 0L) + 1);
        }
        System.out.println(cnt);
        // System.out.println(-10 % 4);
        sc.flush();
    }
}