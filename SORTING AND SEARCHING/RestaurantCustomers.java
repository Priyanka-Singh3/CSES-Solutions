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

public class RestaurantCustomers {
    public static void main(String[] args) {
        FastIO sc = new FastIO();
        int n = sc.nextInt();
        
        // We will use a TreeMap to store events (arrival and departure)
        TreeMap<Integer, Integer> events = new TreeMap<>();
        
        // Reading the events
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt() - 1;  // Customer arrives at 'a'
            int b = sc.nextInt() - 1;  // Customer departs at 'b'
            
            // Increase customer count at 'a' and decrease at 'b'
            events.put(a, events.getOrDefault(a, 0) + 1);
            events.put(b, events.getOrDefault(b, 0) - 1);
        }
        
        // Now we process the events and find the maximum number of customers
        int currentCustomers = 0;
        int maxCustomers = Integer.MIN_VALUE;
        
        // Iterate over all events sorted by the time index
        for (Map.Entry<Integer, Integer> entry : events.entrySet()) {
            currentCustomers += entry.getValue();
            maxCustomers = Math.max(maxCustomers, currentCustomers);
        }
        
        // Output the result
        sc.println(maxCustomers);
        sc.close();
    }
}
