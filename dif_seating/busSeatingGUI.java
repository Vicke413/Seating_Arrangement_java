
package dif_seating;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class busSeatingGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel seatPanel;
    private JTextField seatsField;
    private JComboBox<String> seatTypeComboBox;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    busSeatingGUI frame = new busSeatingGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public busSeatingGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 713, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        
        JPanel headingPanel = new JPanel();
        contentPane.add(headingPanel, BorderLayout.NORTH);

        
        JLabel headingLabel = new JLabel("Bus Seating Arrangement");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingPanel.add(headingLabel);

        
        contentPane.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.CENTER);

        seatPanel = new JPanel(new GridLayout(10, 4, 5, 5));
        contentPane.add(new JScrollPane(seatPanel), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        contentPane.add(inputPanel, BorderLayout.SOUTH);
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblEnterSeats = new JLabel("Enter the number of seats:");
        inputPanel.add(lblEnterSeats);

        seatsField = new JTextField();
        inputPanel.add(seatsField);
        seatsField.setColumns(10);

        JLabel lblSelectSeatType = new JLabel("Select seat type:");
        inputPanel.add(lblSelectSeatType);

        seatTypeComboBox = new JComboBox<>(new String[]{"Window", "Aisle", "Mix"});
        inputPanel.add(seatTypeComboBox);

        JButton btnAllocateSeats = new JButton("Allocate Seats");
        inputPanel.add(btnAllocateSeats);

        btnAllocateSeats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSeating();
            }
        });

        
        initializeSeating(10, 4);
    }

    private void handleSeating() {
        try {
            String seatsText = seatsField.getText().trim();
            if (seatsText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the number of seats.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int totalSeats = Integer.parseInt(seatsText);

            if (totalSeats > 40) {
                throw new IllegalArgumentException("Maximum allowed seats is 40.");
            }

            String seatType = (String) seatTypeComboBox.getSelectedItem();

            initializeSeating(10, 4);

            switch (seatType) {
                case "Window":
                    if (totalSeats > 20) {
                        throw new IllegalArgumentException("Maximum allowed seats for Window type is 20.");
                    }
                    markSeatsAsX(totalSeats, 1);
                    break;
                case "Aisle":
                    if (totalSeats > 20) {
                        throw new IllegalArgumentException("Maximum allowed seats for Aisle type is 20.");
                    }
                    markSeatsAsX(totalSeats, 2);
                    break;
                case "Mix":
                    markSeatsAsX(totalSeats, 3);
                    break;
                default:
                    System.out.println("Invalid seat type.");
                    return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the seats.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeSeating(int rows, int seatsPerRow) {
        seatPanel.removeAll();
        seatPanel.setLayout(new GridLayout(rows, seatsPerRow, 5, 5));
        Color blue = new Color(173, 216, 230);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                JButton seatButton = new JButton(" ");
                seatButton.setBackground(blue);
                seatButton.setPreferredSize(new Dimension(20, 20));
                seatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                seatPanel.add(seatButton);
            }
        }

        seatPanel.revalidate();
        seatPanel.repaint();
    }

    private void markSeatsAsX(int totalTickets, int preference) {
        Color coral = new Color(240, 128, 128);
        Component[] components = seatPanel.getComponents();
        int ticketsRemaining = totalTickets;

        for (Component component : components) {
            JButton seatButton = (JButton) component;
            if (seatButton.getText().equals(" ") && isSeatOfPreference(seatButton, preference) && ticketsRemaining > 0) {
                seatButton.setText("X");
                seatButton.setBackground(coral);
                ticketsRemaining--;
            }
        }
    }

    private boolean isSeatOfPreference(JButton seatButton, int preference) {
        String buttonText = seatButton.getText();
        int col = seatButton.getParent().getComponentZOrder(seatButton) % 4;

        if (preference == 1) {
            return col == 0 || col == 3;
        } else if (preference == 2) {
            return col == 1 || col == 2;
        } else {
            return true;
        }
    }
}
