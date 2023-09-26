import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = in.nextInt();

        String[][] cinema = createCinema(rows, seats);

        while (true) {
            displayMenu();
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    showSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    displayStatistics(cinema);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 0, 1, 2, or 3.");
            }
        }
    }

    public static String[][] createCinema(int rows, int seats) {
        String[][] cinema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S"; // Initialize all seats as available
            }
        }
        return cinema;
    }

    public static void displayMenu() {
        System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
    }

    public static void showSeats(String[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void buyTicket(String[][] cinema) {
    Scanner in = new Scanner(System.in);

    while (true) {
        System.out.println("Enter a row number:");
        int rowNumber = in.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = in.nextInt();

        if (rowNumber < 1 || rowNumber > cinema.length || seatNumber < 1 || seatNumber > cinema[0].length) {
            System.out.println("Wrong input!");
        } else if (cinema[rowNumber - 1][seatNumber - 1].equals("B")) {
            System.out.println("That ticket has already been purchased!");
        } else {
            int ticketPrice = calculateTicketPrice(cinema.length, cinema[0].length, rowNumber);
            System.out.println("Ticket price: $" + ticketPrice);
            cinema[rowNumber - 1][seatNumber - 1] = "B";
            break;
        }
    }
}



    public static int calculateTicketPrice(int totalRows, int totalSeats, int chosenRow) {
        int total = totalRows * totalSeats;
        if (total <= 60 || chosenRow <= totalRows / 2) {
            return 10;
        } else {
            return 8;
        }
    }

    public static void displayStatistics(String[][] cinema) {
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                if (cinema[i][j].equals("B")) {
                    purchasedTickets++;
                    currentIncome += calculateTicketPrice(cinema.length, cinema[0].length, i + 1);
                }
            }
        }

        totalIncome = totalIncome(cinema.length, cinema[0].length);

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        double percentage = (double) purchasedTickets / (cinema.length * cinema[0].length) * 100;
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static int totalIncome(int totalRows, int totalSeats) {
        int total = totalRows * totalSeats;
        if (total <= 60) {
            return total * 10;
        } else {
            int frontHalfIncome = (totalRows / 2) * totalSeats * 10;
            int backHalfIncome = ((totalRows + 1) / 2) * totalSeats * 8;
            return frontHalfIncome + backHalfIncome;
        }
    }
}
