package dif_seating;

import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

public class examm {

}

class exam_seating implements seating {
    static int firstYear = 1001;
    static int secondYear = 2001;
    static int thirdYear = 3001;
    static int fourthYear = 4001;
    double classRooms;
    static int total;
    static int classrooms = 1;
    int firstYearCount;
    int secondYearCount;
    int thirdYearCount;
    int fourthYearCount;
    int select;
    int fy;
    int sy;
    int ty;
    int fyy;

    static int firstYearStudents;
    static int secondYearStudents;
    static int thirdYearStudents;
    static int fourthYearStudents;
    Vector<HashMap<String, Integer>> classroomsData = new Vector<>();


    Scanner sc = new Scanner(System.in);

    public void handle_Seating() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Welcome to the exam seating arrangement");
        System.out.println("---------------------------------------------------------------------");

        System.out.println("Enter the total number of students for seating allocation");
		
        
         
		int totalstudents = sc.nextInt();
        
        System.out.println("40 seats for each classroom");
        System.out.println("Enter the number of students for the first year:");
        firstYearStudents = sc.nextInt();
        fy=firstYearStudents;
        validateInput(firstYearStudents);

        System.out.println("Enter the number of students for the second year:");
        secondYearStudents = sc.nextInt();
         sy=secondYearStudents;
        validateInput(secondYearStudents);
        

        System.out.println("Enter the number of students for the third year:");
        thirdYearStudents = sc.nextInt();
        ty=thirdYearStudents;
        validateInput(thirdYearStudents);
        

        System.out.println("Enter the number of students for the fourth year:");
        fourthYearStudents = sc.nextInt();
        fyy=fourthYearStudents;
        validateInput(fourthYearStudents);

        total = firstYearStudents + secondYearStudents + thirdYearStudents + fourthYearStudents;
        
        
        if (totalstudents <= 0) {
            System.out.println("Error: Invalid total number of students.");
        
        } 
        else if (totalstudents < total) 
        {
            System.out.println("Error: Total students cannot be less than the sum of students in each year.");
            
        }
        else if(totalstudents > total)
        {
	            System.out.println("Error: Total students cannot be greater than the sum of students in each year.");
        }
        else
        {
        double t = total / 40.0;
        classRooms = Math.ceil(t);

        System.out.println("You require " + (int) classRooms + " rooms for seating allocation.");

        for (int roomNumber = 1; roomNumber <= classRooms; roomNumber++) {
            System.out.println("For Classroom " + roomNumber + ":");
            boolean validCombination = chooseArrangement(sc);
            while (!validCombination) {
                System.out.println("Invalid combination. Please select a valid combination (1-6).");
                validCombination = chooseArrangement(sc);
            }
            
            allocateSeats(sc);
            displayRemainingStudents();

            HashMap<String, Integer> classroomData = new HashMap<>();
            classroomData.put("year1", firstYearCount);
            classroomData.put("year2", secondYearCount);
            classroomData.put("year3", thirdYearCount);
            classroomData.put("year4", fourthYearCount);
            classroomsData.add(classroomData);
            
            
        }


        System.out.println("Seating arrangement:");

       

 
  System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Classroom", "year1", "year2", "year3","year4");

        for (int roomNumber = 1; roomNumber <= classRooms; roomNumber++) {
           
            HashMap<String, Integer> classroomData = classroomsData.get(roomNumber - 1);

      
            System.out.printf("%-10d %-10d %-10d %-10d %-10d\n", roomNumber,
                    classroomData.get("year1"), classroomData.get("year2"),
                    classroomData.get("year3"), classroomData.get("year4"));
        }
        sc.close();
        }
    }

    private void validateInput(int input) {
        if (input < 0) {
            throw new IllegalArgumentException("Invalid number of students. Please enter a non-negative value.");
        }
    }

    HashMap<String, Integer> map = new HashMap<>();

    private boolean chooseArrangement(Scanner sc) {
        System.out.println("Select the seating combination you need");
        System.out.println("1. year1, year2");
        System.out.println("2. year1, year3");
        System.out.println("3. year1, year4");
        System.out.println("4. year2, year3");
        System.out.println("5. year2, year4");
        System.out.println("6. year3, year4");

        select = sc.nextInt();

        return select >= 1 && select <= 6;
    }

    private void allocateSeats(Scanner sc) {
    	
 
        int studentsForCombination = 40;

     

        int maxFirstYear = Math.min(firstYearStudents, studentsForCombination);
        int maxSecondYear = Math.min(secondYearStudents, studentsForCombination);
        int maxThirdYear = Math.min(thirdYearStudents, studentsForCombination);
        int maxFourthYear = Math.min(fourthYearStudents, studentsForCombination);
        
        if ((select == 1 && (maxFirstYear == 0  && maxSecondYear == 0)) ||
            (select == 2 && (maxFirstYear == 0 && maxThirdYear == 0)) ||
            (select == 3 && (maxFirstYear == 0 && maxFourthYear == 0)) ||
            (select == 4 && (maxSecondYear == 0 && maxThirdYear == 0)) ||
            (select == 5 && (maxSecondYear == 0 && maxFourthYear == 0)) ||
            (select == 6 && (maxThirdYear == 0 && maxFourthYear == 0))) {
            System.out.println("Selected combination is not possible as two of the years has 0 students.");
           
        }
        
        else {
        if (select == 1) {
            printClassRoomLayout1(maxFirstYear, maxSecondYear);
            calc_each_students(firstYearCount,secondYearCount,thirdYearCount,fourthYearCount);
        } else if (select == 2) {
            printClassRoomLayout2(maxFirstYear, maxThirdYear);
        } else if (select == 3) {
            printClassRoomLayout3(maxFirstYear, maxFourthYear);
        } else if (select == 4) {
            printClassRoomLayout4(maxSecondYear, maxThirdYear);
        } else if (select == 5) {
            printClassRoomLayout5(maxSecondYear, maxFourthYear);
        } else if (select == 6) {
            printClassRoomLayout6(maxThirdYear, maxFourthYear);
        }
        }
    }
    
    private void  calc_each_students(int y1,int y2,int y3,int y4)
    {
    	
    	
    }
    private void printClassRoomLayout1(int maxFirstYear, int maxSecondYear) {
        int[][] layout = new int[8][5];

        firstYearCount = 0;
        secondYearCount = 0;
        thirdYearCount = 0;
        fourthYearCount = 0;
        for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 5; j++) {
            	   
            	 
                   if ( i % 2 == 0) {
                	   
                	   if(j%2==0 && fy>0)
                	   {
                		   layout[i][j] = firstYear++;
                		   firstYearCount++;
                		   fy--;
                		 
                		  
	                      
                	   }
                	   else if(fy<=0 && j%2==0 )
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		 
                		   layout[i][j]=secondYear++;
                		   secondYearCount++;
                		   sy--;
                		   
                	   }
                   }
                   else
                   {
                	   if(j%2!=0 && fy>0)
                	   {
                		  layout[i][j]=firstYear++;
                		  firstYearCount++;
                		 
                		  fy--;
                	   
                	   }
                	   else if(fy<=0 && j%2!=0)
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		   
                		   layout[i][j]=secondYear++;
                		   secondYearCount++;
                		   sy--;
                	   
                		   
                	   }
            	   }
            	
            	 
               }
           }

        map.put("year1 in class room" + classrooms, firstYearCount);
        map.put("year2 in class room" + classrooms, secondYearCount);
        classrooms++;

        printLayout(layout);
    }

    private void printClassRoomLayout2(int maxFirstYear, int maxThirdYear) {
    	 int[][] layout = new int[8][5];

	        firstYearCount = 0;
	        thirdYearCount = 0;
	        secondYearCount = 0;
	        fourthYearCount = 0;
	        for (int i = 0; i < 8; i++) {
	               for (int j = 0; j < 5; j++) {
	            	   
	            	 
	                   if ( i % 2 == 0) {
	                	   
	                	   if(j%2==0 && fy>0)
	                	   {
	                		   layout[i][j] = firstYear++;
	                		   firstYearCount++;
	                		   fy--;
	                		 
	                		  
		                      
	                	   }
	                	   else if(fy<=0 && j%2==0 )
	                	   {
	                		   layout[i][j]=0;
	                	   }
	                	   else 
	                	   {
	                		 
	                		   layout[i][j]=thirdYear++;
	                		   thirdYearCount++;
	                		   ty--;
	                		   
	                	   }
	                   }
	                   else
	                   {
	                	   if(j%2!=0 && fy>0)
	                	   {
	                		  layout[i][j]=firstYear++;
	                		  firstYearCount++;
	                		 
	                		  fy--;
	                	   
	                	   }
	                	   else if(fy<=0 && j%2!=0)
	                	   {
	                		   layout[i][j]=0;
	                	   }
	                	   else 
	                	   {
	                		   
	                		   layout[i][j]=thirdYear++;
	                		   thirdYearCount++;
	                		   ty--;
	                	   
	                		   
	                	   }
	            	   }
	            	
	            	 
	               }
	           }

	        map.put("year1 in class room" + classrooms, firstYearCount);
	        map.put("year2 in class room" + classrooms, thirdYearCount);
	        classrooms++;

	        printLayout(layout);
	        System.out.println("---------------------------------------------------------------------");

        
    }

    private void printClassRoomLayout3(int maxFirstYear, int maxFourthYear) {
        int[][] layout = new int[8][5];

        firstYearCount = 0;
        secondYearCount=0;
       
        thirdYearCount = 0;
        fourthYearCount = 0;
        for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 5; j++) {
            	   
            	 
                   if ( i % 2 == 0) {
                	   
                	   if(j%2==0 && fy>0)
                	   {
                		   layout[i][j] = firstYear++;
                		   firstYearCount++;
                		   fy--;
                		 
                		  
                		   
                	   }
                	   else if(fy<=0 && j%2==0 )
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		 
                		   layout[i][j]=fourthYear++;
                		   fourthYearCount++;
                		   fyy--;
                		   
                	   }
                   }
                   else
                   {
                	   if(j%2!=0 && fy>0)
                	   {
                		  layout[i][j]=firstYear++;
                		  firstYearCount++;
                		 
                		  fy--;
                	   
                	   }
                	   else if(fy<=0 && j%2!=0)
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		   
                		   layout[i][j]=fourthYear++;
                		   fourthYearCount++;
                		   fyy--;
                	   
                		   
                	   }
            	   }
            	
            	 
               }
           }

        map.put("year1 in class room" + classrooms, firstYearCount);
        map.put("year2 in class room" + classrooms, fourthYearCount);
        classrooms++;

        printLayout(layout);
        System.out.println("---------------------------------------------------------------------");
    }

    private void printClassRoomLayout4(int maxSecondYear, int maxThirdYear) {
        int[][] layout = new int[8][5];

        secondYearCount = 0;
        firstYearCount = 0;
        fourthYearCount = 0;
        thirdYearCount = 0;
        for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 5; j++) {
            	   
            	 
                   if ( i % 2 == 0) {
                	   
                	   if(j%2==0 && sy>0)
                	   {
                		   layout[i][j] = secondYear++;
                		   secondYearCount++;
                		   sy--;
                		 
                		  
	                      
                	   }
                	   else if(sy<=0 && j%2==0 )
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		 
                		   layout[i][j]=thirdYear++;
                		   thirdYearCount++;
                		   ty--;
                		   
                	   }
                   }
                   else
                   {
                	   if(j%2!=0 && sy>0)
                	   {
                		  layout[i][j]=secondYear++;
                		  secondYearCount++;
                		  sy--;
                	   
                	   }
                	   else if(sy<=0 && j%2!=0)
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		   
                		   layout[i][j]=thirdYear++;
                		   thirdYearCount++;
                		   ty--;
                	   
                		   
                	   }
            	   }
            	
            	 
               }
           }

        map.put("year2 in class room" + classrooms, secondYearCount);
        map.put("year3 in class room" + classrooms, thirdYearCount);
        classrooms++;

        printLayout(layout);
        System.out.println("---------------------------------------------------------------------");
    }

    private void printClassRoomLayout5(int maxSecondYear, int maxFourthYear) {
        int[][] layout = new int[8][5];

        secondYearCount = 0;
        fourthYearCount = 0;
        firstYearCount = 0;
        thirdYearCount = 0;
        for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 5; j++) {
            	   
            	 
                   if ( i % 2 == 0) {
                	   
                	   if(j%2==0 && sy>0)
                	   {
                		   layout[i][j] = secondYear++;
                		   secondYearCount++;
                		   sy--;
                		 
                		  
	                      
                	   }
                	   else if(sy<=0 && j%2==0 )
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		 
                		   layout[i][j]=fourthYear++;
                		   fourthYearCount++;
                		   fyy--;
                		   
                	   }
                   }
                   else
                   {
                	   if(j%2!=0 && sy>0)
                	   {
                		  layout[i][j]=secondYear++;
                		  secondYearCount++;
                		 
                		  sy--;
                	   
                	   }
                	   else if(sy<=0 && j%2!=0)
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		   
                		   layout[i][j]=fourthYear++;
                		   fourthYearCount++;
                		   fyy--;
                	   
                		   
                	   }
            	   }
            	
            	 
               }
           }
        

        map.put("year2 in class room" + classrooms, secondYearCount);
        map.put("year4 in class room" + classrooms, fourthYearCount);
        classrooms++;

        printLayout(layout);
        System.out.println("---------------------------------------------------------------------");
    }

    private void printClassRoomLayout6(int maxThirdYear, int maxFourthYear) {
        int[][] layout = new int[8][5];

        secondYearCount=0;
        thirdYearCount = 0;
        fourthYearCount = 0;
        firstYearCount = 0;
        for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 5; j++) {
            	   
            	 
                   if ( i % 2 == 0) {
                	   
                	   if(j%2==0 && ty>0)
                	   {
                		   layout[i][j] = thirdYear++;
                		   thirdYearCount++;
                		   ty--;
                		 
                		  
	                      
                	   }
                	   else if(ty<=0 && j%2==0 )
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		 
                		   layout[i][j]=fourthYear++;
                		   fourthYearCount++;
                		   fyy--;
                		   
                	   }
                   }
                   else
                   {
                	   if(j%2!=0 && ty>0)
                	   {
                		  layout[i][j]=thirdYear++;
                		  thirdYearCount ++;
                		 
                		  ty--;
                	   
                	   }
                	   else if(ty<=0 && j%2!=0)
                	   {
                		   layout[i][j]=0;
                	   }
                	   else 
                	   {
                		   
                		   layout[i][j]=fourthYear++;
                		   fourthYearCount++;
                		   fyy--;
                	   
                		   
                	   }
            	   }
            	
            	 
               }
           }

        map.put("year3 in class room" + classrooms, thirdYearCount);
        map.put("year4 in class room" + classrooms, fourthYearCount);
        classrooms++;

        printLayout(layout);
        System.out.println("---------------------------------------------------------------------");
        
    }

    private void printLayout(int[][] layout) {
        System.out.println("Seating arrangement:");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-7s %-7s %-7s %-7s %-7s\n", "Col1  |", "col2  |", "col3   |", "col4  |","col5");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < 8; i++) {
        	 
            for (int j = 0; j < 5; j++) {
                System.out.print(layout[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void displayRemainingStudents() {
  

       
     
        if(firstYearStudents - firstYearCount<=0)
        {
        	System.out.println("Year 1: " + 0 );
        	
        	
        }
        else
        {
            System.out.println("Year 1: " + (firstYearStudents - firstYearCount));
        }
        if(secondYearStudents - secondYearCount<=0)
        {
        	System.out.println("Year 2: " + 0 );
        	
        	
        }
        else
        {
        	  System.out.println("Year 2: " + (secondYearStudents - secondYearCount));
        }
        if(thirdYearStudents - thirdYearCount<=0)
        {
        	System.out.println("Year 3: " + 0 );
        	
        	
        }
        else
        {
        	  System.out.println("Year 3: " + (thirdYearStudents - thirdYearCount));
        }
        if(fourthYearStudents - fourthYearCount<=0)
        {
        	System.out.println("Year 4: " + 0 );
        	
        	
        }
        else
        {
        	  System.out.println("Year 4: " + (fourthYearStudents - fourthYearCount));
        }
        System.out.println("---------------------------------------------------------------------");

        firstYearStudents = firstYearStudents - firstYearCount;
        secondYearStudents = secondYearStudents - secondYearCount;
        thirdYearStudents = thirdYearStudents - thirdYearCount;
        fourthYearStudents = fourthYearStudents - fourthYearCount;
        System.out.println("---------------------------------------------------------------------");

       
    }
    
}



