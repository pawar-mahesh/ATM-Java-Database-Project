import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class MobNoUpdate
{
	MobNoUpdate()
	{
		//code for Jframe for Mobile No Update
		JFrame f = new JFrame("Mobile No Update");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
		f.setUndecorated(true); // to decorate
		f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/MobileNoUpdate.png"))); // sets the import to background
		f.setSize(1500,900); // width , height
		f.setLayout(null);
		f.setVisible(true);
		
		// code For Mobile No Update title
		JLabel lMobileNo = new JLabel("Mobile No Update");//lWelcome.setFont(new Font("Serif", Font.PLAIN, 40));
		lMobileNo.setFont(new Font("Serif", Font.BOLD,60));//lWelcome.setFont(new Font("Courier New", Font.ITALIC, 12)); // Change the Font of the lable 
		lMobileNo.setForeground(Color.white); // set the color to the text
		lMobileNo.setBounds(450,150,600,100);
		f.add(lMobileNo);
		
		// code for enter old number title
		JLabel lOldMobNo = new JLabel("Enter old Mobile No : ");
		lOldMobNo.setFont(new Font("Serif", Font.BOLD,25));
		lOldMobNo.setForeground(Color.white); // set the color to the text
		lOldMobNo.setBounds(400,320,300,30);
		f.add(lOldMobNo);
		
		// code for enter new mobile number title
		JLabel lnewMobNo = new JLabel("Enter new Mobile No : ");
		lnewMobNo.setForeground(Color.white); // set the color to the text
		lnewMobNo.setFont(new Font("Serif", Font.BOLD,25));
		lnewMobNo.setBounds(400,450,300,30);
		f.add(lnewMobNo);
		
		// code for Jtext old mobile number entered
		JTextField tOldMobNo = new JTextField(10);
		tOldMobNo.setFont(new Font("Serif", Font.BOLD,25));
		tOldMobNo.setBounds(700,320,250,45);
		f.add(tOldMobNo);
		
		// code for Jtext new mobile number entered
		JTextField tNewMobNo = new JTextField(10);
		tNewMobNo.setFont(new Font("Serif", Font.BOLD,25));
		tNewMobNo.setBounds(700,450,250,45);
		f.add(tNewMobNo);
		
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
				tOldMobNo.setText(null);
				tNewMobNo.setText(null);
			}
		});
		
		// code for proceed Button action
		bProceed.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					// code for jdbc connectivity
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mahesh?verifyServerCertificate=false&useSSL=true","root","root");
					Statement stmt = con.createStatement();
					ResultSet rs;
					
					LogIn l = new LogIn();
					int acno = l.Account_no;
					
					String oldNo = tOldMobNo.getText();
					String newNo = tNewMobNo.getText();
            
					Long oldMoNo = Long.parseLong(oldNo);
					Long newMoNo = Long.parseLong(newNo);
					Long doldNo = 0L;
					
					rs = stmt.executeQuery("select mob from atm where acno = "+acno);
					while(rs.next())
					{
						doldNo = rs.getLong(1);
					}
					
					if(doldNo.equals(oldMoNo))
					{
						stmt.executeUpdate("update atm set mob = "+newMoNo+" where acno = "+acno);
						JOptionPane.showMessageDialog(null,"Mobile Number Updated Sucessfully","Sucess",JOptionPane.INFORMATION_MESSAGE);
						MenuPage mp = new MenuPage();
						f.setVisible(false);
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,"Inccorect Old Mobile Number "," Wrong Old No ",JOptionPane.ERROR_MESSAGE);
						tOldMobNo.setText(null);
						tNewMobNo.setText(null);
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