import java.io.*;
import java.util.*;

class FastIO extends PrintWriter {
    private InputStream stream;
    private byte[] buf = new byte[1 << 16];
    private int curChar, numChars;

    public FastIO() { this(System.in, System.out); }
    public FastIO(InputStream i, OutputStream o) { super(o); stream = i; }
    public FastIO(String i, String o) throws IOException { super(new FileWriter(o)); stream = new FileInputStream(i); }

    private int nextByte() {
        if (numChars == -1) throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try { numChars = stream.read(buf); } catch (IOException e) { throw new InputMismatchException(); }
            if (numChars == -1) return -1;
        }
        return buf[curChar++];
    }

    public String next() { 
        int c; do { c = nextByte(); } while (c <= ' ');
        StringBuilder res = new StringBuilder();
        do { res.appendCodePoint(c); c = nextByte(); } while (c > ' ');
        return res.toString();
    }

    public int nextInt() {
        int c; do { c = nextByte(); } while (c <= ' ');
        int sgn = 1;
        if (c == '-') { sgn = -1; c = nextByte(); }
        int res = 0;
        do { if (c < '0' || c > '9') throw new InputMismatchException(); res = 10 * res + c - '0'; c = nextByte(); } while (c > ' ');
        return res * sgn;
    }

    public long nextLong() {
        int c; do { c = nextByte(); } while (c <= ' ');
        long sgn = 1;
        if (c == '-') { sgn = -1; c = nextByte(); }
        long res = 0;
        do { if (c < '0' || c > '9') throw new InputMismatchException(); res = 10 * res + c - '0'; c = nextByte(); } while (c > ' ');
        return res * sgn;
    }
}

public class FlightDiscount {
    static FastIO sc = new FastIO();
    
    static class Edge {
        int v;
        long wt;
        
        public Edge(int v, long wt) {
            this.v = v;
            this.wt = wt;
        }
    }
    
    static class Pair implements Comparable<Pair> {
        int u, state;
        long wt;
        
        public Pair(int u, long wt, int state) {
            this.u = u;
            this.wt = wt;
            this.state = state;
        }
        
        public int compareTo(Pair o) {
            return Long.compare(this.wt, o.wt);
        }
    }
    
    public static void main(String args[]) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            adj[a].add(new Edge(b, c));
        }

        // 2D distance array to track both states
        long[][] dist = new long[n + 1][2];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0, 0)); // Start from node 1, cost 0, no discount used
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.u, state = curr.state;
            long currDist = curr.wt;

            if (currDist > dist[node][state]) continue;

            for (Edge e : adj[node]) {
                int v = e.v;
                long weight = e.wt;

                // Case 1: Move normally (same state)
                if (currDist + weight < dist[v][state]) {
                    dist[v][state] = currDist + weight;
                    pq.add(new Pair(v, dist[v][state], state));
                }

                // Case 2: Apply discount if not used yet
                if (state == 0) {
                    long discountedWeight = weight / 2;
                    if (currDist + discountedWeight < dist[v][1]) {
                        dist[v][1] = currDist + discountedWeight;
                        pq.add(new Pair(v, dist[v][1], 1));
                    }
                }
            }
        }

        long answer = dist[n][1];
        System.out.println(answer);

        sc.flush();
    }
}
