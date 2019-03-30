import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Withdraw
{
	Withdraw()
	{
		// code for frame creation
		JFrame f = new JFrame("Withdraw");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
		f.setUndecorated(true); // to decorate
		f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/Withdraw.jpg"))); // sets the import to background
		f.setSize(1500,900); // width , height
		f.setLayout(null);
		f.setVisible(true);
		
		// code for Withdraw Title
		JLabel lWithdraw = new JLabel("Withdraw");
		lWithdraw.setFont(new Font("Serif", Font.BOLD,70)); 
		lWithdraw.setForeground(Color.white); // set the color to the text
		lWithdraw.setBounds(530,170,500,100);
		f.add(lWithdraw);
		
		// code for Enter amount title
		JLabel lEnterAmount = new JLabel("Enter the Amount :");
		lEnterAmount.setFont(new Font("Serif", Font.BOLD,35));
		lEnterAmount.setForeground(Color.white); // set the color to the text
		lEnterAmount.setBounds(400,410,400,45);
		f.add(lEnterAmount);
		
		// code for Rs label
		JLabel lRs = new JLabel("Rs");
		lRs.setFont(new Font("Serif", Font.BOLD,35));
		lRs.setForeground(Color.white); // set the color to the text
		lRs.setBounds(960,410,70,45);
		f.add(lRs);
		
		// Code for Amount JtextField 
		JTextField tAmount = new JTextField(10);
		tAmount.setFont(new Font("Serif", Font.BOLD,25));
		tAmount.setBounds(730,410,210,45);
		tAmount.setEditable(true);
		f.add(tAmount);
		
		// code for MainMenu Button
		JButton bMainMenu = new JButton("Main Menu"); // creating instance of new Button
		bMainMenu.setBounds(380,600,130,40); // x axis , y axis , width , height
		bMainMenu.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bMainMenu); // adding button in jframe
		
		// code for clear button
		JButton bClear = new JButton("Clear"); // creating instance of new Button
		bClear.setBounds(550,600,100,40); // x axis , y axis , width , height
		bClear.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bClear); // adding button in jframe
		
		//code for Proceed Button
		JButton bProceed = new JButton("Proceed"); // creating instance of new Button
		bProceed.setBounds(690,600,150,40); // x axis , y axis , width , height
		bProceed.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bProceed); // adding button in jframe
		
		//code for Exit Button
		JButton bExit = new JButton("Exit"); // creating instance of new Button
		bExit.setBounds(880,600,100,40); // x axis , y axis , width , height
		bExit.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bExit); // adding button in jframe
		
		// code for MainMenu key action
		bMainMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				MenuPage mp = new MenuPage();
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
		
		// code for 
		bProceed.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
				// code for jdbc connectivity
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahesh?verifyServerCertificate=false&useSSL=true","root","root");
					Statement stml = con.createStatement();
					ResultSet rs;
						
					int amu;
					String a = tAmount.getText();
					amu = Integer.parseInt(a);
						
					int oldBal = 0;
					int newBal;
				
					LogIn l = new LogIn();
					int acno = l.Account_no;
				
					rs = stml.executeQuery("select bal from atm where acno = "+acno);
					while(rs.next())
					{
						oldBal = rs.getInt(1);
					}
				
					if(oldBal<amu)
					{
						JOptionPane.showMessageDialog(null,"Last Transcation Cancled\nDue to Insfficient Balance","Transcation Cancled",JOptionPane.INFORMATION_MESSAGE);
					}
				
					else
					{
						newBal = oldBal - amu;
						stml.executeUpdate("update atm set bal = "+newBal+" where acno = "+acno);
						JOptionPane.showMessageDialog(null,"Transcation Suceesfull\nPlease collect your amount","Sucess",JOptionPane.INFORMATION_MESSAGE);
					}
						f.setVisible(false);
						MenuPage mp = new MenuPage();
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