
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n;

        do {
            System.out.print("Enter number of students: ");
            n = sc.nextInt();

            if (n <= 0) {
                System.out.println("Enter a number greater than 0.");
            }
        } while (n <= 0);

        sc.nextLine();

        String[] names = new String[n];
        int[] marks = new int[n];

        int total = 0;
        int highest = 0;
        int lowest = 100;

        for (int i = 0; i < n; i++) {

            System.out.print("Enter student name: ");
            names[i] = sc.nextLine();

            do {
                System.out.print("Enter marks (0-100): ");
                marks[i] = sc.nextInt();

                if (marks[i] < 0 || marks[i] > 100) {
                    System.out.println("Invalid marks! Try again.");
                }
            } while (marks[i] < 0 || marks[i] > 100);

            sc.nextLine();

            total += marks[i];

            if (marks[i] > highest) {
                highest = marks[i];
            }

            if (marks[i] < lowest) {
                lowest = marks[i];
            }
        }

        double average = (double) total / n;

        System.out.println("\n===== STUDENT GRADE REPORT =====");

        for (int i = 0; i < n; i++) {
            System.out.println(names[i] + " - " + marks[i] + " marks");
        }

        System.out.println("-------------------------------");
        System.out.printf("Average Marks: %.2f%n", average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);

        sc.close();
    }
}

