import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter a text to count the words:");

        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();

        String[] words = inputText.split("[\\s\\p{Punct}]+");

        int wordCount = words.length;

        System.out.println("Total number of words: " + wordCount);

        scanner.close();
    }
}
