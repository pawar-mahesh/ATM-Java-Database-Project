import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class LinkAdhar
{
	LinkAdhar()
	{
		try
		{
			//code for jdbc connectivity
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load the jdbc class
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahesh?verifyServerCertificate=false&useSSL=true", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs;
			
			LogIn l = new LogIn();
			int acno = l.Account_no;
			int adhar = 0;
			rs = stmt.executeQuery("select adhar from atm where acno = "+acno);
			
			while(rs.next())
			{
				adhar = rs.getInt(1);
			}
			
			//code for Jframe for Adhar card link
			JFrame f = new JFrame("Adhar Card Link");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setResizable(false);
			f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
			f.setUndecorated(true); // to decorate
			f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/Adhar.jpg"))); // sets the import to background
			f.setSize(1500,900); // width , height
			f.setLayout(null);
			f.setVisible(true);
			
			if(adhar == 0)
			{
				
				// code For Adhar card link title
				JLabel lAdharLink = new JLabel("Adhar Card Link");//lWelcome.setFont(new Font("Serif", Font.PLAIN, 40));
				lAdharLink.setFont(new Font("Serif", Font.BOLD,60));//lWelcome.setFont(new Font("Courier New", Font.ITALIC, 12)); // Change the Font of the lable 
				lAdharLink.setBounds(450,150,600,100);
				f.add(lAdharLink);
				
				// code for enter adhar Number title
				JLabel lAdharNumber = new JLabel("Enter Adhar Number : ");
				lAdharNumber.setFont(new Font("Serif", Font.BOLD,25));
				lAdharNumber.setForeground(Color.white); // set the color to the text
				lAdharNumber.setBounds(400,330,300,30);
				f.add(lAdharNumber);
				
				// code for enter mobile number label
				JLabel lMobNo = new JLabel("Enter Registered Mob. No: ");
				lMobNo.setFont(new Font("Serif", Font.BOLD,25));
				lMobNo.setBounds(380,450,500,30);
				lMobNo.setForeground(Color.white); // set the color to the text
				f.add(lMobNo);
				
				// code for Jtext Adhar No entered
				JTextField tAdharNumber = new JTextField(10);
				tAdharNumber.setFont(new Font("Serif", Font.BOLD,25));
				tAdharNumber.setBounds(700,330,250,45);
				f.add(tAdharNumber);
		
				// code for Jtext Mobile no entered
				JTextField tMobNO = new JTextField(10);
				tMobNO.setFont(new Font("Serif", Font.BOLD,25));
				tMobNO.setBounds(700,450,250,45);
				f.add(tMobNO);
		
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
						tMobNO.setText(null);
						tAdharNumber.setText(null);
					}
				});
				
				//code for Proceed button action
				bProceed.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							
							/*
							// code for jdbc connectivity
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahesh?verifyServerCertificate=false&useSSL=true","root","root");
							Statement stml = con.createStatement();
							ResultSet rs;
							*/							
							String mob;
							int imob;
							
							mob = tMobNO.getText();
							imob = Integer.parseInt(mob);
							
							int dmob = 0;
							
							LogIn l = new LogIn();
							int acno = l.Account_no;
							
							stmt.executeQuery("select mob from atm where acno = "+acno);
							while(rs.next())
							{
								dmob = rs.getInt(1);
							}
							
							if(dmob == imob)
							{
								JOptionPane.showMessageDialog(null,"Adhar card is linked Sucessfully");
								MenuPage mp = new MenuPage();
								f.setVisible(false);
							}
							
							else
							{
								JOptionPane.showMessageDialog(null,"You entered wrong mobile number.","Wrong Mobile Number",JOptionPane.WARNING_MESSAGE);
								tMobNO.setText(null);
								tAdharNumber.setText(null);
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
			
			else
			{
				JOptionPane.showMessageDialog(null,"Adhar card is already linked");
				MenuPage m = new MenuPage();
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}