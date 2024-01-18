package dif_seating;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TheatreSeatingGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel seatPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TheatreSeatingGUI frame = new TheatreSeatingGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TheatreSeatingGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 713, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JButton btnAllocateSeats = new JButton("Allocate Seats");
        contentPane.add(btnAllocateSeats, BorderLayout.NORTH);

        seatPanel = new JPanel(new GridLayout(20, 30, 5, 5));
        contentPane.add(new JScrollPane(seatPanel), BorderLayout.CENTER);

        btnAllocateSeats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSeating();
            }
        });
    }

    private void handleSeating() {
        int totalSeats = getTotalSeats();
        if (totalSeats <= 0) {
            return; 
        }

        String seatType = getSeatType();
        String[][] seatingArrangement = initializeSeating(20, 30);
        markSeatsAsX(seatingArrangement, totalSeats);

        displaySeatingArrangement(seatingArrangement, seatType);
    }

    private int getTotalSeats() {
        String totalSeatsStr = JOptionPane.showInputDialog(null,
                "Enter the total number of seats:", "Total Seats", JOptionPane.PLAIN_MESSAGE);

        try {
            return Integer.parseInt(totalSeatsStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private static String[][] initializeSeating(int rows, int seatsPerRow) {
        String[][] seatingArrangement = new String[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatingArrangement[i][j] = "_";
            }
        }
        return seatingArrangement;
    }

    private void markSeatsAsX(String[][] seatingArrangement, int totalTickets) {
        int ticketsRemaining = totalTickets;
        for (int i = 0; i < seatingArrangement.length && ticketsRemaining > 0; i++) {
            for (int j = 0; j < seatingArrangement[i].length && ticketsRemaining > 0; j++) {
                if (seatingArrangement[i][j].equals("_")) {
                    seatingArrangement[i][j] = "X";
                    ticketsRemaining--;
                }
            }
        }
    }

    private void displaySeatingArrangement(String[][] seatingArrangement, String seatType) {
        seatPanel.removeAll();

        for (int i = 0; i < seatingArrangement.length; i++) {
            for (int j = 0; j < seatingArrangement[i].length; j++) {
                JButton seatButton = new JButton(seatingArrangement[i][j]);
                customizeSeatButton(seatButton, seatType);
                seatPanel.add(seatButton);
            }
        }

        seatPanel.revalidate();
        seatPanel.repaint();
    }

    private void customizeSeatButton(JButton seatButton, String seatType) {
        seatButton.setPreferredSize(new Dimension(30, 30));
        seatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });

        // Customize button color based on seat type
        if (isSeatOfType(seatButton, seatType)) {
            seatButton.setBackground(Color.gray);  
        } else {
            seatButton.setBackground(Color.LIGHT_GRAY);  
        }
    }

    private static String getSeatType() {
        while (true) {
            String seatType = JOptionPane.showInputDialog(null,
                    "Enter the type of seat:\n1. Lower\n2. Upper\n3. Mix",
                    "Seat Type", JOptionPane.PLAIN_MESSAGE);

            if (seatType != null && (seatType.equals("1") || seatType.equals("2") || seatType.equals("3"))) {
                return (seatType.equals("1")) ? "lower" : ((seatType.equals("2")) ? "upper" : "mix");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1, 2, or 3.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static boolean isSeatOfType(JButton seatButton, String seatType) {
        return true;
    }
}
