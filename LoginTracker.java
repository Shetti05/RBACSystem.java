import java.util.Scanner;

public class LoginTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String correctPassword = "admin123";
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter password: ");
            String input = sc.nextLine();

            if (input.equals(correctPassword)) {
                System.out.println("Login successful");
                break;
            } else {
                attempts--;
                System.out.println("Wrong password. Attempts left: " + attempts);
            }
        }

        if (attempts == 0) {
            System.out.println("Account locked");
        }
        sc.close();
    }
}
