package td0;

import java.util.Scanner;

public class Average {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number 1 : ");
        double num_1 = sc.nextDouble();

        System.out.print("Number 2 : ");
        double num_2 = sc.nextDouble();

        System.out.print("Number 3 : ");
        double num_3 = sc.nextDouble();

        System.out.println("Average = " + ((num_1 + num_2 + num_3) * 1 / 3));
        sc.close();
    }
}
