import java.io.*;
import java.util.*;

class FastIO extends PrintWriter{
    private InputStream stream;private byte[]buf=new byte[1<<16];
    private int curChar,numChars;public FastIO(){this(System.in,sc.;}
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


public class NestedRangesCount {
 
    static class Pair{
        int s;
        int e;
        int idx;
        public Pair(int s, int e, int idx) {
            this.s = s;
            this.e = e;
            this.idx = idx;
        }
    }
 
    public static int lower_bound(ArrayList<Integer> list, int x, int size) {
        int s = 0;
        int e = size - 1;
        int ans = size;
        while(s <= e) {
            int mid = (s + e)/2;
            if(list.get(mid) >= x) {
                ans = mid;
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        }
        return ans;
    }
    public static int upper_bound(ArrayList<Integer> list, int x, int size) {
        int s = 0;
        int e = size - 1;
        int ans = size;
        while(s <= e) {
            int mid = (s + e)/2;
            if(list.get(mid) > x) {
                ans = mid;
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        Pair arr[] = new Pair[n];
        for(int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            arr[i] = new Pair(s, e, i);
        }
        Arrays.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if(p1.s == p2.s) {
                    return p2.e - p1.e;
                }
                return p1.s - p2.s;
            }
        });
 
        //how many of intervals contain it
        ArrayList<Integer> list = new ArrayList<>();
        int contains[] = new int[n];
        int contained[] = new int[n];
 
        list.add(arr[0].e);
        contained[arr[0].idx] = 0;
        int max = arr[0].e;
        int size = 1;
        for(int i = 1; i < n; i++) {
            int lb = lower_bound(list, arr[i].e, size);
            contained[arr[i].idx] = size - lb;
            max = Math.max(arr[i].e, max);
            list.add(lb, arr[i].e);
            size++;
        }
 
        list.clear();
 
        list.add(arr[n-1].e);
        contains[arr[n-1].idx] = 0;
        int min = arr[n-1].e;
        size = 1;
        for(int i = n-2; i >= 0; i--) {
            int ub = upper_bound(list, arr[i].e, size);
            contains[arr[i].idx] = ub;
            min = Math.min(arr[i].e, min);
            list.add(ub, arr[i].e);
            size++;
        }
 
        for(int i = 0; i < n; i++) {
            sc.print(contains[i] + " ");
        }
        sc.println();
 
        for(int i = 0; i < n; i++) {
            sc.print(contained[i] + " ");
        }
 
    }
 
    
}