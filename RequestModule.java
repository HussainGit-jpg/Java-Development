package University_Management_Syaytem.java;
import javax.swing.*;
import java.util.Queue;
import java.util.LinkedList;

public class RequestModule extends JFrame {

    Queue<String> requests = new LinkedList<>();

    JTextField requestField;
    JTextArea displayArea;

    public RequestModule() {

        setTitle("Request Queue System");
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbl = new JLabel("Request:");
        lbl.setBounds(20, 20, 100, 25);
        add(lbl);

        requestField = new JTextField();
        requestField.setBounds(100, 20, 200, 25);
        add(requestField);

        JButton addBtn = new JButton("Add Request");
        addBtn.setBounds(320, 20, 130, 30);
        add(addBtn);

        JButton processBtn = new JButton("Delete");
        processBtn.setBounds(100, 70, 120, 30);
        add(processBtn);

        JButton showBtn = new JButton("Show Queue");
        showBtn.setBounds(250, 70, 120, 30);
        add(showBtn);

        displayArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(displayArea);
        scroll.setBounds(20, 130, 540, 200);
        add(scroll);

        addBtn.addActionListener(e -> addRequest());
        processBtn.addActionListener(e -> processRequest());
        showBtn.addActionListener(e -> showQueue());

        setVisible(true);
    }

    private void addRequest() {

        String req = requestField.getText();

        if(req.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Enter a Request");
            return;
        }

        requests.add(req);

        JOptionPane.showMessageDialog(this,
                "Request Added");

        requestField.setText("");
    }

    private void processRequest() {

        if(requests.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Queue Empty");

            return;
        }

        String served = requests.poll();

        JOptionPane.showMessageDialog(this,
                "Processed: " + served);
    }

    private void showQueue() {

        displayArea.setText("");

        for(String req : requests) {

            displayArea.append(req + "\n");
        }
    }
}

