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

public class  TasksandDeadlines {
    static FastIO sc = new FastIO();

    static class Element {
        int duration;
        int deadline;
        int idx;

        public Element(int d, int a, int i) {
            this.duration = d;
            this.deadline = a;
            this.idx = i;
        }
    }
    public static void main(String args[]){
        int n = sc.nextInt();
        Element arr[] = new Element[n];
        for(int i = 0; i < n; i++) {
            arr[i] = new Element(sc.nextInt(), sc.nextInt(), i);
        }
        Arrays.sort(arr, (p1, p2) -> {
            if(p1.duration == p2.duration) {
                return Integer.compare(p1.deadline, p2.deadline);
            }
            return Integer.compare(p1.duration, p2.duration);
        });

        long t = 0;
        long reward = 0;
        for(int i = 0; i < n; i++) {
            Element e = arr[i];
            t += e.duration;
            reward += (e.deadline - t);
        }
        sc.print(reward);
        sc.flush();
    }
}