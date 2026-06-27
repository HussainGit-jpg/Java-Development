package parking_system;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


public class EntryPanel extends JPanel {

    private JTextField numField, nameField;
    private JComboBox<String> typeBox;
    private JTextArea  logArea;

    public EntryPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // ── Form ──────────────────────────────────────────
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new TitledBorder("Vehicle Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        numField  = new JTextField(15);
        nameField = new JTextField(15);
        typeBox   = new JComboBox<>(new String[]{"Car", "Bike", "Truck"});

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Vehicle Number:"), gbc);
        gbc.gridx = 1;
        form.add(numField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Owner Name:"), gbc);
        gbc.gridx = 1;
        form.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Vehicle Type:"), gbc);
        gbc.gridx = 1;
        form.add(typeBox, gbc);

        // ── Buttons ───────────────────────────────────────
        JButton parkBtn   = makeButton("🚗 Park Vehicle",   new Color(34, 139, 34));
        JButton removeBtn = makeButton("❌ Remove Vehicle",  new Color(200, 50, 50));
        JButton undoBtn   = makeButton("↩  Undo Action",    new Color(100, 100, 180));
        JButton clearBtn  = makeButton("🗑  Clear Log",      new Color(120, 120, 120));

        gbc.gridx = 0; gbc.gridy = 3;
        form.add(parkBtn,   gbc);
        gbc.gridx = 1;
        form.add(removeBtn, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        form.add(undoBtn,   gbc);
        gbc.gridx = 1;
        form.add(clearBtn,  gbc);

        // ── Log Area ──────────────────────────────────────
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        logArea.setBackground(new Color(30, 30, 30));
        logArea.setForeground(new Color(0, 255, 100));
        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setBorder(new TitledBorder("Activity Log"));

        // ── Wire Buttons ──────────────────────────────────
        parkBtn.addActionListener(e   -> parkVehicle());
        removeBtn.addActionListener(e -> removeVehicle());
        undoBtn.addActionListener(e   -> undoAction());
        clearBtn.addActionListener(e  -> logArea.setText(""));

        add(form,   BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private JButton makeButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        return btn;
    }

    private void parkVehicle() {
        String num  = numField.getText().trim().toUpperCase();
        String name = nameField.getText().trim();
        String type = (String) typeBox.getSelectedItem();

        if (num.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check duplicate
        if (MainFrame.vehicleBST.search(num) != null) {
            log("⚠  Vehicle " + num + " is already parked!");
            return;
        }

        Vehicle v       = new Vehicle(num, name, type);
        int     freeIdx = MainFrame.slotArray.findFreeSlot();

        if (freeIdx == -1) {
            boolean added = MainFrame.waitingQueue.Enque(v);
            if (added)
                log("🔴 Lot FULL! " + num + " added to waiting queue (position "
                    + MainFrame.waitingQueue.getSize() + ")");
            else
                log("🔴 Lot FULL and queue is also full! Cannot park " + num);
            return;
        }

        MainFrame.slotArray.parkVehicle(freeIdx, v);
        MainFrame.vehicleBST.insert(v);
        MainFrame.actionStack.push("PARKED|" + num + "|" + freeIdx);
        log("✅ " + type + " [" + num + "] owned by " + name +
            " → Slot " + (freeIdx + 1));

        numField.setText("");
        nameField.setText("");
    }

    private void removeVehicle() {
        String num = JOptionPane.showInputDialog(this,
            "Enter Vehicle Number to Remove:", "Remove Vehicle",
            JOptionPane.QUESTION_MESSAGE);
        if (num == null || num.trim().isEmpty()) return;
        num = num.trim().toUpperCase();

        int totalSlots = MainFrame.slotArray.getTotalSlots();
        for (int i = 0; i < totalSlots; i++) {
            if (MainFrame.slotArray.getSlot(i).isOccupied() &&
                MainFrame.slotArray.getSlot(i).getVehicle()
                         .getVehicleNumber().equals(num)) {

                Vehicle exiting = MainFrame.slotArray.getSlot(i).getVehicle();

                // Save to history (Linked List)
                MainFrame.historyList.addRecord(exiting);
                // Remove from BST
                MainFrame.vehicleBST.delete(num);
                // Free slot (Array)
                MainFrame.slotArray.removeVehicle(i);
                // Save action (Stack)
                MainFrame.actionStack.push("REMOVED|" + num + "|" + i);

                log("🚪 " + num + " removed from Slot " + (i + 1) +
                    ". Saved to history.");

                // Check waiting queue
                if (!MainFrame.waitingQueue.isEmpty()) {
                    Vehicle waiting = MainFrame.waitingQueue.dequeue();
                    MainFrame.slotArray.parkVehicle(i, waiting);
                    MainFrame.vehicleBST.insert(waiting);
                    MainFrame.actionStack.push("PARKED|" +
                        waiting.getVehicleNumber() + "|" + i);
                    log("🔄 Waiting vehicle [" + waiting.getVehicleNumber() +
                        "] auto-assigned to Slot " + (i + 1));
                }
                return;
            }
        }
        log("❌ Vehicle " + num + " not found in parking lot.");
    }

    private void undoAction() {
        String last = MainFrame.actionStack.pop();
        if (last == null) { log("ℹ  Nothing to undo."); return; }
        String[] parts = last.split("\\|");
        log("↩  Undid action: " + parts[0] + " on vehicle " + parts[1] +
            " (manual re-correction needed for slot " + parts[2] + ")");
    }

    private void log(String msg) {
        logArea.append(msg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}