import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Transfer
{
	Transfer()
	{
		//code for Jframe for Transfer
		JFrame f = new JFrame("Transfer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
		f.setUndecorated(true); // to decorate
		f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/Transfer.jpg"))); // sets the import to background
		f.setSize(1500,900); // width , height
		f.setLayout(null);
		f.setVisible(true);
		
		// code For Transfer title
		JLabel lTransfer = new JLabel("Transfer");//lWelcome.setFont(new Font("Serif", Font.PLAIN, 40));
		lTransfer.setFont(new Font("Serif", Font.BOLD,70));//lWelcome.setFont(new Font("Courier New", Font.ITALIC, 12)); // Change the Font of the lable 
		lTransfer.setBounds(550,120,500,100);
		lTransfer.setForeground(Color.white); // set the color to the text
		f.add(lTransfer);
		
		// code for enter account number title
		JLabel lAccountNumber = new JLabel("Enter Account Number : ");
		lAccountNumber.setFont(new Font("Serif", Font.BOLD,25));
		lAccountNumber.setForeground(Color.white); // set the color to the text
		lAccountNumber.setBounds(400,300,300,30);
		f.add(lAccountNumber);
		
		// code for enter amount title
		JLabel lAmount = new JLabel("Enter the Amount: ");
		lAmount.setFont(new Font("Serif", Font.BOLD,25));
		lAmount.setForeground(Color.white); // set the color to the text
		lAmount.setBounds(400,450,300,30);
		f.add(lAmount);
		
		// code for Jtext Account No entered
		JTextField tAccountNumber = new JTextField(10);
		tAccountNumber.setFont(new Font("Serif", Font.BOLD,25));
		tAccountNumber.setBounds(700,300,250,45);
		f.add(tAccountNumber);
		
		// code for Jtext Amount entered
		JTextField tAmount = new JTextField(10);
		tAmount.setFont(new Font("Serif", Font.BOLD,25));
		tAmount.setBounds(700,450,250,45);
		f.add(tAmount);
		
		// code for MainMenu Button
		JButton bMainMenu = new JButton("Main Menu"); // creating instance of new Button
		bMainMenu.setBounds(370,600,130,40); // x axis , y axis , width , height
		bMainMenu.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bMainMenu); // adding button in jframe
		
		// code for clear button
		JButton bClear = new JButton("Clear"); // creating instance of new Button
		bClear.setBounds(540,600,100,40); // x axis , y axis , width , height
		bClear.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bClear); // adding button in jframe
		
		//code for Proceed Button
		JButton bProceed = new JButton("Proceed"); // creating instance of new Button
		bProceed.setBounds(680,600,150,40); // x axis , y axis , width , height
		bProceed.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bProceed); // adding button in jframe
		
		//code for Exit Button
		JButton bExit = new JButton("Exit"); // creating instance of new Button
		bExit.setBounds(870,600,100,40); // x axis , y axis , width , height
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
				tAccountNumber.setText(null);
			}
		});
		
		// code for deposite button action
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
					
					LogIn l = new LogIn();
					int macno = l.Account_no;
					int moldBal = 0;
					int mnewBal;
            
					int amu;
            
					int tacno;
					int toldBal = 0;
					String tname = null;
					int tnewBal;
            
					String am = tAmount.getText();
					amu = Integer.parseInt(am);
            
					String ac = tAccountNumber.getText();
					tacno = Integer.parseInt(ac);
            
					rs = stml.executeQuery("select Name from atm where acno = "+tacno);
					while(rs.next())
					{
						tname = rs.getString(1);
					}
        
					int responce = JOptionPane.showConfirmDialog(null, "Name of Account Holder :  "+tname+"\n\nDo you want to continue?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(responce == JOptionPane.YES_OPTION)
					{
						rs = stml.executeQuery("select bal from atm where acno = "+macno); 
						while(rs.next())
						{
							moldBal = rs.getInt(1);
						}
						
						if(amu>moldBal)
						{
							JOptionPane.showMessageDialog(null,"Last Transcation Cancled\nDue to Insfficient Balance","Transcation Cancled",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							rs = stml.executeQuery("select bal from atm where acno = "+tacno);
				
							while(rs.next())
							{
								toldBal = rs.getInt(1);
							}
				
							mnewBal = moldBal - amu;
							tnewBal = toldBal + amu;
				
							stml.executeUpdate("update atm set bal = "+mnewBal+" where acno = "+macno);
							stml.executeUpdate("update atm set bal = "+tnewBal+" where acno = "+tacno);
							JOptionPane.showMessageDialog(null,"Transcation Suceesfull","Sucess",JOptionPane.INFORMATION_MESSAGE);
						}
						MenuPage mp = new MenuPage();
						f.setVisible(false);
					}
       
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