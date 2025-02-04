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

public class  SumofFourValues {
    static FastIO sc = new FastIO();
    static class Pair implements Comparable<Pair>{
        long first;
        int second;
        
        public Pair(long f, int s) {
            this.first = f;
            this.second = s;
        }
 
        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.first, o.first);
        }
    }
    public static void solve(Pair[] arr, int n, long x) {
        int i = 0;
        while(i < n-3) {
            int j = i + 1;
            long req = x - arr[i].first;
            while(j < n-2) {
                long req2 = req - arr[j].first;
                int left = j + 1;
                int end = n - 1;
                while(left < end) {
                    long sum = arr[left].first + arr[end].first;
                    if(sum > req2) {
                        end--;
                    }
                    else if(sum < req2) {
                        left++;
                    }
                    else {
                        int[] result = {arr[i].second + 1, arr[j].second + 1, arr[left].second + 1, arr[end].second + 1};
                        Arrays.sort(result);
                        System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
                        return;
                    }
                }
                j++;
            }
            i++;
        }
        System.out.println("IMPOSSIBLE");
    }
    public static void main(String args[]){
        int n = sc.nextInt() ;
        long x = sc.nextLong();
        Pair arr[] = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(sc.nextLong(), i);
        }
        Arrays.sort(arr);
        solve(arr, n, x);
        sc.flush();

        sc.flush();
    }
}