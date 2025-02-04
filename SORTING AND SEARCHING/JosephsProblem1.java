import java.util.ArrayList;
import java.util.Scanner;

public class JosephsProblem1 {
    // public static void display(String args[]) {
    //     for(int i=0; i<args.length; i++) {
    //         System.out.println(args[i]);
    //         Thread.currentThread().sleep(1000);
    //     }
    // }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        int i = 1;
        int count = 0;
        int vis = 0;
        boolean check[] = new boolean[n+1];
        while(vis != n) {
            int k = i%n == 0 ? n : i%n;
            if(!check[k]) {
                count++;
                if(count % 2 == 0) {
                    list.add(k);
                    check[k] = true;
                    vis++;
                }
            }
            i++;
        }
        for(int num: list) {
            System.out.print(num + " ");
        }
        System.out.println();
       
    }
}