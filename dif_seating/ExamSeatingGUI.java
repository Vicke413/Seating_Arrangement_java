package dif_seating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ExamSeatingGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField totalStudentsField;
    private JTextField year1Field;
    private JTextField year2Field;
    private JTextField year3Field;
    private JTextField year4Field;
    private JPanel resultPanel;
    private JButton[][] buttons;
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

    private int totalStudents;
    private HashMap<Integer, Integer> yearStudents = new HashMap<>();
    private HashMap<Integer, Color> yearColors = new HashMap<>();
    private int[][] seatingLayout;
    private int currentRollNumber = 1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExamSeatingGUI frame = new ExamSeatingGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ExamSeatingGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));

        JLabel totalStudentsLabel = new JLabel("Total Students:");
        totalStudentsField = new JTextField();
        JLabel year1Label = new JLabel("Year 1 Students:");
        year1Field = new JTextField();
        JLabel year2Label = new JLabel("Year 2 Students:");
        year2Field = new JTextField();
        JLabel year3Label = new JLabel("Year 3 Students:");
        year3Field = new JTextField();
        JLabel year4Label = new JLabel("Year 4 Students:");
        year4Field = new JTextField();
        JButton generateButton = new JButton("Generate Seating");
        resultPanel = new JPanel(new GridLayout(1, 1));

        inputPanel.add(totalStudentsLabel);
        inputPanel.add(totalStudentsField);
        inputPanel.add(year1Label);
        inputPanel.add(year1Field);
        inputPanel.add(year2Label);
        inputPanel.add(year2Field);
        inputPanel.add(year3Label);
        inputPanel.add(year3Field);
        inputPanel.add(year4Label);
        inputPanel.add(year4Field);
        inputPanel.add(new JLabel());
        inputPanel.add(generateButton);

        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(resultPanel, BorderLayout.CENTER);

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSeating();
            }
        });
    }

    private void handleSeating() {
        totalStudents = Integer.parseInt(totalStudentsField.getText());
        int year1Students = Integer.parseInt(year1Field.getText());
        int year2Students = Integer.parseInt(year2Field.getText());
        int year3Students = Integer.parseInt(year3Field.getText());
        int year4Students = Integer.parseInt(year4Field.getText());

        setYearStudents(1, year1Students);
        setYearStudents(2, year2Students);
        setYearStudents(3, year3Students);
        setYearStudents(4, year4Students);

        
        setYearColor(1, Color.RED);
        setYearColor(2, Color.BLUE);
        setYearColor(3, Color.GREEN);
        setYearColor(4, Color.YELLOW);

        calculateSeatingLayout();
        
       
    }

    private void setYearStudents(int year, int students) {
        yearStudents.put(year, students);
    }

    private void setYearColor(int year, Color color) {
        yearColors.put(year, color);
    }

    private void calculateSeatingLayout() {
        int totalRooms = (int) Math.ceil((double) totalStudents / 40);
        seatingLayout = new int[totalRooms][8 * 5];

        for (int roomNumber = 0; roomNumber < totalRooms; roomNumber++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 5; j++) {
                    int year = (roomNumber * 40 + i * 5 + j) % 4 + 1;
                    int maxStudents = Math.min(yearStudents.get(year), 40);

                    if (maxStudents > 0) {
                        seatingLayout[roomNumber][i * 5 + j] = currentRollNumber;
                        currentRollNumber++;
                        yearStudents.put(year, yearStudents.get(year) - 1);
                    }
                }
            }
        }
        
        
    }
    private void printClassRoomLayout1(int maxFirstYear, int maxSecondYear) {
        int[][] layout = new int[8][5];

       int firstYearCount = 0;
       int secondYearCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0 && fy > 0) {
                        layout[i][j] = firstYear++;
                        firstYearCount++;
                        fy--;
                    } else if (fy <= 0 && j % 2 == 0) {
                        layout[i][j] = 0;
                    } else {
                        layout[i][j] = secondYear++;
                        secondYearCount++;
                        sy--;
                    }
                } else {
                    if (j % 2 != 0 && fy > 0) {
                        layout[i][j] = firstYear++;
                        firstYearCount++;
                        fy--;
                    } else if (sy <= 0 && j % 2 != 0) {
                        layout[i][j] = 0;
                    } else {
                        layout[i][j] = secondYear++;
                        secondYearCount++;
                        sy--;
                    }
                }
            }
        }

   
        classrooms++;

        printLayout(layout);
    }
    
   
    private int[] getYearsForCombination(int combination) {
        int[] years = new int[2];
        switch (combination) {
            case 1:
                years[0] = 1;
                years[1] = 2;
                break;
            case 2:
                years[0] = 1;
                years[1] = 3;
                break;
            case 3:
                years[0] = 1;
                years[1] = 4;
                break;
            case 4:
                years[0] = 2;
                years[1] = 3;
                break;
            case 5:
                years[0] = 2;
                years[1] = 4;
                break;
            case 6:
                years[0] = 3;
                years[1] = 4;
                break;
        }
        return years;
    }
    private Color getColorForYear(int year) {
        
        switch (year) {
            case 0:
                return Color.WHITE; 
            case 1:
                return Color.RED;   
            case 2:
                return Color.GREEN; 
            case 3:
                return Color.BLUE;  
            case 4:
                return Color.YELLOW; 
            default:
                return Color.WHITE; 
        }
    }


    private void printLayout(int[][] layout) {
        buttons = new JButton[8][5];

        
        JPanel panel = new JPanel(new GridLayout(8, 5));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new JButton(Integer.toString(layout[i][j]));
                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                buttons[i][j].setBackground(getColorForYear(layout[i][j]));
                panel.add(buttons[i][j]);
            }
        }

        
        JFrame frame = new JFrame("Seating Arrangement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

}
