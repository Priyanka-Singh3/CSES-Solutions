import java.io.*;
import java.util.*;

public class  StaticRangeMinimumQueries {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(System.out);

    public static void build(int ind, int low, int high, long[] seg, int a[]) {
        if (low == high) {
            seg[ind] = a[low];
            return;
        }
        int mid = (low + high) >> 1;

        build(2 * ind + 1, low, mid, seg, a);
        build(2 * ind + 2, mid + 1, high, seg, a);

        seg[ind] = Math.min(seg[2 * ind + 1],  seg[2 * ind + 2]);
    }

    public static long query(int ind, int low, int high, int l, int r, long seg[]) {
        // Fully within range
        if (l <= low && high <= r) {
            return seg[ind];
        }
        // Completely outside range
        if (high < l || r < low) {
            return Long.MAX_VALUE;
        }

        // Partial overlap
        int mid = (low + high) >> 1;

        long left = query(2 * ind + 1, low, mid, l, r, seg);
        long right = query(2 * ind + 2, mid + 1, high, l, r, seg);

        return Math.min(left, right);
    }

    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int arr[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long seg[] = new long[4 * n];
        build(0, 0, n - 1, seg, arr);

        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1; // 0-based index
            int r = Integer.parseInt(st.nextToken()) - 1;

            long ans = query(0, 0, n - 1, l, r, seg);
            sb.append(ans).append("\n");
        }
        pw.print(sb.toString());
        pw.flush();
    }

    public static void main(String args[]) throws IOException {
        solve();
    }
}