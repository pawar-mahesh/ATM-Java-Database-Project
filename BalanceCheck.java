import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class BalanceCheck
{
	BalanceCheck()
	{
		JFrame f = new JFrame("Balance Info");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
		f.setUndecorated(true); // to decorate
		f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/Balance.jpg"))); // sets the import to background
		f.setSize(1500,900); // width , height
		f.setLayout(null);
		f.setVisible(true);
		
		JLabel lBalanceCheck = new JLabel("Balance Info");
		lBalanceCheck.setFont(new Font("Serif", Font.BOLD,60)); 
		lBalanceCheck.setBounds(500,150,400,70);
		f.add(lBalanceCheck);
		
		JLabel lBalanceInfo = new JLabel("Your current Balance :");
		lBalanceInfo.setFont(new Font("Serif", Font.BOLD,35));
		lBalanceInfo.setBounds(370,350,400,45);
		f.add(lBalanceInfo);
		
		JLabel lRs = new JLabel("Rs");
		lRs.setFont(new Font("Serif", Font.BOLD,35));
		lRs.setBounds(950,350,70,45);
		f.add(lRs);
		
		JTextField tAmount = new JTextField(10);
		tAmount.setFont(new Font("Serif", Font.BOLD,25));
		tAmount.setBounds(760,350,170,45);
		tAmount.setEditable(false);
		f.add(tAmount);
		
		JButton bMainMenu = new JButton("Main Menu"); // creating instance of new Button
		bMainMenu.setBounds(380,550,180,45); // x axis , y axis , width , height
		bMainMenu.setFont(new Font("Serif", Font.BOLD,25));
		f.add(bMainMenu); // adding button in jframe
		
		JButton bCheck = new JButton("Check"); // creating instance of new Button
		bCheck.setBounds(660,550,120,45); // x axis , y axis , width , height
		bCheck.setFont(new Font("Serif", Font.BOLD,25));
		f.add(bCheck); // adding button in jframe
		
		JButton bExit = new JButton("Exit"); // creating instance of new Button
		bExit.setBounds(880,550,100,45); // x axis , y axis , width , height
		bExit.setFont(new Font("Serif", Font.BOLD,25));
		f.add(bExit); // adding button in jframe
		
		bMainMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				MenuPage mp = new MenuPage();
			}
		});
		
		bCheck.addActionListener(new ActionListener()
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
					int balance = 0;
					
					rs = stmt.executeQuery("select bal from atm where acno ="+Account_Number);
					
					while(rs.next())
					{
						balance = rs.getInt(1);
					}
					
					String amu = Integer.toString(balance);
					tAmount.setText(amu);
					
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		
		bExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
}