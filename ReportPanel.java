package parking_system;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

import parking_system.MainFrame;

public class ReportPanel extends JPanel {

    private JTextArea historyArea;
    private JTextArea queueArea;
    private JTextArea stackArea;

    public ReportPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JButton refreshBtn = new JButton("🔄 Refresh All Reports");
        refreshBtn.setBackground(new Color(50, 100, 200));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFocusPainted(false);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.add(refreshBtn);

        // Three panels side by side
        JPanel panels = new JPanel(new GridLayout(1, 3, 10, 10));

        historyArea = makeTextArea();
        queueArea   = makeTextArea();
        stackArea   = makeTextArea();

        JScrollPane s1 = new JScrollPane(historyArea);
        JScrollPane s2 = new JScrollPane(queueArea);
        JScrollPane s3 = new JScrollPane(stackArea);

        s1.setBorder(new TitledBorder("📜 Exit History (Linked List)"));
        s2.setBorder(new TitledBorder("⏳ Waiting Queue"));
        s3.setBorder(new TitledBorder("↩  Action Stack"));

        panels.add(s1);
        panels.add(s2);
        panels.add(s3);

        refreshBtn.addActionListener(e -> refreshAll());
        refreshAll();

        add(top,    BorderLayout.NORTH);
        add(panels, BorderLayout.CENTER);
    }

    private JTextArea makeTextArea() {
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        return ta;
    }

    private void refreshAll() {
        // History (Linked List)
        List<String> history = MainFrame.historyList.getHistoryList();
        if (history.isEmpty()) {
            historyArea.setText("No exit history yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : history) sb.append(s).append("\n");
            historyArea.setText(sb.toString());
        }

        // Waiting Queue
        Vehicle[] waiting = MainFrame.waitingQueue.getAllWaiting();
        if (waiting.length == 0) {
            queueArea.setText("No vehicles waiting.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < waiting.length; i++)
                sb.append((i + 1) + ". " + waiting[i].toString()).append("\n");
            queueArea.setText(sb.toString());
        }

        // Action Stack
        String[] actions = MainFrame.actionStack.getAllActions();
        if (actions.length == 0) {
            stackArea.setText("No actions recorded.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = actions.length - 1; i >= 0; i--)
                sb.append(actions[i]).append("\n");
            stackArea.setText(sb.toString());
        }
    }
}
