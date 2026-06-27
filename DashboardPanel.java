package parking_system;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import parking_system.MainFrame;
import parking_system.ParkingSlot;

public class DashboardPanel extends JPanel {

    private JLabel[]   slotLabels;
    private JLabel     freeLabel, occupiedLabel;
    private JButton    refreshBtn;

    public DashboardPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Summary bar at top
        JPanel summary = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        summary.setBackground(new Color(240, 240, 240));
        summary.setBorder(new TitledBorder("Summary"));

        freeLabel     = new JLabel();
        occupiedLabel = new JLabel();
        refreshBtn    = new JButton("🔄 Refresh");

        freeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        occupiedLabel.setFont(new Font("Arial", Font.BOLD, 14));
        freeLabel.setForeground(new Color(0, 150, 0));
        occupiedLabel.setForeground(new Color(200, 0, 0));

        summary.add(new JLabel("Total: 20"));
        summary.add(freeLabel);
        summary.add(occupiedLabel);
        summary.add(refreshBtn);

        // Slot grid
        JPanel grid = new JPanel(new GridLayout(4, 5, 10, 10));
        grid.setBorder(new TitledBorder("Parking Slots (Green=Free, Red=Occupied)"));
        slotLabels = new JLabel[20];

        for (int i = 0; i < 20; i++) {
            slotLabels[i] = new JLabel("Slot " + (i + 1), JLabel.CENTER);
            slotLabels[i].setOpaque(true);
            slotLabels[i].setFont(new Font("Arial", Font.BOLD, 12));
            slotLabels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            slotLabels[i].setPreferredSize(new Dimension(100, 80));
            grid.add(slotLabels[i]);
        }

        refreshBtn.addActionListener(e -> refreshSlots());
        refreshSlots(); // Initial load

        // Legend
        JPanel legend = new JPanel(new FlowLayout());
        JLabel g = new JLabel("  FREE  ");
        JLabel r = new JLabel("  OCCUPIED  ");
        g.setOpaque(true); g.setBackground(new Color(144, 238, 144));
        r.setOpaque(true); r.setBackground(new Color(255, 99, 99));
        legend.add(new JLabel("Legend: "));
        legend.add(g); legend.add(r);

        add(summary, BorderLayout.NORTH);
        add(grid,    BorderLayout.CENTER);
        add(legend,  BorderLayout.SOUTH);
    }

    public void refreshSlots() {
        ParkingSlot[] slots = MainFrame.SlotArray.getAllSlots();
        for (int i = 0; i < 20; i++) {
            if (slots[i].isOccupied()) {
                slotLabels[i].setText("<html><center>Slot " + (i + 1) +
                    "<br><font size=2>" + slots[i].getVehicle().getvehicleNumber() +
                    "</font></center></html>");
                slotLabels[i].setBackground(new Color(255, 99, 99));
                slotLabels[i].setForeground(Color.WHITE);
            } else {
                slotLabels[i].setText("<html><center>Slot " + (i + 1) +
                    "<br><font size=2>FREE</font></center></html>");
                slotLabels[i].setBackground(new Color(144, 238, 144));
                slotLabels[i].setForeground(Color.BLACK);
            }
        }
        freeLabel.setText("Free: "     + MainFrame.slotArray.countFree());
        occupiedLabel.setText("Occupied: " + MainFrame.slotArray.countOccupied());
    }
}