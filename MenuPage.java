import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class MenuPage
{
	MenuPage()
	{
		JFrame f = new JFrame("Menu Page");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); // to full the frame size 
		f.setUndecorated(true); // to decorate
		f.setContentPane(new JLabel(new ImageIcon("E://Atm Project Images/MenuPage.jpg"))); // sets the import to background
		f.setSize(1500,900); // width , height
		f.setLayout(null);
		f.setVisible(true);
		
		JLabel lMenuPage = new JLabel("Services");
		lMenuPage.setFont(new Font("Serif", Font.BOLD,70));
		//lMenuPage.setForeground(Color.white); // set the color to the text
		//lWelcome.setFont(new Font("Courier New", Font.ITALIC, 12)); // Change the Font of the lable 
		lMenuPage.setBounds(540,110,500,100);
		f.add(lMenuPage);
		
		JButton bBalanceCheck = new JButton("Balance Check"); // creating instance of new Button
		bBalanceCheck.setBounds(400,260,170,40); // x axis , y axis , width , height
		bBalanceCheck.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bBalanceCheck); // adding button in jframe
		
		JButton bDeposite = new JButton("Deposite"); // creating instance of new Button
		bDeposite.setBounds(400,370,120,40); // x axis , y axis , width , height
		bDeposite.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bDeposite); // adding button in jframe
		
		JButton bTransfer = new JButton("Transfer"); // creating instance of new Button
		bTransfer.setBounds(400,480,120,40); // x axis , y axis , width , height
		bTransfer.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bTransfer); // adding button in jframe
		
		JButton bWithdraw = new JButton("Withdraw"); // creating instance of new Button
		bWithdraw.setBounds(400,590,120,40); // x axis , y axis , width , height
		bWithdraw.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bWithdraw); // adding button in jframe
		
		JButton bPinChange = new JButton("Pin Change"); // creating instance of new Button
		bPinChange.setBounds(800,260,150,40); // x axis , y axis , width , height
		bPinChange.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bPinChange); // adding button in jframe
		
		JButton bMobNoUpdate = new JButton("Mob. No Update"); // creating instance of new Button
		bMobNoUpdate.setBounds(770,370,180,40); // x axis , y axis , width , height
		bMobNoUpdate.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bMobNoUpdate); // adding button in jframe
		
		JButton bLinkAdhar = new JButton("Link Adhar"); // creating instance of new Button
		bLinkAdhar.setBounds(800,480,150,40); // x axis , y axis , width , height
		bLinkAdhar.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bLinkAdhar); // adding button in jframe
		
		JButton bExit = new JButton("Exit"); // creating instance of new Button
		bExit.setBounds(870,590,80,40); // x axis , y axis , width , height
		bExit.setFont(new Font("Serif", Font.BOLD,20));
		f.add(bExit); // adding button in jframe
		
		
		bBalanceCheck.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				BalanceCheck bc = new BalanceCheck();
			}
		});
		
		bDeposite.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				Deposite d = new Deposite();
			}
		});
		
		bTransfer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				Transfer t = new Transfer();
			}
		});
		
		bWithdraw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				Withdraw w = new Withdraw();
			}
		});
		
		bPinChange.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				PinChange p = new PinChange();
			}
		});
		
		bMobNoUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				MobNoUpdate mob = new MobNoUpdate();
			}
		});
		
		bLinkAdhar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				LinkAdhar adhar = new LinkAdhar();
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
