
package University_Management_Syaytem.java;
import java.util.ArrayList;
import javax.swing.*;
import java.util.*;
    

class CourseNode {

    String name;
    ArrayList<CourseNode> children;

    public CourseNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
}


public class CourseTree extends JFrame {

    CourseNode root;
    JTextArea display;

    public CourseTree() {

        setTitle("Course Tree System");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        display = new JTextArea();
        JScrollPane scroll = new JScrollPane(display);
        scroll.setBounds(20, 20, 440, 300);
        add(scroll);

        JButton showBtn = new JButton("Show Tree");
        showBtn.setBounds(170, 330, 120, 30);
        add(showBtn);

        buildTree(); // create structure

        showBtn.addActionListener(e -> showTree());
        
       setVisible(true);
}
    private void buildTree() {

        root = new CourseNode("Computer Science");

        CourseNode programming = new CourseNode("Programming");
        CourseNode ai = new CourseNode("Artificial Intelligence");

        CourseNode java = new CourseNode("Java");
        CourseNode cpp = new CourseNode("C++");

        CourseNode ml = new CourseNode("Machine Learning");
        CourseNode dl = new CourseNode("Deep Learning");

        
        programming.children.add(java);
        programming.children.add(cpp);

        ai.children.add(ml);
        ai.children.add(dl);

        root.children.add(programming);
        root.children.add(ai);
    } 
  private void showTree() {

        display.setText("");

        printTree(root, 0);
    }
    private void printTree(CourseNode node, int level) {

        for (int i = 0; i < level; i++) {
            display.append("   ");
        }

        display.append(node.name + "\n");

        for (CourseNode child : node.children) {
            printTree(child, level + 1);
        }
    }

       
}
