import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        // Input marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            int mark = scanner.nextInt();

            if (mark < 0 || mark > 100) {
                System.out.println("Invalid mark. Please enter a number between 0 and 100.");
                i--; // re-ask for this subject
                continue;
            }

            marks[i] = mark;
            totalMarks += mark;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Determine grade
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display results
        System.out.println("\n📊 Result:");
        System.out.println("Total Marks = " + totalMarks + " out of " + (numSubjects * 100));
        System.out.printf("Average Percentage = %.2f%%\n", averagePercentage);
        System.out.println("Grade = " + grade);

        scanner.close();
    }
}
