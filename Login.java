
package University_Management_Syaytem.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Login extends JFrame{
    JTextField userField;
    JPasswordField paswordField;
    JButton LoginBtn;
    
    
    public Login(){
        setTitle("UNIVERSITY MANAGEMENT SYSTEM : ");
        setSize(400,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel userLabel = new JLabel("UserName");
        userLabel.setBounds(50,40,100,25);
        panel.add(userLabel);
        
        userField = new JTextField();
        userField.setBounds(150,40,180,25);
        panel.add(userField);
        
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(50,60,100,50);
        panel.add(passLabel);
        
        paswordField = new JPasswordField();
        paswordField.setBounds(150,80,180,30);
        panel.add(paswordField);
        
        LoginBtn = new JButton("Login");
        LoginBtn.setBounds(180,130,100,25);
        panel.add(LoginBtn);
        
        LoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });
        add(panel);
        setVisible(true);
    }
    
    public void Login(){
        String userName =userField.getText();
        String Password = new String(paswordField.getPassword());
        
        
        if(userName.equals("Admin") && Password.equals("1234"))
        {
        JOptionPane.showMessageDialog(this, "Login Successful!");
        
        dispose();
        new DashBoard();
        
    }else{
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
        
    }
    
    
}
