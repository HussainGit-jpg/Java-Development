
package University_Management_Syaytem.java;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;
import static java.nio.file.Files.delete;
public class Student_Module extends JFrame{
    LinkedList<Student> Students = new LinkedList<>();
    Stack<Student> undoStack = new Stack<>();
    
    
    JTextField idField,nameField,DeparField;
    JLabel table;
    DefaultTableModel model;
    private Object TitleLabel;
    
    
    
    public Student_Module(){
        setTitle("Student Management System");
        setSize(700,500);
        setLocationRelativeTo(null);
        setLayout(null);
        Font title = new Font("Arial",Font.BOLD,20);
        
        
        JLabel idlabel = new JLabel("S_ID :");
        idlabel.setBounds(20,20,50,25);
        add(idlabel);
        
        idField = new JTextField();
        idField.setBounds(80,20,150,25);
        add(idField);
        
        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setBounds(20,60,50,25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(80,60,150,25);
        add(nameField);
        
        JLabel DepartField = new JLabel("Department :");
        DepartField.setBounds(20,100,50,25);
        add(DepartField);
        
        DeparField = new JTextField();
        DeparField.setBounds(80,100,150,25);
        add(DeparField);
        
        
        JButton addBtn=new JButton("Add");
        addBtn.setBounds(280,20,100,30);
        add(addBtn);
        
        addBtn.setFocusPainted(false);
        
        
        JButton delete=new JButton("Delete");
        delete.setBounds(280,60,100,30);
        add(delete);
        
        JButton search=new JButton("Search");
        search.setBounds(280,100,100,30);
        add(search);
        
        JButton undo = new JButton("Undo");
        undo.setBounds(400,20,100,30);
        add(undo);
        
        JButton save = new JButton("Save");
        save.setBounds(400,60,100,30);
        add(save);
        
        JButton loadBtn = new JButton("Load");
        loadBtn.setBounds(400,100,100,30);
        add(loadBtn);
        
        JButton sortBtn = new JButton("Sort By Name");
        sortBtn.setBounds(520,100,100,30);
        add(sortBtn);
        
        JButton sortid = new JButton("Sort By ID");
        sortid.setBounds(520,60,100,30);
        add(sortid);
    
        
        model = new DefaultTableModel();
        model.addColumn("S_ID:");
        model.addColumn("S_Name:");
        model.addColumn("Depatment");
        
        JTable table = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 170, 640, 250);
        add(scrollPane);
        
        getContentPane().setBackground(new Color(240,248,255));
        
        addBtn.addActionListener(e->addstudent());
        delete.addActionListener(e->deleteStudent());
        search.addActionListener(e->searchStudent());
        undo.addActionListener(e -> undoDelete());
        save.addActionListener(e -> save());
        loadBtn.addActionListener(e -> load());
        sortBtn.addActionListener(e -> sortStudents());
        sortid.addActionListener(e -> sortByID());
        setVisible(true);
    }
    private void sortByID() {

    for(int i=0;i<Students.size()-1;i++) {

        for(int j=0;
            j<Students.size()-i-1;j++) {
            if(Students.get(j).id >Students.get(j+1).id) {
                Student temp = Students.get(j);
                Students.set(j,Students.get(j+1));
                Students.set(j+1,temp);
            }
        }
    }

    refreshTable();
}
    private void sortStudents() {

    for(int i=0;i<Students.size()-1;i++) {

        for(int j=0;j<Students.size()-i-1;j++) {
            
            if(Students.get(j).S_name.compareTo(
                    Students.get(j+1).S_name) > 0) {

                Student temp = Students.get(j);

                Students.set(j,Students.get(j+1));

                Students.set(j+1,temp);
            }
        }
    }
    refreshTable();
    JOptionPane.showMessageDialog(this,"Sorted Successfully");
}
    private void addstudent(){
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String depart = DeparField.getText();
        Students.add(new Student(id, name, depart));
         refreshTable();
         JOptionPane.showMessageDialog(this, "Student Added");
        clearFields();
        
    }
    

    private void searchStudent() {

        int id = Integer.parseInt(idField.getText());

        for (Student s : Students) {
            if (s.id == id) {

                nameField.setText(s.S_name);
                DeparField.setText(s.Departmant);

                JOptionPane.showMessageDialog(this, "Student Found");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Student Not Found");
    }
    
    private void undoDelete() {

    if (undoStack.isEmpty()) {

        JOptionPane.showMessageDialog(this,"Nothing To Undo");

        return;
    }

    Student restoredStudent = undoStack.pop();

    Students.add(restoredStudent);

    refreshTable();

    JOptionPane.showMessageDialog(this,"Student Restored");
}
         private void deleteStudent() {

          int id = Integer.parseInt(idField.getText());

         for (Student s : Students) {

          if (s.id == id) {
 
            undoStack.push(s);   

            Students.remove(s);

            refreshTable();

            JOptionPane.showMessageDialog(this,"Student Deleted");

            return;
        }
    }

    JOptionPane.showMessageDialog(this,"Student Not Found");
}
    private void refreshTable() {

        model.setRowCount(0);

        for (Student s : Students) {
            model.addRow(new Object[]{
                    s.id,
                    s.S_name,
                    s.Departmant
            });
        }
    }
    private void load() {

    try {

        Students.clear();

        BufferedReader reader =new BufferedReader(new FileReader("students.txt"));

        String line;

        while((line = reader.readLine()) != null) {

            String[] data = line.split(",");

            int id = Integer.parseInt(data[0]);
            String name = data[1];
            String dept = data[2];

            Students.add(new Student(id,name,dept));
        }

        reader.close();

        refreshTable();

        JOptionPane.showMessageDialog(this,"Students Loaded");
    }
    catch(Exception e) {

        JOptionPane.showMessageDialog(this,
                e.getMessage());
    }
}
    private void save() {

    try {

        BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"));

        for(Student s : Students) {

            writer.write(
                    s.id + "," +
                    s.S_name + "," +
                    s.Departmant);

            writer.newLine();
        }

        writer.close();

        JOptionPane.showMessageDialog(this,"Students Saved");

    }
    catch(Exception e) {

        JOptionPane.showMessageDialog(this,e.getMessage());
    }
}

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        DeparField.setText("");
    }
}
