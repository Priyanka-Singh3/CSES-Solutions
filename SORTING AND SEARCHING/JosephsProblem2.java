// import java.util.ArrayList;
// import java.util.Scanner;

// public class JosephsProblem2 {
//     public static void main(String args[]) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int x = sc.nextInt();
//         ArrayList<Integer> list = new ArrayList<>();
//         int i = 1;
//         int count = 0;
//         int vis = 0;
//         boolean check[] = new boolean[n+1];
//         while(vis != n) {
//             int k = i%n == 0 ? n : i%n;
//             if(!check[k]) {
//                 count++;
//                 if(count % (x+1) == 0) {
//                     list.add(k);
//                     check[k] = true;
//                     vis++;
//                 }
//             }
//             i++;
//         }
//         for(int num: list) {
//             System.out.print(num + " ");
//         }
//         System.out.println();
       
//     }
// }
import java.util.ArrayList;
import java.util.Scanner;

public class JosephsProblem2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        int current = 0; // The current position in the circle
        int count = 0;   // Counts steps taken
        int visitedCount = 0; // Tracks how many people have been removed

        while (visitedCount < n) {
            count = 0;
            while (count < x + 1) { // Skip `x+1` steps
                current = (current % n) + 1; // Move to the next position
                if (!visited[current]) {
                    count++; // Increment count only if the person is not visited
                }
            }
            // Mark the person at the current position as removed
            list.add(current);
            visited[current] = true;
            visitedCount++;
        }

        // Print the result
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
