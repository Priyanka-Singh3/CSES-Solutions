import java.io.*;
import java.util.*;
class FastIO extends PrintWriter {
    private InputStream stream;
    private byte[] buf = new byte[1 << 16];
    private int curChar, numChars;

    public FastIO() {
        this(System.in, System.out);
    }

    public FastIO(InputStream i, OutputStream o) {
        super(o);
        stream = i;
    }

    public FastIO(String i, String o) throws IOException {
        super(new FileWriter(o));
        stream = new FileInputStream(i);
    }

    private int nextByte() {
        if (numChars == -1) throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars == -1) return -1;
        }
        return buf[curChar++];
    }

    public String nextLine() {
        int c;
        do {
            c = nextByte();
        } while (c <= '\n');
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = nextByte();
        } while (c > '\n');
        return res.toString();
    }

    public String next() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = nextByte();
        } while (c > ' ');
        return res.toString();
    }

    public int nextInt() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextByte();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            res = 10 * res + c - '0';
            c = nextByte();
        } while (c > ' ');
        return res * sgn;
    }

    public long nextLong() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        long sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextByte();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            res = 10 * res + c - '0';
            c = nextByte();
        } while (c > ' ');
        return res * sgn;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}

public class Projects {

    // public static long f(int i, int prev, int arr[][], int n, long[][] dp) {
    //     if(i == n) {
    //         return 0;
    //     }
    //     if(prev != -1 && dp[i][prev] != -1) {
    //         return dp[i][prev];
    //     }
    //     long notPick = f(i+1, prev, arr, n, dp);
    //     long pick = 0;
    //     if(prev == -1 || arr[prev][1] < arr[i][0] ) {
    //         pick = arr[i][2] + f(i+1, i, arr, n, dp);
    //     }
    //     if(prev == -1) {
    //         return Math.max(pick, notPick);
    //     }
    //     return dp[i][prev] = Math.max(pick, notPick);
    // }
    public static long f(int i, int arr[][], long dp[], int n) {
        if(i >= n) {
            return 0;
        }
        if(dp[i] != -1) {
            return dp[i];
        }
        long notPick = f(i+1, arr, dp, n);
        int nextPossibleIdx = next(i, i+1, arr, n);
        long pick = arr[i][2] + f(nextPossibleIdx, arr, dp, n);
        return dp[i] = Math.max(pick, notPick);
    }
    public static int next(int curr, int next, int arr[][], int n) {
        int start = next;
        int end = n;
        while(start < end) {
            int mid = (start + end)/2;
            if(arr[curr][1] >= arr[mid][0]) {
                start = mid+1;
            }
            else {
                end = mid;
            }
        }
        return start;
    }   
    public static void main(String args[]) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int arr[][] = new int[n][3];
        for(int i=0; i<n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        Arrays.sort(arr, (r1, r2) -> r1[0] - r2[0]);
        // int maxDay = arr[n-1][1];
        long dp[] = new long[n];
        Arrays.fill(dp, -1);
        
        long ans = f(0, arr, dp, n);
        System.out.println(ans);
    }
}

// import java.util.*;
// public class Solution{
//     public static class Pair{
//         int start;
//         int end;
//         int profit;
//         public Pair(int s,int e,int p){
//             this.start=s;
//             this.end=e;
//             this.profit=p;
//         }
//     }
//     static int dp[];
//     public static int helper(Pair arr[],int i){
//         int n=arr.length;
//         if(i==n){
//             return 0;
//         }
//         if(dp[i]!=-1){
//             return dp[i];
//         }
//         int max=arr[i].profit;
//         for(int j=i+1;j<n;j++){
//             if(arr[j].start>arr[i].end){
//                 max = Math.max(max , arr[i].profit + helper(arr,j) ) ;
//             }
//         }
//         return dp[i] = max;
//     }
//     public static void main(String args[]){
//         Scanner sc=new Scanner(System.in);
//         int n=sc.nextInt();
//         Pair arr[]=new Pair[n];
//         for(int i=0;i<n;i++){
//             int x=sc.nextInt();
//             int y=sc.nextInt();
//             int z=sc.nextInt();
//             arr[i]=new Pair(x,y,z);
//         }
//         dp=new int[n];
//         Arrays.fill(dp,-1);
//         Arrays.sort(arr,(a,b)->Integer.compare(a.start,b.start));
//         int max=0;
//         for(int i=0;i<n;i++){
//             max = Math.max(max,helper(arr,i));
//         }
//         System.out.println(max);
        
//     }
// }
