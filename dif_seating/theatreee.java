package dif_seating;

import java.util.Scanner;


public class theatreee {

}

class theatre_seating implements seating{
	int max_tseats=300;
	 int rows = 20;
     int seatsPerRow = 30;

	public void handle_Seating() {
		
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Welcome  to theatre seating arrangement");
		System.out.println("---------------------------------------------------------------------");
		 Scanner scanner = new Scanner(System.in);

	  
	        System.out.println("Enter the total number of seats:");
	        int totalSeats = scanner.nextInt();
	        int tt=totalSeats;

	        if (totalSeats > rows * seatsPerRow) {
	            System.out.println("Invalid input. Total seats cannot exceed " + (rows * seatsPerRow));
	            return;
	        }
	        

	      
	        String seatType = getSeatType(scanner);		
	        
	
	   
	        String[][] seatingArrangement = initializeSeating(rows, seatsPerRow);

	   
	        markSeatsAsX(seatingArrangement, totalSeats, seatType);

	    
	        printSeatingArrangement(seatingArrangement);
	}
	
	

    private static String[][] initializeSeating(int rows, int seatsPerRow) {
        String[][] seatingArrangement = new String[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatingArrangement[i][j] = "|_|";
            }
        }
        return seatingArrangement;
    }
	        
	        private static void markSeatsAsX(String[][] seatingArrangement, int totalTickets,String choice) {
	            int ticketsRemaining = totalTickets;
	            for (int i = 0; i < seatingArrangement.length && ticketsRemaining > 0; i++) {
	                for (int j = 0; j < seatingArrangement[i].length && ticketsRemaining > 0; j++) {
	                    if (seatingArrangement[i][j].equals("|_|") && isSeatOfType(i,choice)) {
	                        seatingArrangement[i][j] = "|X|";
	                        ticketsRemaining--;
	                    }
	                }
	            }
	        }

	        private static void printSeatingArrangement(String[][] seatingArrangement) {
	            System.out.println("Theatre Seating Arrangement:");
	            for (int i = 0; i < seatingArrangement.length; i++) {
	            	  System.out.print("Row " + (i + 1));
	                  System.out.print(" ".repeat(4 - (int) Math.log10(i + 1)));
	                  System.out.print(":");
	                for (int j = 0; j < seatingArrangement[i].length; j++) {
	                    System.out.print(seatingArrangement[i][j] + "  ");
	                }
	                System.out.println();
	            }
	        }
	       

	    private static String getSeatType(Scanner scanner) {
	        while (true) {
	            System.out.println("Enter the type of seat:");
	            System.out.println("1. Lower");
	            System.out.println("2. Upper");
	            System.out.println("3. Mix");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    return "lower";
	                case 2:
	                    return "upper";
	                case 3:
	                    return "mix";
	                default:
	                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
	            }
	        }
	    }

	    private static boolean isSeatOfType(int row, String seatType) {
	        if (seatType.equals("upper")) {
	            return row <= 5;
	        } else if (seatType.equals("lower")) {
	            return row >5 ;
	        } else { 
	            return true;
	        }
		

	    }
}

