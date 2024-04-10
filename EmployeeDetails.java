
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
 


public class EmployeeDetails extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    JButton btn;
    String id,name,department,address,salary;
    EmployeeDetails() {
        setTitle("Employee Details");
        setVisible(true);
        setSize(400, 400);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Employee ID:");
        l1.setBounds(50, 50, 100, 20);
        tf1 = new JTextField();
        tf1.setBounds(150, 50, 150, 20);

        l2 = new JLabel("Name:");
        l2.setBounds(50, 100, 100, 20);
        tf2 = new JTextField();
        tf2.setBounds(150, 100, 150, 20);

        l3 = new JLabel("Department:");
        l3.setBounds(50, 150, 100, 20);
        tf3 = new JTextField();
        tf3.setBounds(150, 150, 150, 20);

        l4 = new JLabel("Salary:");
        l4.setBounds(50, 200, 100, 20);
        tf4 = new JTextField();
        tf4.setBounds(150, 200, 150, 20);

        l5 = new JLabel("Address:");
        l5.setBounds(50, 250, 100, 20);
        tf5 = new JTextField();
        tf5.setBounds(150, 250, 150, 20);

        btn = new JButton("Submit");
        btn.setBounds(150,300 ,100 ,30);
        
        add(l1); add(tf1); add(l2); add(tf2); add(l3); add(tf3); add(l4); add(tf4); add(l5); add(tf5); add(btn);
        
        btn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
             id=tf1.getText();
             name=tf2.getText();
             department=tf3.getText();
             salary=tf4.getText();
             address=tf5.getText();

            JOptionPane.showMessageDialog(this,"Employee ID: "+id+"\nName: "+name+"\nDepartment: "+department+"\nSalary: "+salary+"\nAddress: "+address);
            writedata();
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
            tf5.setText("");
    }
    void dbconnect(){
        Connection conn = null;
        try {
            String databaseURL = "jdbc:sqlite:C:\\Users\\gento\\Desktop\\emp.db"; 
            conn = DriverManager.getConnection(databaseURL);
            System.out.println("SQLite database created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    void writedata(){
                Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\gento\\Desktop\\emp.db");

            String[] stringArray = {id, name, department,salary,address};

            String insertDataSQL = "INSERT INTO employee (empdata) VALUES (?)";
            String SQLinput = String.join(",", stringArray);
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL);
            preparedStatement.setString(1, SQLinput);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("String data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    public void createTable() {
        Statement statement = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\gento\\Desktop\\emp.db");
            statement = connection.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + "employee" + " (" +
                    "empdata TEXT" +
                    ")";

            statement.executeUpdate(createTableSQL);

            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
            EmployeeDetails e = new EmployeeDetails();
            e.dbconnect();
            e.createTable();

    }
}

