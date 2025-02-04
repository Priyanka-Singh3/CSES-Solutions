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

public class  ArrayDivision {
    static FastIO sc = new FastIO();

    public static long helper(long sum, int arr[], int n) {
        long currsum = 0;
        long k = 0;
        for (int i = 0; i < n; i++) {
            if(currsum + arr[i] > sum) {
                currsum = arr[i];
                k++;
            }
            else {
                currsum += arr[i];
            }
        }
        return k+1;
    }
    public static void main(String args[]){
        int n = sc.nextInt();
        int k = sc.nextInt();

        int arr[] = new int[n];
        long sum = 0;
        int maxElement = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
            maxElement = Math.max(maxElement, arr[i]);
        }


        long lo = maxElement, hi = sum;
        long res = sum;  // Default to sum

        while(lo <= hi) {
            long mid = (lo + hi) >> 1;
            long k_ = helper(mid, arr, n);
            if(k_ <= k) {
                res = mid;
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }
        System.out.println(res);

        sc.flush();
    }
}