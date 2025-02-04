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

// public class Nested1RangesCount {
 
//     static class Pair{
//         int s;
//         int e;
//         int idx;
//         public Pair(int s, int e, int idx) {
//             this.s = s;
//             this.e = e;
//             this.idx = idx;
//         }
//     }
//     public static int lower_bound(ArrayList<Integer> list, int x, int size) {
//         int s = 0;
//         int e = size - 1;
//         int ans = size;
//         while(s <= e) {
//             int mid = (s + e)/2;
//             if(list.get(mid) >= x) {
//                 ans = mid;
//                 e = mid - 1;
//             }
//             else {
//                 s = mid + 1;
//             }
//         }
//         return ans;
//     }
//     public static int upper_bound(ArrayList<Integer> list, int x, int size) {
//         int s = 0;
//         int e = size - 1;
//         int ans = size;
//         while(s <= e) {
//             int mid = (s + e)/2;
//             if(list.get(mid) > x) {
//                 ans = mid;
//                 e = mid - 1;
//             }
//             else {
//                 s = mid + 1;
//             }
//         }
//         return ans;
//     }
//     public static void main(String args[]) {
//         FastIO sc = new FastIO();
//         int n = sc.nextInt();
//         Pair arr[] = new Pair[n];
//         for(int i = 0; i < n; i++) {
//             int s = sc.nextInt();
//             int e = sc.nextInt();
//             arr[i] = new Pair(s, e, i);
//         }
//         Arrays.sort(arr, new Comparator<Pair>() {
//             @Override
//             public int compare(Pair p1, Pair p2) {
//                 if(p1.s == p2.s) {
//                     return p2.e - p1.e;
//                 }
//                 return p1.s - p2.s;
//             }
//         });
 
//         //how many of intervals contain it
//         ArrayList<Integer> list1 = new ArrayList<>(); 
//         ArrayList<Integer> list2 = new ArrayList<>(); 
//         int contains[] = new int[n];
//         int contained[] = new int[n];
 
//         list1.add(arr[0].e);
//         contained[arr[0].idx] = 0;
//         int max = arr[0].e;
//         int size1 = 1, size2 = 1;

//         list2.add(arr[n-1].e);  
//         contains[arr[n-1].idx] = 0;
//         int min = arr[n-1].e;
//         size2 = 1;

//         for(int i = 1; i < n; i++) {
//             int lb = lower_bound(list1, arr[i].e, size1);
//             int ub = upper_bound(list2, arr[n-i-1].e, size2);
//             contains[arr[n-i-1].idx] = ub;
//             contained[arr[i].idx] = size1 - lb;
//             max = Math.max(arr[i].e, max);
//             min = Math.min(arr[n-i-1].e, min);
//             list1.add(lb, arr[i].e);
//             size1++;
//             list2.add(ub, arr[n-i-1].e);
//             size2++;
//         }
//         for(int i = 0; i < n; i++) {
//             sc.print(contains[i] + " ");
//         }
//         sc.println();
 
//         for(int i = 0; i < n; i++) {
//             sc.print(contained[i] + " ");
//         }
//         sc.flush();
 
//     }
 
    
// }

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <climits> // For INT_MAX and INT_MIN
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;
using namespace std;
template <class T>
using oset = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;
const long long MOD = 1000000007;


struct Pair {
    int s, e, idx;
    Pair(int start, int end, int index) : s(start), e(end), idx(index) {}
};



int main() {
    int n;
    cin >> n;

    vector<Pair> arr;
    for (int i = 0; i < n; ++i) {
        int s, e;
        cin >> s >> e;
        arr.emplace_back(s, e, i);
    }

    sort(arr.begin(), arr.end(), [](const Pair& p1, const Pair& p2) {
        if (p1.s == p2.s) {
            return p1.e > p2.e;
        }
        return p1.s < p2.s;
    });

    set<int> orderedSet;
    oset<pair<long long,long long> > s;

    vector<int> contains(n, 0), contained(n, 0);

    int max = INT_MIN;
    for (int i = 0; i < n; ++i) {
        int count = s.order_of_key({arr[i].e-1,MOD});
        contained[arr[i].idx] = i - count;
        s.insert({arr[i].e,i});
        if(max < arr[i].e) {
            max = arr[i].e;
        }
    }

    orderedSet.clear();
    s.clear();

    int min = INT_MAX;
    for (int i = n-1; i >= 0; --i) {
        int count = s.order_of_key({arr[i].e,MOD});
        contains[arr[i].idx] = count;
        s.insert({arr[i].e,i});
        if(min > arr[i].e) {
            min = arr[i].e;
        }
    }

    for (int i = 0; i < n; ++i) {
        cout << contains[i] << " ";
    }
    cout << endl;
 
    for (int i = 0; i < n; ++i) {
        cout << contained[i] << " ";
    }
    cout << endl;
    return 0;

}
