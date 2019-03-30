import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Deposite
{
	Deposite()
	{
		JFrame f = new JFrame("Deposite");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
		f.setUndecorated(true); // to decorate
		f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/Deposite.jpg"))); // sets the import to background
		f.setSize(1500,900); // width , height
		f.setLayout(null);
		f.setVisible(true);
		
		// code for Title
		JLabel lDeposite = new JLabel("Deposite");
		lDeposite.setForeground(Color.white); // set the color to the text
		lDeposite.setFont(new Font("Serif", Font.BOLD,70)); 
		lDeposite.setBounds(550,160,500,100);
		f.add(lDeposite);
		
		// code for Enter amount title
		JLabel lEnterAmount = new JLabel("Enter the Amount :");
		lEnterAmount.setForeground(Color.white); // set the color to the text
		lEnterAmount.setFont(new Font("Serif", Font.BOLD,35));
		lEnterAmount.setBounds(390,370,400,45);
		f.add(lEnterAmount);
		
		// code for Rs label
		JLabel lRs = new JLabel("Rs");
		lRs.setFont(new Font("Serif", Font.BOLD,35));
		lRs.setForeground(Color.white); // set the color to the text
		lRs.setBounds(890,370,70,45);
		f.add(lRs);
		
		// Code for Amount JtextField 
		JTextField tAmount = new JTextField(10);
		tAmount.setFont(new Font("Serif", Font.BOLD,25));
		tAmount.setBounds(700,370,170,45);
		tAmount.setEditable(true);
		f.add(tAmount);
		
		// code for MainMenu Button
		JButton bMainMenu = new JButton("Main Menu"); // creating instance of new Button
		bMainMenu.setBounds(350,550,130,40); // x axis , y axis , width , height
		bMainMenu.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bMainMenu); // adding button in jframe
		
		// code for clear button
		JButton bClear = new JButton("Clear"); // creating instance of new Button
		bClear.setBounds(520,550,100,40); // x axis , y axis , width , height
		bClear.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bClear); // adding button in jframe
		
		//code for Deposite Button
		JButton bDeposite = new JButton("Deposite"); // creating instance of new Button
		bDeposite.setBounds(660,550,150,40); // x axis , y axis , width , height
		bDeposite.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bDeposite); // adding button in jframe
		
		//code for Exit Button
		JButton bExit = new JButton("Exit"); // creating instance of new Button
		bExit.setBounds(850,550,100,40); // x axis , y axis , width , height
		bExit.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bExit); // adding button in jframe
		
		// code for MainMenu key action
		bMainMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MenuPage mp = new MenuPage();
				f.setVisible(false);
			}
		});
		
		// code for clear button action
		bClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tAmount.setText(null);
			}
		});
		
		// code for deposite button action
		bDeposite.addActionListener(new ActionListener()
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
					
					LogIn l = new LogIn();
					int Account_Number = l.Account_no;
					int oldBal = 0;
					int newBal;
					int amu;
            
					String a = tAmount.getText();
					amu = Integer.parseInt(a);
            
            
					rs = stmt.executeQuery("select bal from atm where acno = "+Account_Number);
            
					while(rs.next())
					{
						oldBal = rs.getInt(1);
					}
            
					newBal = oldBal + amu;
            
					stmt.executeUpdate("update atm set bal = "+newBal+" where acno = "+Account_Number);
            
					// code for DialogMessage
					JOptionPane.showMessageDialog(null,"Transcation Suceesfull","Sucess",JOptionPane.INFORMATION_MESSAGE);
            
					MenuPage mp = new MenuPage();
					f.setVisible(false);
				}
				
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		
		// code for exit button action
		bExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});


	}
}