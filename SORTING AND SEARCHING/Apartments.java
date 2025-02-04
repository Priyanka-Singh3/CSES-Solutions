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
// public class Apartments {
//     public static void main(String args[]) {
//         FastIO sc = new FastIO();
//         int n = sc.nextInt();
//         int m = sc.nextInt();
//         int k = sc.nextInt();
//         int desiredSizeOfApartments[] = new int[n];
//         int sizeOfEachApartment [] = new int[m];

//         for(int i=0; i<n; i++) {
//             desiredSizeOfApartments[i] = sc.nextInt(); 
//         }
//         for(int i=0; i<m; i++) {
//             sizeOfEachApartment[i] = sc.nextInt(); 
//         }
//         Arrays.sort(desiredSizeOfApartments);
//         Arrays.sort(sizeOfEachApartment);

//         int i=0;
//         int j=0;
//         int matches = 0;
//         while(i < n && j < m) {
//             int minVal = desiredSizeOfApartments[i] - k;
//             int maxVal = desiredSizeOfApartments[i] + k;
//             // System.out.println(minVal);
//             // System.out.println(maxVal);
//             int size = sizeOfEachApartment[j];
//             // System.out.println(size);
//             if(minVal <= size  && size <= maxVal) {
//                 i++;
//                 j++;
//                 matches++;
//             }
//             else if(maxVal < size) {
//                 i++;
//             }
//             else {
//                 j++;
//             }
//         }
//         System.out.println(matches);
//     }
// }

import java.io.*;
import java.util.*;

public class Apartments {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        // Read n, m, k
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int k = Integer.parseInt(line[2]);
        
        // Read desired sizes
        int[] desiredSizeOfApartments = new int[n];
        String[] sizes = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            desiredSizeOfApartments[i] = Integer.parseInt(sizes[i]);
        }
        
        // Read available sizes
        int[] sizeOfEachApartment = new int[m];
        sizes = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            sizeOfEachApartment[i] = Integer.parseInt(sizes[i]);
        }
        
        // Sort both arrays
        Arrays.sort(desiredSizeOfApartments);
        Arrays.sort(sizeOfEachApartment);
        
        int i = 0, j = 0, matches = 0;
        
        // Two pointer approach
        while (i < n && j < m) {
            int desired = desiredSizeOfApartments[i];
            int apartment = sizeOfEachApartment[j];
            
            if (apartment >= desired - k && apartment <= desired + k) {
                // Found a match
                matches++;
                i++;
                j++;
            } else if (apartment < desired - k) {
                j++; // Apartment too small, move to the next apartment
            } else {
                i++; // Desired size too small, move to the next desired size
            }
        }
        
        // Output the result
        out.println(matches);
        out.close();
    }
}
