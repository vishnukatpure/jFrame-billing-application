
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Date;
@SuppressWarnings("serial")
public class supplier_master extends JInternalFrame implements ActionListener
{
    static final int xOffset = 60, yOffset = 30;
    JButton btnadd,btnsave,btnupdate,btndelete;
	JButton btnfirst,btnlast,btnnext,btnprevious;
	JDesktopPane jf ;
	JTextField txtid,txtname,txtadd,txtcontact,txtperson,txtemail;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	
	Connection con=null;
	ResultSet rs=null;
	Statement st=null;


    public supplier_master() 
    {
    
    	//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
        super("SUPPLIER MASTER"  ,false,true,false,true);
        jf 	= new JDesktopPane();
        getContentPane().add(jf,BorderLayout.CENTER);
        setSize(540,550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(xOffset , yOffset );
        connect c=new connect();
        con=c.getConnection(con);
	 
		l1=new JLabel("SUPPLIER MASTER");
		l2=new JLabel("Supplier ID");
		l3=new JLabel("Supplier Name");
		l4=new JLabel("Address");
		l5=new JLabel("Contact No");
		l6=new JLabel("E-mail Address");
		l7=new JLabel("Contact Person");
		l8=new JLabel(""+new Date());
		
		btnadd=new JButton("Add");
		btnsave=new JButton("Save");
		btnupdate=new JButton("Update");
		btndelete=new JButton("Delete");
		btnfirst=new JButton("First");
		btnlast=new JButton("Last");
		btnnext=new JButton(">>");
		btnprevious=new JButton("<<");
		
		txtid=new JTextField(15);
		txtname=new JTextField(15);
		txtadd=new JTextField(15);
		txtcontact=new JTextField(15);
		txtemail=new JTextField(15);
		txtperson=new JTextField(15);
		
		JLabel lborder=new JLabel();
		lborder.setBounds(5, 5, 520, 510);
		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		jf.add(lborder);
		
		  
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
		 
		
		l6.setBounds(50, 260, 300, 55);
		jf.add(l6);
		txtemail.setBounds(200, 275, 200, 25);
		jf.add(txtemail);
 
		
		l7.setBounds(50, 300, 300, 55);
		jf.add(l7);
		txtperson.setBounds(200, 315, 200, 25);
		jf.add(txtperson);
		 
		
		btnadd.setBounds(100, 400, 75, 25);
		jf.add(btnadd);
		
		btnsave.setBounds(200, 400, 75, 25);
		jf.add(btnsave);
		
		btnupdate.setBounds(300, 400, 75, 25);
		jf.add(btnupdate);
		
		btndelete.setBounds(400, 400, 75, 25);
		jf.add(btndelete);
		btnsave.setEnabled(false);
		btndelete.setEnabled(false);
		btnupdate.setEnabled(false);
		btnfirst.setBounds(100, 450, 75, 25);
		jf.add(btnfirst);
		
		btnnext.setBounds(200, 450, 75, 25);
		jf.add(btnnext);
		
		btnprevious.setBounds(300, 450, 75, 25);
		jf.add(btnprevious);
		
		btnlast.setBounds(400, 450, 75, 25);
		jf.add(btnlast);
		
		
		l8.setBounds(800, 50, 520, 50);
		jf.add(l8);
		
		
		btnadd.addActionListener(this);
		btnsave.addActionListener(this);
		btnfirst.addActionListener(this);
		btnlast.addActionListener(this);
		btnnext.addActionListener(this);
		btnprevious.addActionListener(this);
		btndelete.addActionListener(this);
		btnupdate.addActionListener(this);
		
		   JLabel lborder1=new JLabel();
		   lborder1.setBounds(70, 380,430, 120);
		   lborder1.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
		   jf.add(lborder1);
	
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
	


	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource()==btnadd)
		{
			btnsave.setEnabled(true);
			btnupdate.setEnabled(true);
			txtname.setText("");
			txtadd.setText("");
			txtcontact.setText("");
			txtemail.setText("");
			txtperson.setText("");
		 	try 
		 	{
		 		st=con.createStatement();
		 		rs=st.executeQuery("SELECT supplier_id FROM supplier_master order by supplier_id DESC");
		 		if(rs.next())
		 		{
		 			int id=rs.getInt("supplier_id");
		 			id++;
		 			txtid.setText(""+id);
		 		} 
		 		else
		 			txtid.setText(""+1);
		 	}
		 	catch (Exception e)
		 	{
		 		JOptionPane.showMessageDialog(null,e);
		 	}
		}	
		 	
		if(ae.getSource()==btnsave)
		{
			String name,add,contact,email,person;
			int id=Integer.parseInt(txtid.getText());
			add=txtadd.getText();
			  contact=txtcontact.getText();
			  email=txtemail.getText();
			
			person=txtperson.getText();
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
							  if(!(email.equals("")))
							  {
								  	st=con.createStatement();
									st.executeUpdate("insert into supplier_master values("+id+",'"+name+"','"+add+"','"+contact+"','"+email+"','"+person+"')");
									JOptionPane.showMessageDialog(jf, "record saved");
									txtid.setText("");
									txtname.setText("");
									txtadd.setText("");
									txtcontact.setText("");
									txtemail.setText("");
									txtperson.setText("");
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
			String name,add,contact,email,person;
			
			int id=Integer.parseInt(txtid.getText());
			
			add=txtadd.getText();
			contact=txtcontact.getText();
			email=txtemail.getText();
			person=txtperson.getText();
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
									st.executeUpdate("update supplier_master set s_name='"+name+"',address='"+add+"',email='"+email+"',contact='"+contact+"',c_person='"+person+"' where supplier_id="+id);
									JOptionPane.showMessageDialog(jf, "record updated");
									txtid.setText("");
									txtname.setText("");
									txtadd.setText("");
									txtcontact.setText("");
									txtemail.setText("");
									txtperson.setText("");
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
				btndelete.setEnabled(true);
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from supplier_master");
			if(rs.next())
			{
				rs.first();
				txtid.setText(rs.getString(1));
				txtname.setText(rs.getString(2));
				txtadd.setText(rs.getString(3));
				txtcontact.setText(rs.getString(4));
				txtemail.setText(rs.getString(5));
				txtperson.setText(rs.getString(6));
				
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
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from supplier_master");
			if(rs.next())
			{
				rs.last();
				txtid.setText(rs.getString(1));
				txtname.setText(rs.getString(2));
				txtadd.setText(rs.getString(3));
				txtcontact.setText(rs.getString(4));
				txtemail.setText(rs.getString(5));
				txtperson.setText(rs.getString(6));
				
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
			{btndelete.setEnabled(true);
				int id=Integer.parseInt(txtid.getText());
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from supplier_master where supplier_id >"+id);
			if(rs.next())
			{
				rs.first();
				txtid.setText(rs.getString(1));
				txtname.setText(rs.getString(2));
				txtadd.setText(rs.getString(3));
				txtcontact.setText(rs.getString(4));
				txtemail.setText(rs.getString(5));
				txtperson.setText(rs.getString(6));
				
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
				int id=Integer.parseInt(txtid.getText());
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from supplier_master where supplier_id <"+id);
			if(rs.next())
			{
				rs.last();
				txtid.setText(rs.getString(1));
				txtname.setText(rs.getString(2));
				txtadd.setText(rs.getString(3));
				txtcontact.setText(rs.getString(4));
				txtemail.setText(rs.getString(5));
				txtperson.setText(rs.getString(6));
				
			}
			else
				JOptionPane.showMessageDialog(null,"you are at first record");		
			
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
				st.executeUpdate("delete * from supplier_master where supplier_id="+id);
				
				txtid.setText("");
				txtname.setText("");
				txtadd.setText("");
				txtcontact.setText("");
				txtemail.setText("");
				txtperson.setText("");
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

