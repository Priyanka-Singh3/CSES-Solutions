
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

// public class TrafficLights {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int x = sc.nextInt();
//         int n = sc.nextInt();

//         TreeSet<Integer> positions = new TreeSet<>();
//         TreeMap<Integer, Integer> lengths = new TreeMap<>();

//         // Initialize positions and lengths
//         positions.add(0);
//         positions.add(x);
//         lengths.put(x, 1);

//         for (int i = 0; i < n; i++) {
//             int added = sc.nextInt();

//             // Insert new position
//             positions.add(added);

//             // Find previous and next positions
//             int previousVal = positions.lower(added);
//             int nextVal = positions.higher(added);

//             // Update lengths
//             int oldLength = nextVal - previousVal;
//             if (lengths.get(oldLength) == 1) {
//                 lengths.remove(oldLength);
//             } else {
//                 lengths.put(oldLength, lengths.get(oldLength) - 1);
//             }

//             int leftLength = added - previousVal;
//             int rightLength = nextVal - added;

//             lengths.put(leftLength, lengths.getOrDefault(leftLength, 0) + 1);
//             lengths.put(rightLength, lengths.getOrDefault(rightLength, 0) + 1);

//             // Output the largest length
//             System.out.print(lengths.lastKey() + " ");
//         }
//     }
// }
#include <bits/stdc++.h>
using namespace std;

int main() {
    int x, n;
    cin >> x >> n;

    set<int> positions;              // Stores the positions of the traffic lights
    multiset<int> lengths;           // Stores the lengths of segments

    // Initialize positions and lengths
    positions.insert(0);
    positions.insert(x);
    lengths.insert(x);

    for (int i = 0; i < n; i++) {
        int added;
        cin >> added;

        // Insert the new position
        auto it = positions.insert(added).first;

        // Find previous and next positions
        int previousVal = *prev(it);
        int nextVal = *next(it);

        // Update the segment lengths
        lengths.erase(lengths.find(nextVal - previousVal));
        lengths.insert(added - previousVal);
        lengths.insert(nextVal - added);

        // Output the largest segment
        cout << *lengths.rbegin() << " ";
    }

    return 0;
}

