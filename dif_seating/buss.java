package dif_seating;

import java.util.Scanner;


public class buss {

}

class bus_seating  implements seating{
	int max_seats=45;
	int total_seats;
	public void handle_Seating() {
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Welcome  to bus seating arrangement");
		System.out.println("---------------------------------------------------------------------");
		
		System.out.println("Enter the number of seats to be allocate");
		Scanner sca = new Scanner(System.in);
	total_seats=sca.nextInt();
	
	
		System.out.println("Enter the type of seat ");
	   
        String[][] seatingArrangement = initializeSeating(10, 4);
        if(total_seats>40)
        {
        	System.out.println("enter the seats of less than 40");
        	System.out.println("Enter the number of seats to be allocate");
        	total_seats=sca.nextInt();
        	

        }

        System.out.println("Please select your preferred seating arrangement:");
        System.out.println("1. Window");
        System.out.println("2. Aisle");
        System.out.println("3. Mix");
        int ch = sca.nextInt();

        switch (ch) {
            case 1:
            	if(total_seats>20)
            	{
            		System.out.println("max of 20 windows seats are available");
            		System.out.println("Enter the number of seats to be allocate for window");
                	total_seats=sca.nextInt();
        
            	}
                markSeatsAsX(seatingArrangement, total_seats, 1);
                printSeatingArrangement(seatingArrangement);
                break;
            case 2:
            	if(total_seats>20)
            	{
            		System.out.println("max of 20 aisle seats are available");
            		System.out.println("Enter the number of seats to be allocate for aisle");
                	total_seats=sca.nextInt();
            	
            	}
                markSeatsAsX(seatingArrangement, total_seats, 2);
                printSeatingArrangement(seatingArrangement);
                break;
            case 3:
                markSeatsAsX(seatingArrangement, total_seats, 3);
                printSeatingArrangement(seatingArrangement);
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                return;
        }

      
		
		
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

	    private static void markSeatsAsX(String[][] seatingArrangement, int totalTickets, int preference) {
	        int ticketsRemaining = totalTickets;
	        for (int i = 0; i < seatingArrangement.length && ticketsRemaining > 0; i++) {
	            for (int j = 0; j < seatingArrangement[i].length && ticketsRemaining > 0; j++) {
	                if (seatingArrangement[i][j].equals("|_|") && isSeatOfPreference(i, j, preference)) {
	                    seatingArrangement[i][j] = "|X|";
	                    ticketsRemaining--;
	                }
	            }
	        }
	    }

	    private static boolean isSeatOfPreference(int row, int col, int preference) {
	        if (preference == 1) {
	            return col == 0 || col == 3;
	        } else if (preference == 2) {
	            return col == 1 || col == 2;
	        } else { 
	            return true;
	        }
	    }

	    private static void printSeatingArrangement(String[][] seatingArrangement) {
	        System.out.println("Bus Seating Arrangement:");
	        for (int i = 0; i < seatingArrangement.length; i++) {
	            System.out.print("Row " + (i + 1));
	            System.out.print(" ".repeat(4 - (int) Math.log10(i + 1)));
	            System.out.print(":");
	            for (int j = 0; j < seatingArrangement[i].length; j++) {
	                System.out.print(seatingArrangement[i][j] + " ");
	                
	                if(j==1)
	                {
	                	System.out.print("     |    ");
	                }
	            }
	            System.out.println();
	        }
	    
	}

		

}