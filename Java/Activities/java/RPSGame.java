import java.util.Random;
import java.util.Scanner;

public class RPSGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[] choices = {"Rock", "Paper", "Scissors"};

        try {

            System.out.println("Welcome to Rock Paper Scissors Game");
            System.out.println("Enter your choice (Rock/Paper/Scissors): ");

            String userChoice = sc.nextLine();

            int computerIndex = rand.nextInt(3);
            String computerChoice = choices[computerIndex];

            System.out.println("Computer Choice: " + computerChoice);

            if(userChoice.equalsIgnoreCase(computerChoice)) {
                System.out.println("Rerocksult: Draw");
            }

            else if(
                    (userChoice.equalsIgnoreCase("Rock") && computerChoice.equals("Scissors")) ||
                    (userChoice.equalsIgnoreCase("Paper") && computerChoice.equals("Rock")) ||
                    (userChoice.equalsIgnoreCase("Scissors") && computerChoice.equals("Paper"))
            ) {
                System.out.println("Result: Player Wins");
            }

            else if(
                    (userChoice.equalsIgnoreCase("Rock") && computerChoice.equals("Paper")) ||
                    (userChoice.equalsIgnoreCase("Paper") && computerChoice.equals("Scissors")) ||
                    (userChoice.equalsIgnoreCase("Scissors") && computerChoice.equals("Rock"))
            ) {
                System.out.println("Result: Computer Wins");
            }

            else {
                throw new IllegalArgumentException("Invalid input! Please enter Rock, Paper, or Scissors.");
            }

        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        sc.close();
    }
}