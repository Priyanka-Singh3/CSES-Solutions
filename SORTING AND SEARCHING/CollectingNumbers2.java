
// import java.io.*;
// import java.lang.reflect.Array;
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

// public class CollectingNumbers2 {

//     public static class Pair {
//         int val1;
//         int val2;
//         public Pair(int v1, int v2) {
//             this.val1 = v1;
//             this.val2 = v2;
//         }

//         @Override
//         public boolean equals(Object o) {
//             if (this == o) return true; // Check reference equality
//             if (o == null || getClass() != o.getClass()) return false; // Check type compatibility
//             Pair pair = (Pair) o;
//             return val1 == pair.val1 && val2 == pair.val2; // Check field equality
//         }
//         @Override
//         public int hashCode() {
//             return Objects.hash(val1, val2); // Compute hash based on fields
//         }
//     }

//     public static void main(String[] args) {
//         FastIO sc = new FastIO();
//         int n = sc.nextInt();
//         int m = sc.nextInt();
//         int arr[] = new int[n + 1];
//         int pos[] = new int[n + 1];
//         for(int i = 1; i <= n; i++) {
//             arr[i] = sc.nextInt();
//             pos[arr[i]] = i;
//         }
//         int rounds = 1;
//         for(int i = 1; i < n; i++) {
//             if(pos[i] > pos[i + 1]) {
//                 rounds++;
//             }
//         }
//         for(int i = 0; i < m; i++) {
//             int l = sc.nextInt();
//             int r = sc.nextInt();
//             HashSet<Pair> set = new HashSet<>();
//             int leftEle = arr[l];
//             int rightEle = arr[r];
//             if(leftEle - 1 >= 1) {
//                 set.add(new Pair(leftEle-1, leftEle));
//             }
//             if(leftEle + 1 <= n) {
//                 set.add(new Pair(leftEle, leftEle + 1));
//             }
//             if(rightEle + 1 <= n) {
//                 set.add(new Pair(rightEle, rightEle + 1));
//             }
//             if(rightEle - 1 >= 1) {
//                 set.add(new Pair(rightEle - 1, rightEle));
//             }
            

//             for(Pair p: set) {
//                 if(pos[p.val1] > pos[p.val2]) {
//                     rounds--;
//                 }
//             }
//             int temp = arr[l];
//             arr[l] = arr[r];
//             arr[r] = temp;
            
//             pos[arr[l]] = l;
//             pos[arr[r]] = r;

//             for(Pair p: set) {
//                 if(pos[p.val1] > pos[p.val2]) {
//                     rounds++;
//                 }
//             }
//             System.out.println(rounds);
//         }
//     }
// }
#include <bits/stdc++.h>
using namespace std;

class FastIO {
public:
    FastIO() {
        ios::sync_with_stdio(false);
        cin.tie(nullptr);
    }

    int nextInt() {
        int x;
        cin >> x;
        return x;
    }
};

int main() {
    FastIO io;

    int n = io.nextInt();
    int m = io.nextInt();

    vector<int> arr(n + 1), pos(n + 1);

    for (int i = 1; i <= n; i++) {
        arr[i] = io.nextInt();
        pos[arr[i]] = i;
    }

    int rounds = 1;
    for (int i = 1; i < n; i++) {
        if (pos[i] > pos[i + 1]) {
            rounds++;
        }
    }

    for (int i = 0; i < m; i++) {
        int l = io.nextInt();
        int r = io.nextInt();

        set<pair<int, int>> affectedPairs;

        int leftEle = arr[l];
        int rightEle = arr[r];

        // Track affected pairs for leftEle
        if (leftEle - 1 >= 1) {
            affectedPairs.insert({leftEle - 1, leftEle});
        }
        if (leftEle + 1 <= n) {
            affectedPairs.insert({leftEle, leftEle + 1});
        }

        // Track affected pairs for rightEle
        if (rightEle - 1 >= 1) {
            affectedPairs.insert({rightEle - 1, rightEle});
        }
        if (rightEle + 1 <= n) {
            affectedPairs.insert({rightEle, rightEle + 1});
        }

        // Remove the effect of existing pairs before the swap
        for (const auto &p : affectedPairs) {
            if (pos[p.first] > pos[p.second]) {
                rounds--;
            }
        }

        // Perform the swap
        swap(arr[l], arr[r]);
        pos[arr[l]] = l;
        pos[arr[r]] = r;

        // Add the effect of pairs after the swap
        for (const auto &p : affectedPairs) {
            if (pos[p.first] > pos[p.second]) {
                rounds++;
            }
        }

        // Output the current number of rounds
        cout << rounds << '\n';
    }

    return 0;
}
