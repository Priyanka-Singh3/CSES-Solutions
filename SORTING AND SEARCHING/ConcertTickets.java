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


// public class ConcertTickets {

//     public static int f(ArrayList<Integer> list, int x) {
//         int si = 0;
//         int ei = list.size() - 1;
//         int ans = -1;
//         while(si <= ei) {
//             int mid = (si + ei) / 2;
//             if(list.get(mid) <= x) {
//                 ans = mid;
//                 si = mid + 1;
//             }
//             else {
//                 ei = mid - 1;
//             }
//         }
//         return ans;
//     } 
//     public static void main(String args[]) {
//         FastIO sc = new FastIO();
//         int n = sc.nextInt();
//         int m = sc.nextInt();
//         ArrayList<Integer> list = new ArrayList<>();
//         int customers[] = new int[m];

//         for (int i = 0; i < n; i++) {
//             list.add(sc.nextInt());
//         }
//         Collections.sort(list);
//         for (int i = 0; i < m; i++) {
//             customers[i] = sc.nextInt();
//             int idx = f(list, customers[i]);
//             if(idx != -1) {
//                 System.out.println(list.get(idx));
//                 list.remove(idx);
//             }
//             else {
//                 System.out.println(-1);
//             }
//         }
        
//     }
// }
import java.util.*;
import java.io.*;

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
}

public class ConcertTickets {
    public static void main(String[] args) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        int m = sc.nextInt();

        // Use TreeMap to store ticket prices
        TreeMap<Integer, Integer> tickets = new TreeMap<>();

        // Read ticket prices into TreeMap
        for (int i = 0; i < n; i++) {
            int price = sc.nextInt();
            tickets.put(price, tickets.getOrDefault(price, 0) + 1);
        }

        // Use StringBuilder for fast output
        StringBuilder sb = new StringBuilder();

        // Process customer requests
        for (int i = 0; i < m; i++) {
            int maxPrice = sc.nextInt();
            Integer ticketPrice = tickets.floorKey(maxPrice);

            if (ticketPrice == null) {
                sb.append("-1\n");
            } else {
                sb.append(ticketPrice).append("\n");

                // Decrease the count or remove the ticket price
                if (tickets.get(ticketPrice) == 1) {
                    tickets.remove(ticketPrice);
                } else {
                    tickets.put(ticketPrice, tickets.get(ticketPrice) - 1);
                }
            }
        }

        // Print all output at once
        System.out.print(sb);
        sc.close();
    }
}
