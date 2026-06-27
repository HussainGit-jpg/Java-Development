
package University_Management_Syaytem.java;
import java.awt.Font;
import javax.swing.*;
import java.util.*;

import java.util.Stack;

public class DashBoard extends JFrame {
    public DashBoard(){
        setTitle("UNIVERSITY SYSTEM DASHBOARD :");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel title = new JLabel("SMART UNIVERSITY MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial",Font.BOLD,22));
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel Title = new JLabel("SMART UNIVERSITY SYSTEM :");
        Title.setBounds(180,20,300,30);
        panel.add(Title);
        
        JButton StudentBtn = new JButton("Student module");
        StudentBtn.setBounds(100,100,130,30);
        panel.add(StudentBtn);
        
        JButton Course = new JButton("Course Tree ");
        Course.setBounds(100,150,130,30);
        panel.add(Course);
        
        JButton Queue = new JButton("Request Queue");
        Queue.setBounds(300,100,130,30);
        panel.add(Queue);
        
        JButton Graph = new JButton("Graph Network");
        Graph.setBounds(300,150,130,30);
        panel.add(Graph);
        
        add(panel);
        setVisible(true);
        
         StudentBtn.addActionListener(e -> {
        new Student_Module();
    });
        Course.addActionListener(e -> {
    new CourseTree();
    });
     
    Graph.addActionListener(e -> {
    new GraphModule();
    });
    Queue.addActionListener(e -> {
        new RequestModule();
    });
    }
   
}
