package parking_system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;


public class SearchPanel extends JPanel {

    private JTextField searchField;
    private JTextArea  resultArea;

    public SearchPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // ── Search Bar ────────────────────────────────────
        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchBar.setBorder(new TitledBorder("Search Vehicle (BST)"));

        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton searchBtn = new JButton("🔍 Search");
        JButton allBtn    = new JButton("📋 Show All Slots");
        JButton pathBtn   = new JButton("🗺  Show Path (Graph)");

        searchBtn.setBackground(new Color(0, 102, 204));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);

        allBtn.setBackground(new Color(100, 160, 100));
        allBtn.setForeground(Color.WHITE);
        allBtn.setFocusPainted(false);

        pathBtn.setBackground(new Color(180, 100, 20));
        pathBtn.setForeground(Color.WHITE);
        pathBtn.setFocusPainted(false);

        searchBar.add(new JLabel("Vehicle Number:"));
        searchBar.add(searchField);
        searchBar.add(searchBtn);
        searchBar.add(allBtn);
        searchBar.add(pathBtn);

        // ── Result Area ───────────────────────────────────
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(new TitledBorder("Results"));

        searchBtn.addActionListener(e -> searchVehicle());
        allBtn.addActionListener(e    -> showAllSlots());
        pathBtn.addActionListener(e   -> showGraphPath());

        // Enter key triggers search
        searchField.addActionListener(e -> searchVehicle());

        add(searchBar, BorderLayout.NORTH);
        add(scroll,    BorderLayout.CENTER);
    }

    private void searchVehicle() {
        String num = searchField.getText().trim().toUpperCase();
        if (num.isEmpty()) {
            resultArea.setText("Please enter a vehicle number.");
            return;
        }

        // BST Search — O(log n)
        Vehicle v = MainFrame.vehicleBST.search(num);
        if (v == null) {
            resultArea.setText("❌ Vehicle [" + num + "] not found in parking lot.\n"
                + "(BST search returned null — vehicle not currently parked)");
            return;
        }

        // Find which slot
        int slotNum = -1;
        for (int i = 0; i < MainFrame.slotArray.getTotalSlots(); i++) {
            if (MainFrame.slotArray.getSlot(i).isOccupied() &&
                MainFrame.slotArray.getSlot(i).getVehicle()
                         .getVehicleNumber().equals(num)) {
                slotNum = i + 1;
                break;
            }
        }

        resultArea.setText(
            "✅ VEHICLE FOUND (via BST Search)\n" +
            "─────────────────────────────────\n" +
            "  Vehicle Number : " + v.getVehicleNumber() + "\n" +
            "  Owner Name     : " + v.getOwnerName()     + "\n" +
            "  Vehicle Type   : " + v.getVehicleType()   + "\n" +
            "  Parked at Slot : " + slotNum              + "\n" +
            "─────────────────────────────────\n" +
            "  BST Search = O(log n) — fast!"
        );
    }

    private void showAllSlots() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALL PARKING SLOTS\n");
        sb.append("═══════════════════════════════\n");
        for (int i = 0; i < MainFrame.slotArray.getTotalSlots(); i++) {
            sb.append(MainFrame.slotArray.getSlot(i).toString()).append("\n");
        }
        sb.append("═══════════════════════════════\n");
        sb.append("Free: ").append(MainFrame.slotArray.countFree());
        sb.append("   Occupied: ").append(MainFrame.slotArray.countOccupied());
        resultArea.setText(sb.toString());
    }

    private void showGraphPath() {
        // Show BFS shortest path from Entry(0) to Exit(4)
        List<Integer> path = MainFrame.parkingGraph.shortestPath(0, 4);
        StringBuilder sb = new StringBuilder();
        sb.append("PARKING LOT MAP — Graph BFS Shortest Path\n");
        sb.append("══════════════════════════════════════════\n");
        sb.append("Zones: Entry → Zone A → Zone B → Zone C → Exit\n\n");
        sb.append("Connections (Edges):\n");
        sb.append("  Entry  ↔ Zone A\n");
        sb.append("  Entry  ↔ Zone B\n");
        sb.append("  Zone A ↔ Zone C\n");
        sb.append("  Zone B ↔ Zone C\n");
        sb.append("  Zone C ↔ Exit\n\n");
        sb.append("Shortest Path (Entry → Exit):\n  ");
        for (int i = 0; i < path.size(); i++) {
            sb.append(MainFrame.parkingGraph.getZoneName(path.get(i)));
            if (i < path.size() - 1) sb.append(" → ");
        }
        sb.append("\n\nAlgorithm: BFS (Breadth-First Search)\n");
        sb.append("Time Complexity: O(V + E)");
        resultArea.setText(sb.toString());
    }
}