
package University_Management_Syaytem.java;
import javax.swing.*;
import java.util.*;
public class GraphModule extends JFrame {

    HashMap<String, ArrayList<String>> graph = new HashMap<>();

    JTextField student1Field;
    JTextField student2Field;

    JTextArea displayArea;

    public GraphModule() {

        setTitle("Student Network Graph");
        setSize(700, 500);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbl1 = new JLabel("Student 1:");
        lbl1.setBounds(20, 20, 100, 25);
        add(lbl1);

        student1Field = new JTextField();
        student1Field.setBounds(120, 20, 150, 25);
        add(student1Field);

        JLabel lbl2 = new JLabel("Student 2:");
        lbl2.setBounds(20, 60, 100, 25);
        add(lbl2);

        student2Field = new JTextField();
        student2Field.setBounds(120, 60, 150, 25);
        add(student2Field);

        JButton addBtn = new JButton("Add ");
        addBtn.setBounds(320, 20, 160, 30);
        add(addBtn);

        JButton showBtn = new JButton("Show Graph");
        showBtn.setBounds(320, 60, 160, 30);
        add(showBtn);

        displayArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(displayArea);
        scroll.setBounds(20, 120, 640, 300);
        add(scroll);

        addBtn.addActionListener(e -> addFriendship());
        showBtn.addActionListener(e -> showGraph());

        setVisible(true);
    }
    private void addFriendship() {

    String s1 = student1Field.getText().trim();
    String s2 = student2Field.getText().trim();

    if (s1.isEmpty() || s2.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Enter both student names");

        return;
    }

    graph.putIfAbsent(s1, new ArrayList<>());
    graph.putIfAbsent(s2, new ArrayList<>());

    graph.get(s1).add(s2);
    graph.get(s2).add(s1);

    JOptionPane.showMessageDialog(this,
            "Friendship Added");

    student1Field.setText("");
    student2Field.setText("");
}
    private void showGraph() {

    displayArea.setText("");

    for (String student : graph.keySet()) {

        displayArea.append(student + " -> ");

        for (String friend : graph.get(student)) {

            displayArea.append(friend + " ");
        }

        displayArea.append("\n");
    }
}
    
}
