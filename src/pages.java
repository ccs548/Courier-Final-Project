import java.util.Scanner;

public class Pages {
    private Scanner scanner;
    private int currentPage;

    public Pages() {
        this.scanner = new Scanner(System.in);
        this.currentPage = 1;
    }

    public void navigate() {
        while (true) {
            switch (currentPage) {
                case 1:
                    showPage1();
                    break;
                case 2:
                    showPage2();
                    break;
                case 3:
                    showPage3();
                    break;
                default:
                    System.out.println("Invalid page number.");
                    break;
            }
        }
    }

    private void showPage1() {
        System.out.println("Welcome to Page 1");
        System.out.println("1. Go to Page 2");
        System.out.println("2. Go to Page 3");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        handleChoice(choice);
    }

    private void showPage2() {
        System.out.println("Welcome to Page 2");
        System.out.println("1. Go to Page 1");
        System.out.println("2. Go to Page 3");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        handleChoice(choice);
    }

    private void showPage3() {
        System.out.println("Welcome to Page 3");
        System.out.println("1. Go to Page 1");
        System.out.println("2. Go to Page 2");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        handleChoice(choice);
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                currentPage = 1;
                break;
            case 2:
                currentPage = 2;
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}
