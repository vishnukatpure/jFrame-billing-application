import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
@SuppressWarnings("serial")
public class employee_master extends JInternalFrame implements ActionListener
{
    static final int xOffset = 50, yOffset = 30;
    JButton btnadd,btnsave,btnupdate,btndelete;
	JButton btnfirst,btnlast,btnnext,btnprevious,btnpay;
	JTextField txtid,txtname,txtadd,txtcontact,txtemail,txtdob,txtregi,txtsal,txtcomm;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
	JFrame jf;	
	Connection con=null;
	ResultSet rs=null;
	Statement st=null;		
     public employee_master() 
    {
    
    	//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
        super("EMPLOYEE MASTER"  ,false,true,false,true);
        JDesktopPane jf	= new JDesktopPane();
        getContentPane().add(jf,BorderLayout.CENTER);
        setSize(540,690);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(xOffset, yOffset);
        connect c=new connect();
        con=c.getConnection(con);
        l1=new JLabel("EMPLOYEE MASTER");
		l2=new JLabel("Employee ID");
		l3=new JLabel("Employee Name");
		l4=new JLabel("Address");
		l5=new JLabel("Contact No");
		l6=new JLabel("E-mail Address");
		l7=new JLabel("Registration Date");
		
		l10=new JLabel("Date Of Birth");
		l11=new JLabel("Salary");
		l12=new JLabel("Commision");
		
		
	
		btnadd=new JButton("Add");
		btnsave=new JButton("Save");
		btnsave.setEnabled(false);
		btnupdate=new JButton("Update");
		btndelete=new JButton("Delete");
		btnupdate.setEnabled(false);
		btndelete.setEnabled(false);
		btnfirst=new JButton("First");
		btnlast=new JButton("Last");
		btnnext=new JButton(">>");
		btnprevious=new JButton("<<");
		
		txtid=new JTextField(15);
		txtname=new JTextField(15);
		txtadd=new JTextField(15);
		txtcontact=new JTextField(15);
		txtemail=new JTextField(15);
		txtdob=new JTextField(15);
		txtregi=new JTextField(15);
		txtsal=new JTextField(15);
		txtcomm=new JTextField(15);
		
		
	
		 
		
		l1.setBounds(150, 40, 800, 40);
		l1.setFont(new Font("areal",Font.BOLD,28));
		jf.add(l1);
		
		l2.setBounds(50, 100, 300, 55);
		jf.add(l2);
		txtid.setBounds(200, 115, 200, 25);
		jf.add(txtid);
		txtid.setEditable(false);
		 
		
		l3.setBounds(50, 140, 300, 55);
		jf.add(l3);
		txtname.setBounds(200, 155, 200, 25);
		jf.add(txtname);
	 
		
		l4.setBounds(50, 180, 300, 55);
		jf.add(l4);
		txtadd.setBounds(200, 195, 200, 25);
		jf.add(txtadd);
		 
		
		l5.setBounds(50, 220, 300, 55);
		jf.add(l5);
		txtcontact.setBounds(200, 235, 200, 25);
		jf.add(txtcontact);
		//txtcontact.addKeyListener(this);
		
		l6.setBounds(50, 260, 300, 55);
		jf.add(l6);
		txtemail.setBounds(200, 275, 200, 25);
		jf.add(txtemail);
		
		l10.setBounds(50, 300, 300, 55);
		jf.add(l10);
		txtdob.setBounds(200, 315, 200, 25);
		jf.add(txtdob);
		
		l11.setBounds(50, 340, 300, 55);
		jf.add(l11);
		txtsal.setBounds(200, 355, 200, 25);
		jf.add(txtsal);
		
		l12.setBounds(50, 380, 300, 55);
		jf.add(l12);
		txtcomm.setBounds(200, 395, 200, 25);
		jf.add(txtcomm);
		txtcomm.setEditable(false);
		
	 
		
		l7.setBounds(50, 420, 300, 55);
		jf.add(l7);
		txtregi.setBounds(200, 435, 200, 25);
		jf.add(txtregi);
		txtregi.setEditable(false);
	 
		
		btnadd.setBounds(100, 500, 75, 25);
		jf.add(btnadd);
		
		btnsave.setBounds(200, 500, 75, 25);
		jf.add(btnsave);
		
		btnupdate.setBounds(300, 500, 75, 25);
		jf.add(btnupdate);
		
		btndelete.setBounds(400, 500, 75, 25);
		jf.add(btndelete);
		
		btnfirst.setBounds(100, 550, 75, 25);
		jf.add(btnfirst);
		
		btnnext.setBounds(200, 550, 75, 25);
		jf.add(btnnext);
		
		btnprevious.setBounds(300, 550, 75, 25);
		jf.add(btnprevious);
		
		btnlast.setBounds(400, 550, 75, 25);
		jf.add(btnlast);
		
		btnpay=new JButton("PAY COMMISSION");
		btnpay.setBounds(200, 600, 150, 25);
		jf.add(btnpay);
		btnpay.addActionListener(this);
		
		
		
		btnadd.addActionListener(this);
		btnsave.addActionListener(this);
		btnfirst.addActionListener(this);
		btnlast.addActionListener(this);
		btnnext.addActionListener(this);
		btnprevious.addActionListener(this);
		btndelete.addActionListener(this);
		btnupdate.addActionListener(this);
		
		jf.setVisible(true);
		jf.setBounds(5, 5,1020, 730);
		
		JLabel lborder=new JLabel();
		lborder.setBounds(5, 5, 520,645);
		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder);
		
		JLabel lborder1=new JLabel();
		lborder1.setBounds(70, 480,450, 160);
		lborder1.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder1);
		
		
		l9=new JLabel(new ImageIcon("bin\\line.PNG"));
		l9.setBounds(10, 10,10,10);
		jf.add(l9);
			
		
		
	} 	
	
	
	public void check()
	{
		String str=txtcontact.getText();
		int l=str.length();
		boolean flag=true;
		if(l==10)
		{
			x:for(int j=0;j<=l;j++)
				for(int i=49;i<=57;i++)
					if((str.charAt(j)==(char)i))
						flag=true;
					else
					{
						flag=false;
						break x;
					}
					if(flag==true)
					{
						JOptionPane.showMessageDialog(jf,"Char Not Allowed");
						txtcontact.setText("");
					}		
		}
		else
		{
			JOptionPane.showMessageDialog(jf,"Please Enter Correct No");
			txtcontact.setText("");
		}
		
	}
	

		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent ae) 
		{
			if(ae.getSource()==btnadd)
			{
				btnsave.setEnabled(true);
				txtname.setText("");
				txtadd.setText("");
				txtcontact.setText("");
				txtemail.setText("");
				txtdob.setText("");
				txtsal.setText("");
				txtcomm.setText("00");
				txtregi.setText(""+new Date());
			 	try 
			 	{
			 		st=con.createStatement();
			 		rs=st.executeQuery("SELECT employee_id FROM employee_master order by employee_id DESC");
			 		if(rs.next())
			 		{
			 			int id=rs.getInt("employee_id");
			 			id++;
			 			txtid.setText(""+id);
			 		} 
			 		else
			 			txtid.setText(""+1);
			 		btnupdate.setEnabled(true);
			 	}
			 	catch (Exception e)
			 	{
			 		JOptionPane.showMessageDialog(null,e);
			 	}
			}
		if(ae.getSource()==btnsave)
		{
			String name,add,contact,email,date,dob,salary,comm;
			int id=Integer.parseInt(txtid.getText());
			add=txtadd.getText();
			
			
			date=txtregi.getText();
			  try 
				{
					name=txtname.getText();
				  if(!(name.equals("")))
				  {
					  if(!(add.equals("")))
					  { check();
						  contact=txtcontact.getText();
						  if(!(contact.equals("")))
						  {//checkemail();
						  	  email=txtemail.getText();
						  	dob=txtdob.getText();
						  	salary=txtsal.getText();
						  	comm=txtcomm.getText();
							  if(!(email.equals("")))
							  {
								  	st=con.createStatement();
									st.executeUpdate("insert into employee_master values("+id+",'"+name+"','"+add+"','"+contact+"','"+email+"','"+dob+"','"+salary+"','"+comm+"','"+date+"')");
									JOptionPane.showMessageDialog(jf, "record saved");
									txtid.setText("");
									txtname.setText("");
									txtadd.setText("");
									txtcontact.setText("");
									txtemail.setText("");
									txtdob.setText("");
									txtsal.setText("");
									txtcomm.setText("");
									txtregi.setText("");
							  }
							  else
								  JOptionPane.showMessageDialog(jf,"Enter Email Address","Error Message",JOptionPane.ERROR_MESSAGE);
						  }
						  else
							  JOptionPane.showMessageDialog(jf,"Enter Contact No.","Error Message",JOptionPane.ERROR_MESSAGE);
					  }
					  else
						  JOptionPane.showMessageDialog(jf,"Enter Address","Error Message",JOptionPane.ERROR_MESSAGE);
				  }
				  else
					  JOptionPane.showMessageDialog(jf,"Enter name","Error Message",JOptionPane.ERROR_MESSAGE);
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}				 		
		}
		
		
		if(ae.getSource()==btnupdate)
		{
			String name,add,contact,email,date,dob,salary,comm;
			
			int id=Integer.parseInt(txtid.getText());
			
			add=txtadd.getText();
			contact=txtcontact.getText();
			email=txtemail.getText();
			date=txtregi.getText();
			dob=txtdob.getText();
		  	salary=txtsal.getText();
		  	comm=txtcomm.getText();
			  try 
				{   
					name=txtname.getText();
				  if(!(name.equals("")))
				  {
					  if(!(add.equals("")))
					  {
						  if(!(contact.equals("")))
						  {
							  if(!(email.equals("")))
							  {
								  	st=con.createStatement();
									st.executeUpdate("update employee_master set e_name='"+name+"',address='"+add+"',e_mail='"+email+"',contact='"+contact+"',d_o_b='"+dob+"',salary="+salary+",comm="+comm+" where employee_id="+id);
									JOptionPane.showMessageDialog(jf, "record updated");
									txtid.setText("");
									txtname.setText("");
									txtadd.setText("");
									txtcontact.setText("");
									txtemail.setText("");
									txtdob.setText("");
									txtsal.setText("");
									txtcomm.setText("");
									txtregi.setText("");
							  }
							  else
								  JOptionPane.showMessageDialog(jf,"Enter Email Address","Error Message",JOptionPane.ERROR_MESSAGE);
						  }
						  else
							  JOptionPane.showMessageDialog(jf,"Enter Contact No.","Error Message",JOptionPane.ERROR_MESSAGE);
					  }
					  else
						  JOptionPane.showMessageDialog(jf,"Enter Address","Error Message",JOptionPane.ERROR_MESSAGE);
				  }
				  else
					  JOptionPane.showMessageDialog(jf,"Enter name","Error Message",JOptionPane.ERROR_MESSAGE);
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}			
		}
		
		
		if(ae.getSource()==btnfirst)
		{
			try 
			{
				btnupdate.setEnabled(true);
				btndelete.setEnabled(true);
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs=st.executeQuery("select * from employee_master");
			if(rs.next())
			{
				rs.first();
				txtid.setText(rs.getString(1));
				txtname.setText(rs.getString(2));
				txtadd.setText(rs.getString(3));
				txtcontact.setText(rs.getString(4));
				txtemail.setText(rs.getString(5));
				txtdob.setText(rs.getString(6));
				txtsal.setText(rs.getString(7));
				txtcomm.setText(rs.getString(8));
				txtregi.setText(rs.getString(9));
				
			}
			} 
			catch (SQLException e) 
			{

				JOptionPane.showMessageDialog(null,e);
			}
		 
				
		}
		
		if(ae.getSource()==btnlast)
		{
			try 
			{
				btnupdate.setEnabled(true);
				btndelete.setEnabled(true);
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from employee_master");
			if(rs.next())
			{
				rs.last();
				txtid.setText(rs.getString(1));
				txtname.setText(rs.getString(2));
				txtadd.setText(rs.getString(3));
				txtcontact.setText(rs.getString(4));
				txtemail.setText(rs.getString(5));
				txtdob.setText(rs.getString(6));
				txtsal.setText(rs.getString(7));
				txtcomm.setText(rs.getString(8));
				txtregi.setText(rs.getString(9));
				
			}
			} 
			catch (SQLException e) 
			{

				JOptionPane.showMessageDialog(null,e);
			}
		 
				
		}
		
		if(ae.getSource()==btnnext)
		{
			try 
			{
				btnupdate.setEnabled(true);
				btndelete.setEnabled(true);
				int id=Integer.parseInt(txtid.getText());
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from employee_master where employee_id >"+id);
			if(rs.next())
			{
				rs.first();
				txtid.setText(rs.getString(1));
				txtname.setText(rs.getString(2));
				txtadd.setText(rs.getString(3));
				txtcontact.setText(rs.getString(4));
				txtemail.setText(rs.getString(5));
				txtdob.setText(rs.getString(6));
				txtsal.setText(rs.getString(7));
				txtcomm.setText(rs.getString(8));
				txtregi.setText(rs.getString(9));
				
			}
			else
			{
				JOptionPane.showMessageDialog(null,"you are at last record");		
			}
			} 
			catch (SQLException e) 
			{

				JOptionPane.showMessageDialog(null,e);
			}
		 
				
		}
		
		if(ae.getSource()==btnprevious)
		{
			try 
			{
				btnupdate.setEnabled(true);
				btndelete.setEnabled(true);
				int id=Integer.parseInt(txtid.getText());
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from employee_master where employee_id <"+id);
			if(rs.next())
			{
				rs.last();
				txtid.setText(rs.getString(1));
				txtname.setText(rs.getString(2));
				txtadd.setText(rs.getString(3));
				txtcontact.setText(rs.getString(4));
				txtemail.setText(rs.getString(5));
				txtdob.setText(rs.getString(6));
				txtsal.setText(rs.getString(7));
				txtcomm.setText(rs.getString(8));
				txtregi.setText(rs.getString(9));
				
			}
			else
			{
				JOptionPane.showMessageDialog(null,"you are at first record");		
			}
			} 
			catch (SQLException e) 
			{

				JOptionPane.showMessageDialog(null,e);
			}
		}
		
		
		if(ae.getSource()==btnpay)
		{
			int no=Integer.parseInt(txtid.getText());
			try 
			{
				st=con.createStatement();
				st.executeUpdate("update employee_master set comm='"+00+"' where employee_id="+no);
				txtcomm.setText("0");
			} 
			catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null,e);
			}
			
			
			
		}
		
		if(ae.getSource()==btndelete)
		{
			int ans=JOptionPane.showConfirmDialog(jf, " Are You sure","confirm", JOptionPane.YES_NO_OPTION);
			 if (ans==JOptionPane.YES_OPTION)
			 {
		      try 
			  {
				int id=Integer.parseInt(txtid.getText());
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				st.executeUpdate("delete * from employee_master where employee_id="+id);
				
				txtid.setText("");
				txtname.setText("");
				txtadd.setText("");
				txtcontact.setText("");
				txtemail.setText("");
				txtdob.setText("");
				txtsal.setText("");
				txtcomm.setText("");
				txtregi.setText("");
				JOptionPane.showMessageDialog(jf,"Your record has been deleted");
				 
		   	 } 
			catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null,e);
			}
			}
		}
		}
	
}
