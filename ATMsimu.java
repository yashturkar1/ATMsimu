import javax.swing.*;//for overall graphics
import java.awt.*;//buttons etc
import java.awt.event.*;// for actionlistener
import java.io.*;//for input and output
import java.util.*;//for utility classes like Scanner
class profile//Note -  this program is only for windows incase of linux the directories wont be formed
{
	public void CreateBalanceFile() throws IOException//creates a .txt file in C drive for balance
	{
		new File("c:\\data").mkdir();
		FileWriter ryt=new FileWriter("c://data/balance.txt");
		BufferedWriter out = new BufferedWriter(ryt);
		out.write("10000");
		out.close();
	}
	public void CreatePasswordFile() throws IOException//creates a .txt file in C drive for password
	{
		FileWriter ryt=new FileWriter("c://data/password.txt");
		BufferedWriter out = new BufferedWriter(ryt);
		String pas = JOptionPane.showInputDialog("Enter new password");//opens a window for entering password
		out.write(pas);
		out.close();
	}
	public void CreateAccount()//for creating an account
	{
		try
		{
			CreateBalanceFile();
			CreatePasswordFile();
		}catch(IOException e)
		{
			System.out.println(e);
		}	
	}
	public int Login(int a) throws IOException//just for checking the password rest is done in loginmain()
	{
		int flag = 0;
		Scanner in = new Scanner(new FileReader("c://data/password.txt"));
		String c = in.nextLine();
		int x = Integer.parseInt(c);
		if(a==x)
		{
			flag = 1;
		}
		return flag;
	}
	public void ChangePassword(int u)//for changing password on a new password file is created and overrides on the previous one
	{
		try
		{
			int o = Login(u);
			if(o==1);
			{
			CreatePasswordFile();	
			}
		}catch(IOException x)
		{
			System.out.println(x);
		}	
	}
	public void Loginmain(int pass)throws IOException//calls graphic function
			{
						int flag = Login(pass);
							if(flag==1)
							{
									MenuWindow mw = new MenuWindow();
									mw.MenuGraphics();	
							}
							else
							{
								WrongPassword wp = new WrongPassword();
								wp.Wrong();
							}
			}				
}	
class ATMsimu//class for all the atm tasks
{
		public void Deposit()// just calls the graphic method
		{
			DepositWindow dw = new DepositWindow();
			dw.DepositGraphics();
		}	
		public void Withdraw()// just calls the graphic method
		{
			WithdrawWindow ww = new WithdrawWindow();
			ww.WithdrawGraphics();
		}	
		public void Statement()// just calls the graphic method
		{
			StatementWindow sw = new StatementWindow();
			sw.StatementGraphics();
		}	
		public void Password()// just calls the graphic method
		{
			ChangePasswordWindow cpw = new ChangePasswordWindow();
			cpw.ChangePasswordGraphics();
		}	
		public void MakeDeposit(int dep)throws IOException//here takes place all the calculation part
		{
			int bal = ScanBalanceFile();
			bal +=dep;
			DepositedValue dv = new DepositedValue();
			dv.ShowDeposited(bal);
			new File("c:\\data").mkdir();
			FileWriter ryt=new FileWriter("c://data/balance.txt");
			BufferedWriter out = new BufferedWriter(ryt);
			String balstr = Integer.toString(bal);
			out.write(balstr);
			out.close();
		}
		public void WithdrawMoney(int with)throws IOException//here takes place all the calculation part
		{
			int bal = ScanBalanceFile();
			bal -=with;
			WithdrawValue wv = new WithdrawValue();
			wv.ShowWithdrawen(bal);
			FileWriter ryt=new FileWriter("c://data/balance.txt");
			BufferedWriter out = new BufferedWriter(ryt);
			String balstr = Integer.toString(bal);
			out.write(balstr);
			out.close();
		}
		public int ScanBalanceFile() throws IOException//Scans the balance file in c://data
		{
			Scanner intin = new Scanner(new FileReader("c://data/balance.txt"));
			String a = intin.nextLine();
			int bal = Integer.parseInt(a);
			return bal;
		}	
		public int GetStatement()throws IOException//Takes value from ScanBalanceFile and returns it
		{
			int bal = ScanBalanceFile();
			return bal;
		}
		public static void main(String args[])throws IOException//Main method just calls the main graphics method
		{	
				welcome ag = new welcome();
				ag.welcomegraphics();//primary grpahics method called	
		}
}
class welcome implements ActionListener//primary grpahics class
{
		public void welcomegraphics()
	{
		new welcome();
	}	
	JFrame f;
	JLabel welcome;
	JButton createacc,login;
	
	welcome()
	{
	welcome = new JLabel("Welcome");
	createacc = new JButton("Create Account");
	login = new JButton("Login");
	f = new JFrame("ATM");
	f.setSize(200,200);
	f.setLayout(new FlowLayout());
	f.setVisible(true);
	f.add(welcome);
	f.add(createacc);
	f.add(login);
	createacc.addActionListener(this);
	login.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==createacc)
		{
				try
				{
					profile pl = new profile();
					pl.CreateAccount();
				}catch(IOException gx)
					{
						System.out.println(gx);
					}	
		}
						
				if(ae.getSource()==login)
				{
					LoginWindow lw = new LoginWindow();
					lw.LoginGraphics();
				}	
	}
}
class LoginWindow implements ActionListener //opens a window to enter and get the password checked
{
	public void LoginGraphics()
	{
		new LoginWindow();
	}
	JFrame f;
	JLabel welcome;//WrongPassword;
	JTextField  password;
	JButton Signin;
	
	LoginWindow()
	{
		welcome = new JLabel("Enter your saved password");
		password = new JTextField(5);
		Signin = new JButton("Sign in");
		//WrongPassword = new jLabel("Sorry wrong password, re-enter your password");
		f = new JFrame("ATM");
		f.setSize(300,300);
		f.setLayout(new FlowLayout());
		f.setVisible(true);
		f.add(welcome);
		f.add(password);
		f.add(Signin);
		Signin.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==Signin)
		{
			try
			{
				String a = password.getText();
				int a1 = Integer.parseInt(a);
				profile p = new profile();
				p.Loginmain(a1);
			}catch(IOException e)
			{
				System.out.println(e);
			}	
		}	
	}
}
class WrongPassword//gives a message incase for a wrong password
{
	public void Wrong()
	{
	JFrame f;
	JLabel pass;
	pass = new JLabel("Wrong password or your account does not exist");
	f = new JFrame("Wrong Password");
	f.setSize(300,100);
	f.setLayout(new FlowLayout());
	f.setVisible(true);
	f.add(pass);
	}
}	
class MenuWindow implements ActionListener//opens menuwindow
{
	public void MenuGraphics()
	{
		new MenuWindow();
	}
	JFrame f;
	JLabel Menu;
	JButton deposit,withdraw,statement,changepassword,exit;
	
	MenuWindow()
	{
		Menu = new JLabel("You are in ! What would you like to do:-");
		deposit = new JButton("Deposit");
		withdraw = new JButton("WithDraw");
		statement = new JButton("Balance Statement");
		changepassword = new JButton("Change Password");
		exit = new JButton("Exit");
		f = new JFrame("Menu");
		f.setSize(300,300);
		f.setLayout(new FlowLayout());
		f.setVisible(true);
		f.add(Menu);
		f.add(deposit);
		f.add(withdraw);
		f.add(statement);
		f.add(changepassword);
		f.add(exit);
		deposit.addActionListener(this);
		withdraw.addActionListener(this);
		statement.addActionListener(this);
		changepassword.addActionListener(this);
		exit.addActionListener(this);
	}	
		public void actionPerformed(ActionEvent ae)
		{
			ATMsimu AS = new ATMsimu();
			if(ae.getSource()==deposit)
			{
				AS.Deposit();
			}
			if(ae.getSource()==withdraw)
			{				
					AS.Withdraw();
			}
			if(ae.getSource()==statement)
			{
					AS.Statement();				
			}
			if(ae.getSource()==changepassword)
			{
					AS.Password();	
			}
			if(ae.getSource()==exit)
			{
				System.exit(0);
			}	
		}
}
class DepositedValue /*shows final value after transaction*/ implements ActionListener
{

	JFrame f;
	JLabel bal;
	JButton exit;
	
	public void ShowDeposited(int bal1)
	{
	ATMsimu AS = new ATMsimu();
		bal = new JLabel("Your new Balance is = "+bal1);
		f = new JFrame("New Amount");
		exit = new JButton("Exit");
		f.setSize(300,300);
		f.setLayout(new FlowLayout());
		f.setVisible(true);
		f.add(bal);
		f.add(exit);
		exit.addActionListener(this);
	}	
		
public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource() == exit)
	{
		System.exit(0);
	}
}

}
class WithdrawValue implements ActionListener /*shows final value after transaction*/ 
{
	JFrame f;
	JLabel bal;
	JButton exit;
	public void ShowWithdrawen(int bal1)
	{
	
	ATMsimu AS = new ATMsimu();
		bal = new JLabel("Your new Balance is = "+bal1);
		f = new JFrame("New Amount");
		f.setSize(300,300);
		exit = new JButton("Exit");
		f.setLayout(new FlowLayout());
		f.setVisible(true);
		f.add(bal);
		f.add(exit);
		exit.addActionListener(this);
	}	
public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource() == exit)
	{
		System.exit(0);
	}
}	
	
}		
class DepositWindow implements ActionListener//opens respective windows
{
		public void DepositGraphics()
		{
				new DepositWindow();
		}	
		JFrame f;
		JLabel oldamt,newamt,deposit;
		JTextField  depositamt;
		JButton depositb;
		ATMsimu AS = new ATMsimu();
		DepositWindow()
		{
			try
			{
			oldamt = new JLabel("Your current balance is = "+AS.ScanBalanceFile());
			}catch(IOException r)
			{
				System.out.println(r);
			}
			deposit = new JLabel("Enter amount to deposit");
			depositb = new JButton("Deposit");
			depositamt = new JTextField(20);
			f = new JFrame("Deposit");
			f.setSize(300,300);
			f.setLayout(new FlowLayout());
			f.setVisible(true);
			f.add(oldamt);
			f.add(deposit);
			f.add(depositb);
			f.add(depositamt);
			depositb.addActionListener(this);	
		}	
	    public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource()==depositb)
			{
				try
				{
					String a = depositamt.getText();
					int a1 = Integer.parseInt(a);
					AS.MakeDeposit(a1);
				}catch(IOException e)
				{
					System.out.println(e);
				}	
			}
		}
}	
class WithdrawWindow implements ActionListener//opens respective windows
{
		public void WithdrawGraphics()
		{
				new WithdrawWindow();
		}	
		JFrame f;
		JLabel oldamt,newamt,withdraw;
		JTextField  withdrawamt;
		JButton withdrawb;
		ATMsimu AS = new ATMsimu();
		WithdrawWindow()
		{
			try
			{
			oldamt = new JLabel("Your current balance is = "+AS.ScanBalanceFile());
			}catch(IOException r)
			{
				System.out.println(r);
			}
			withdraw = new JLabel("Enter amount to Withdraw");
			withdrawb = new JButton("Withdraw");
			withdrawamt = new JTextField(20);
			f = new JFrame("withdraw");
			f.setSize(300,300);
			f.setLayout(new FlowLayout());
			f.setVisible(true);
			f.add(oldamt);
			f.add(withdraw);
			f.add(withdrawb);
			f.add(withdrawamt);
			withdrawb.addActionListener(this);	
		}	
	    public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource()==withdrawb)
			{
				try
				{
					String a = withdrawamt.getText();
					int a1 = Integer.parseInt(a);
					AS.WithdrawMoney(a1);	
				}catch(IOException e)
				{
					System.out.println(e);
				}	
			}
		}
}	
class StatementWindow//opens respective windows
{
	public void StatementGraphics()
	{
		new StatementWindow();
	}
	JLabel bal;
	JFrame f;
	ATMsimu AS = new ATMsimu();
	StatementWindow()
	{
		try
		{
			bal = new JLabel("Your current balance is = "+AS.ScanBalanceFile());
		}catch(IOException e)
		{
			System.out.println(e);
		}	
		f = new JFrame("Statement");
		f.setSize(200,200);
		f.setLayout(new FlowLayout());
		f.setVisible(true);
		f.add(bal);
	}
}
class ChangePasswordWindow implements ActionListener//opens respective windows
{
	public void ChangePasswordGraphics()
	{
		new ChangePasswordWindow();
	}	
	JLabel oldpass,newpass;
	JButton submit;
	JTextField oldpasst,newpasst;
	JFrame f;
	profile p = new profile();
	ChangePasswordWindow()
	{
		oldpass = new JLabel("Enter current password");
		oldpasst = new JTextField(5);
		submit = new JButton("Submit Password");
		f = new JFrame("Change Password");
		f.setSize(300,300);
		f.setLayout(new FlowLayout());
		f.setVisible(true);
		f.add(oldpass);
		f.add(oldpasst);
		f.add(submit);
		submit.addActionListener(this);
	}	
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource()==submit)
			{
				try
				{
					String a = oldpasst.getText();
					int a1 = Integer.parseInt(a);
					p.ChangePassword(a1);
				}catch(IOException e)
				{
					System.out.println(e);
				}	
			}
		}
}			


