// import java.util.*;
// import java.io.*;
// import java.util.*;
// class FastIO extends PrintWriter {
//     private InputStream stream;
//     private byte[] buf = new byte[1 << 16];
//     private int curChar, numChars;

//     public FastIO() {
//         this(System.in, System.out);
//     }

//     public FastIO(InputStream i, OutputStream o) {
//         super(o);
//         stream = i;
//     }

//     public FastIO(String i, String o) throws IOException {
//         super(new FileWriter(o));
//         stream = new FileInputStream(i);
//     }

//     private int nextByte() {
//         if (numChars == -1) throw new InputMismatchException();
//         if (curChar >= numChars) {
//             curChar = 0;
//             try {
//                 numChars = stream.read(buf);
//             } catch (IOException e) {
//                 throw new InputMismatchException();
//             }
//             if (numChars == -1) return -1;
//         }
//         return buf[curChar++];
//     }

//     public String nextLine() {
//         int c;
//         do {
//             c = nextByte();
//         } while (c <= '\n');
//         StringBuilder res = new StringBuilder();
//         do {
//             res.appendCodePoint(c);
//             c = nextByte();
//         } while (c > '\n');
//         return res.toString();
//     }

//     public String next() {
//         int c;
//         do {
//             c = nextByte();
//         } while (c <= ' ');
//         StringBuilder res = new StringBuilder();
//         do {
//             res.appendCodePoint(c);
//             c = nextByte();
//         } while (c > ' ');
//         return res.toString();
//     }

//     public int nextInt() {
//         int c;
//         do {
//             c = nextByte();
//         } while (c <= ' ');
//         int sgn = 1;
//         if (c == '-') {
//             sgn = -1;
//             c = nextByte();
//         }
//         int res = 0;
//         do {
//             if (c < '0' || c > '9') throw new InputMismatchException();
//             res = 10 * res + c - '0';
//             c = nextByte();
//         } while (c > ' ');
//         return res * sgn;
//     }

//     public long nextLong() {
//         int c;
//         do {
//             c = nextByte();
//         } while (c <= ' ');
//         long sgn = 1;
//         if (c == '-') {
//             sgn = -1;
//             c = nextByte();
//         }
//         long res = 0;
//         do {
//             if (c < '0' || c > '9') throw new InputMismatchException();
//             res = 10 * res + c - '0';
//             c = nextByte();
//         } while (c > ' ');
//         return res * sgn;
//     }

//     public double nextDouble() {
//         return Double.parseDouble(next());
//     }
// }

// public class NestedRangesCheck {

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

//     public static void main(String args[]) {
//         Scanner sc = new Scanner(System.in);
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

//         int contains[] = new int[n];
//         int contained[] = new int[n];


//         //whether it contain others
//         int min = Integer.MAX_VALUE;
//         for(int i = n-1; i >= 0; i--) {
//             if(arr[i].e < min) {
//                 contains[arr[i].idx] = 0;
//                 min = arr[i].e;
//             }
//             else {
//                 contains[arr[i].idx] = 1;
//             }
//         }

//         //whether it is contained by anyone
//         int max = 0;
//         for(int i = 0; i < n; i++) {
//             if(arr[i].e > max) {
//                 contained[arr[i].idx] = 0;
//                 max = arr[i].e;
//             }
//             else {
//                 contained[arr[i].idx] = 1;
//             }
//         }

//         for(int i = 0; i < n; i++) {
//             System.out.print(contains[i] + " ");
//         }
//         System.out.println();

//         for(int i = 0; i < n; i++) {
//             System.out.print(contained[i] + " ");
//         }
//     }
// }
#include <bits/stdc++.h>
using namespace std;

struct Pair {
    int s, e, idx;
    Pair(int start, int end, int index) : s(start), e(end), idx(index) {}
};

// Custom comparator for sorting
bool compare(const Pair &p1, const Pair &p2) {
    if (p1.s == p2.s) {
        return p1.e > p2.e; // Sort by 'e' in descending order if 's' is equal
    }
    return p1.s < p2.s; // Otherwise, sort by 's' in ascending order
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n;
    cin >> n;
    vector<Pair> arr;

    for (int i = 0; i < n; i++) {
        int s, e;
        cin >> s >> e;
        arr.emplace_back(s, e, i);
    }

    // Sort the array using the custom comparator
    sort(arr.begin(), arr.end(), compare);

    vector<int> contains(n, 0);
    vector<int> contained(n, 0);

    // Check whether a range contains others
    int minEnd = INT_MAX;
    for (int i = n - 1; i >= 0; i--) {
        if (arr[i].e < minEnd) {
            contains[arr[i].idx] = 0;
            minEnd = arr[i].e;
        } else {
            contains[arr[i].idx] = 1;
        }
    }

    // Check whether a range is contained by any other range
    int maxEnd = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i].e > maxEnd) {
            contained[arr[i].idx] = 0;
            maxEnd = arr[i].e;
        } else {
            contained[arr[i].idx] = 1;
        }
    }

    // Print the results
    for (int i = 0; i < n; i++) {
        cout << contains[i] << " ";
    }
    cout << "\n";

    for (int i = 0; i < n; i++) {
        cout << contained[i] << " ";
    }
    cout << "\n";

    return 0;
}
