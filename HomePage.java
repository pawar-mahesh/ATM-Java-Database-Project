import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class LogIn 
{
	static int Account_no; 
	
    public int getAccount_no()
    {
        return Account_no;
    }
	
    public void setAccount_no(int Account_no)
    {
        LogIn.Account_no = Account_no;
    }
	
	//@SuppressWarnings("deprecation")
	void LogInDetails()
	{
		// Code for creation of frame
		JFrame f = new JFrame("Lon In Page"); // Creating instance of JFrame with String as 'LogIn Page'
		f.setResizable(false);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
		f.setUndecorated(true); // to decorate
		f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/Welcome.jpg"))); // sets the import to background
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1500,900); // width , height
		f.setLayout(null); // set the layout null //f.setVisible(true); // setting the frame visible
			
		// code For Welcome title
		JLabel lWelcome = new JLabel("WEL - COME");//lWelcome.setFont(new Font("Serif", Font.PLAIN, 40));
		lWelcome.setFont(new Font("Serif", Font.BOLD,60));//lWelcome.setFont(new Font("Courier New", Font.ITALIC, 12)); // Change the Font of the lable 
		lWelcome.setBounds(515,130,400,70);
		f.add(lWelcome);
		
		// code for Enter amount title
		JLabel lAccountNumber = new JLabel("Enter Account Number : ");
		lAccountNumber.setFont(new Font("Serif", Font.BOLD,25));
		lAccountNumber.setBounds(400,270,300,30);
		f.add(lAccountNumber);
		
		// code for pin enter title
		JLabel lPin = new JLabel("Enter Pin Number : ");
		lPin.setFont(new Font("Serif", Font.BOLD,25));
		lPin.setBounds(400,380,300,30);
		f.add(lPin);
		
		//bLogIn = new JButton(new ImageIcon("D://Images/Login.jpg")); // creating instance of new Button with icon
		JButton bLogIn = new JButton("LogIn"); // creating instance of new Button
		bLogIn.setBounds(420,515,110,35); // x axis , y axis , width , height
		bLogIn.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bLogIn); // adding button in jframe
		
		JButton bClear = new JButton("Clear"); // creating instance of new Button
		bClear.setFont(new Font("Serif", Font.BOLD,20));
		bClear.setBounds(620,515,100,40); // x axis , y axis , width , height
		f.add(bClear); // adding button in jframe
		
		JButton bExit = new JButton("Exit"); // creating instance of new Button
		bExit.setFont(new Font("Serif", Font.BOLD,20));
		bExit.setBounds(820,515,100,40); // x axis , y axis , width , height
		f.add(bExit); // adding button in jframe
		
		//code for JTextField 
		JTextField tAccountNumber = new JTextField(10);
		tAccountNumber.setFont(new Font("Serif", Font.BOLD,25));
		tAccountNumber.setBounds(700,270,250,40);
		tAccountNumber.setEditable(true);
		f.add(tAccountNumber);
		
		//code for JPasswordField
		JPasswordField pPin = new JPasswordField(10);
		pPin.setFont(new Font("Serif", Font.BOLD,25));
		pPin.setBounds(700,380,250,40);
		f.add(pPin);
		
		f.setVisible(true);
		
		
		// Code for actions performed to the button
		bExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		bClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pPin.setText(null);
				tAccountNumber.setText(null);
			}
		});
		
		bLogIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					//code for jdbc connectivity
					Class.forName("com.mysql.cj.jdbc.Driver"); // Load the jdbc class
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahesh?verifyServerCertificate=false&useSSL=true", "root", "root");
					Statement stmt = con.createStatement();
					ResultSet rs;
					
					String PinNo =  new String(pPin.getPassword());
					String AccNo = tAccountNumber.getText();
					
					int p = Integer.parseInt(PinNo);
					int a = Integer.parseInt(AccNo);
					int dpin = 0;
					Account_no = a; // Set the account number
					rs = stmt.executeQuery("select pin from atm where acno = "+a);
					while(rs.next())
					{
						dpin = rs.getInt(1);
					}
					
					if(dpin == p)
					{
						MenuPage mp = new MenuPage();
						f.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Inccorect Details","Login error",JOptionPane.ERROR_MESSAGE);
						tAccountNumber.setText(null);
						pPin.setText(null);
					}
					
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
	}
}

class HomePage 
{
	public static void main(String args[])
	{
		LogIn l = new LogIn();
		l.LogInDetails();
	}
}