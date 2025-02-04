// import java.io.*;
// import java.util.*;

// class FastIO extends PrintWriter{
//     private InputStream stream;private byte[]buf=new byte[1<<16];
//     private int curChar,numChars;public FastIO(){this(System.in,System.out);}
//     public FastIO(InputStream i,OutputStream o){super(o);stream=i;}
//     public FastIO(String i,String o)throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}
//     private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return -1;}return buf[curChar++];}
//     public String nextLine(){int c;do{c=nextByte();}while(c<='\n');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>'\n');return res.toString();}
//     public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}
//     public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res * sgn;}
//     public long nextLong(){int c;do{c=nextByte();}while(c<=' ');long sgn=1;if(c=='-'){sgn=-1;c=nextByte();}long res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res * sgn;}
//     public double nextDouble(){return Double.parseDouble(next());
//     }
// }

// public class  SlidingWindowCost {
//     static FastIO sc = new FastIO();

//     public static PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
//     public static PriorityQueue<Integer> right = new PriorityQueue<>();
//     public static long leftSum = 0;
//     public static long rightSum = 0;


//     private static void addNum(int a) {
//         if(left.isEmpty() || a <= left.peek()) {
//             left.offer(a);
//             leftSum += a;
//         }
//         else {
//             right.offer(a);
//             rightSum += a;
//         }
//         balance();
//     }
//     private static void delNum(int a) {
//         if(a <= left.peek()) {
//             left.remove(a);
//             leftSum -= a;
//         }
//         else {
//             right.remove(a);
//             rightSum -= a;
//         }
//         balance();
//     }
//     private static void balance() {
//         if(left.size() > right.size() + 1) {
//             int l = left.poll();
//             right.offer(l);
//             rightSum += l;
//             leftSum -= l;
//         }
//         else if(left.size() < right.size()){
//             int r = right.poll();
//             left.offer(r);
//             leftSum += r;
//             rightSum -= r;
//         }
//     }
//     private static int getMedian() {
//         return left.peek();
//     }
//     public static void main(String args[]){
//         int n = sc.nextInt();
//         int k = sc.nextInt();
//         int a = k % 2 == 0 ? 0 : 1;
//         int arr[] = new int[n];
//         for (int i = 0; i < n; i++) {
//             arr[i] = sc.nextInt();
//         }
//         for (int i = 0; i < k; i++) {
//             addNum(arr[i]);
//         }
//         int m = getMedian();
//         System.out.print(m * (k/2 + a) - leftSum + rightSum - (m * (k/2)) + " ");
//         for(int i = k; i < n; i++) {
//             delNum(arr[i-k]);
//             addNum(arr[i]);
//             m = getMedian();
//             System.out.print(m * (k/2 + a) - leftSum + rightSum - (m * (k/2)) + " ");

//         }
//         sc.flush();
//     }
// }

#include <bits/stdc++.h>
using namespace std;

class FastIO {
public:
    FastIO() {
        ios_base::sync_with_stdio(false);
        cin.tie(nullptr);
        cout.tie(nullptr);
    }

    int nextInt() {
        int x;
        cin >> x;
        return x;
    }
};

multiset<int> leftSet, rightSet;
long long leftSum = 0, rightSum = 0;
void balance();
void addNum(int a) {
    if (leftSet.empty() || a <= *leftSet.rbegin()) {
        leftSet.insert(a);
        leftSum += a;
    } else {
        rightSet.insert(a);
        rightSum += a;
    }
    balance();
}

void delNum(int a) {
    if (leftSet.find(a) != leftSet.end()) {
        leftSet.erase(leftSet.find(a));
        leftSum -= a;
    } else {
        rightSet.erase(rightSet.find(a));
        rightSum -= a;
    }
    balance();
}

void balance() {
    if (leftSet.size() > rightSet.size() + 1) {
        int val = *leftSet.rbegin();
        leftSet.erase(prev(leftSet.end()));
        rightSet.insert(val);
        leftSum -= val;
        rightSum += val;
    } else if (leftSet.size() < rightSet.size()) {
        int val = *rightSet.begin();
        rightSet.erase(rightSet.begin());
        leftSet.insert(val);
        rightSum -= val;
        leftSum += val;
    }
}

int getMedian() {
    return *leftSet.rbegin();
}

int main() {
    FastIO io;
    int n = io.nextInt(), k = io.nextInt();
    vector<int> arr(n);
    for (int i = 0; i < n; i++) {
        arr[i] = io.nextInt();
    }

    for (int i = 0; i < k; i++) {
        addNum(arr[i]);
    }

    int m = getMedian();
    cout << (m * (k / 2 + (k % 2)) - leftSum + rightSum - (m * (k / 2))) << " ";

    for (int i = k; i < n; i++) {
        delNum(arr[i - k]);
        addNum(arr[i]);
        m = getMedian();
        cout << (m * (k / 2 + (k % 2)) - leftSum + rightSum - (m * (k / 2))) << " ";
    }

    cout << "\n";
    return 0;
}
