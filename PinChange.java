import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PinChange
{
	PinChange()
	{
		// code for frame creation
		JFrame f = new JFrame("Pin Change");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
		f.setUndecorated(true); // to decorate
		f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/PinChange.jpg"))); // sets the import to background
		f.setSize(1500,900); // width , height
		f.setLayout(null);
		f.setVisible(true);
		
		// code for Pin Change Title
		JLabel lPinChange = new JLabel("Pin Change");
		lPinChange.setFont(new Font("Serif", Font.BOLD,60)); 
		lPinChange.setForeground(Color.white); // set the color to the text
		lPinChange.setBounds(520,150,400,70);
		f.add(lPinChange);
		
		//code for Enter old pin Label
		JLabel lOldPin = new JLabel("Enter Old Pin :");
		lOldPin.setFont(new Font("Serif", Font.BOLD,35));
		lOldPin.setForeground(Color.white); // set the color to the text
		lOldPin.setBounds(400,280,400,45);
		f.add(lOldPin);
		
		//code for Enter new pin Label
		JLabel lNewPin = new JLabel("Enter New Pin :");
		lNewPin.setFont(new Font("Serif", Font.BOLD,35));
		lNewPin.setForeground(Color.white); // set the color to the text
		lNewPin.setBounds(400,380,400,45);
		f.add(lNewPin);
		
		//code for re Enter new pin Label
		JLabel lreNewPin = new JLabel("Re-enter New Pin :");
		lreNewPin.setFont(new Font("Serif", Font.BOLD,35));
		lreNewPin.setForeground(Color.white); // set the color to the text
		lreNewPin.setBounds(400,480,400,45);
		f.add(lreNewPin);
		
		// Code for OldPin JtextField 
		JTextField tOldPin = new JTextField(10);
		tOldPin.setFont(new Font("Serif", Font.BOLD,25));
		tOldPin.setBounds(700,300,200,45);
		tOldPin.setEditable(true);
		f.add(tOldPin);
		
		// Code for NewPin JtextField 
		JTextField tNewPin = new JTextField(10);
		tNewPin.setFont(new Font("Serif", Font.BOLD,25));
		tNewPin.setBounds(700,380,200,45);
		tNewPin.setEditable(true);
		f.add(tNewPin);
		
		// Code for NewPin JtextField 
		JTextField treNewPin = new JTextField(10);
		treNewPin.setFont(new Font("Serif", Font.BOLD,25));
		treNewPin.setBounds(700,460,200,45);
		treNewPin.setEditable(true);
		f.add(treNewPin);
		
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
				f.setVisible(false);
				MenuPage mp = new MenuPage();
			}
		});
		
		// code for clear button action
		bClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tOldPin.setText(null);
				tNewPin.setText(null);
				treNewPin.setText(null);
			}
		});
		
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
					
					int doldpin = 0;
					int oldpin;
					int n1;
					int n2;
            
					String a = tOldPin.getText();
					String b = tNewPin.getText();
					String c = treNewPin.getText();
            
					oldpin = Integer.parseInt(a);
					n1 = Integer.parseInt(b);
					n2 = Integer.parseInt(c);
					
					LogIn l = new LogIn();
					int acno = l.Account_no;
					rs = stml.executeQuery("select pin from atm where acno = "+acno);
            
					while(rs.next())
					{
						doldpin = rs.getInt(1);
					}
					
					if(doldpin == oldpin)
					{
						if(n1==n2)
						{
							stml.executeUpdate("update atm set pin = "+n1+" where acno = "+acno);
							JOptionPane.showMessageDialog(null,"Pin Updation Done","Sucess",JOptionPane.INFORMATION_MESSAGE);
							MenuPage mp = new MenuPage();
							f.setVisible(false);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "New pin does NOT match", "Wrong Pin", JOptionPane.ERROR_MESSAGE);
							tOldPin.setText(null);
							tNewPin.setText(null);
							treNewPin.setText(null);
						}
					}
            
					else
					{
						JOptionPane.showMessageDialog(null, "Wrong Old pin entered", "Wrong Pin", JOptionPane.ERROR_MESSAGE);
						tOldPin.setText(null);
						tNewPin.setText(null);
						treNewPin.setText(null);
					}
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
			
		// Code for Exit button action performed to the button
		bExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
}