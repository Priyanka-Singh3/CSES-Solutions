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

public class  NearestSmallerValues {
    static FastIO sc = new FastIO();
    static class Pair implements Comparable<Pair>{
        int first;
        int second;
        
        public Pair(int f, int s) {
            this.first = f;
            this.second = s;
        }
 
        @Override
        public int compareTo(Pair o) {
            if(this.first == o.first) {
                return Integer.compare(o.second, this.second);
            }
            return Integer.compare(this.first, o.first);
        }
    }
    public static int lower_bound(int X, List<Integer> indexes){
        int s = 0;
        int e = indexes.size()-1;
        int ans = e + 1;
        while(s <= e) {
            int mid = (s + e)/2;
            if(indexes.get(mid) >= X) {
                ans = mid;
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        } 
        return ans; 
    }
    
    public static void solve(int n, Pair[] arr) {
        List<Integer> indexes = new ArrayList<>();
        int ans[] = new int[n+1];
        for(int i = 0; i < n; i++) {
            int idx = arr[i].second;
            int lb = lower_bound(idx, indexes);
            int size = indexes.size();
            if(lb == 0) {
                ans[idx] = 0;
            }
            else {
                ans[idx] = indexes.get(lb-1)+1;
            }
            indexes.add(lb, idx);
        }
        for(int i = 0; i < n; i++) {
            System.out.print((ans[i]) + " ");
        }
    }
    public static void main(String args[]){
        int n = sc.nextInt();
        Pair arr[] = new Pair[n];
        for(int i = 0; i < n; i++) {
            arr[i] = new Pair(sc.nextInt(), i);
        }
        Arrays.sort(arr);
        solve(n, arr);
        sc.flush();
    }
}