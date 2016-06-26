import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.*; 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
@SuppressWarnings("serial")
public class employee_report extends JInternalFrame 
{
    static final int xOffset = 3, yOffset = 25;
    JButton btnnext,btnprevious;	
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
	JFrame jf;
	
	Connection con=null;
	ResultSet rs=null;
	Statement st=null;
	JTextArea eid,add,contact,email,cperson,dob,sal,com;
	int i=0;
	String str="",stradd="",stremail="",strp="",strcontact="",dob1="",sal1="",comm="";

     public employee_report() 
    {
    
    	//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
        super("Employee Report" ,true,true,true,true);
        JDesktopPane jf	= new JDesktopPane();
        getContentPane().add(jf,BorderLayout.CENTER);
        setSize(1020,700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(xOffset, yOffset);
        connect c=new connect();
        con=c.getConnection(con);
    	
      //------------------------label initialize------------------------------------  
      		l1=new JLabel("EMPLOYEE REPORT");
      		l2=new JLabel("E.ID");
      		l3=new JLabel("Employee Name");
      		l9=new JLabel("Address");
      		l5=new JLabel("Contact No");
      		l6=new JLabel("Email");
      		l10=new JLabel("Date Of Birth");
      		l11=new JLabel("Salary");
      		l12=new JLabel("Commission");
      		l7=new JLabel("Register Date");
      	
      		
      		
      		btnnext=new JButton("Next");
      		btnprevious=new JButton("Previous");
      		//===========================Text box initialize=============================
      		
      		//===============text field add====================================== 
      		l1.setBounds(300, 40, 800, 40);
      		l1.setFont(new Font("areal",Font.BOLD,28));
      		jf.add(l1);
                      
      		l2.setBounds(20, 130, 100, 50);
      		jf.add(l2) ;
      		eid=new JTextArea();
      		eid.setBounds(20, 165, 180, 450);
              jf.add(eid);
               
              l3.setBounds(60, 130, 100, 50);
      		jf.add(l3) ;
              
              l9.setBounds(210, 130, 100, 50);
              jf.add(l9) ;
              add=new JTextArea();
              add.setBounds(205, 165, 160, 450);
              jf.add(add);
              
      		
                      
      		l5.setBounds(375, 130, 100, 50);
      		jf.add(l5) ;
      		contact=new JTextArea();
              contact.setBounds(370, 165, 75, 450);
              jf.add(contact);
      		
      		l6.setBounds(450, 130, 100, 50);
      		jf.add(l6) ;
      		email=new JTextArea();
              email.setBounds(450, 165, 140, 450);
              jf.add(email);
              
              l10.setBounds(600, 130, 100, 50);
              jf.add(l10);
              dob=new JTextArea();
              dob.setBounds(595, 165, 80, 450);
              jf.add(dob);
              
              l11.setBounds(690, 130, 100, 50);
              jf.add(l11);
              sal=new JTextArea();
              sal.setBounds(680, 165, 65, 450);
              jf.add(sal);
              
              l12.setBounds(750, 130, 100, 50);
              jf.add(l12);
              com=new JTextArea();
              com.setBounds(750, 165, 65, 450);
              jf.add(com);
              
              l7.setBounds(830, 130, 100, 50);
              jf.add(l7) ;
              cperson=new JTextArea();
              cperson.setBounds(820, 165, 165, 450);
              jf.add(cperson);        
                          
            
                      
                      try
                      {                	
                      	st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                      	rs=st.executeQuery("select * from employee_master order by employee_id");
                      	while(rs.next())
                      	{
                      		if(i<27)
                      		{
                      			str=str+"  "+ rs.getInt(1)+"        "+rs.getString(2)+"\n";
                      			stradd=stradd+rs.getString(3)+"\n";
                      			strcontact=strcontact+rs.getString(4)+"\n";
                      			stremail=stremail+rs.getString(5)+"\n";   
                      			dob1=dob1+rs.getString(6)+"\n";
                      			sal1=sal1+rs.getString(7)+"\n";
                      			comm=comm+rs.getString(8)+"\n";
                      			strp=strp+rs.getString(9)+"\n";
                      			i++;
                      		}
                      		else
                      			break;
                      	}
                      	eid.setText(str);
                      	add.setText(stradd);
                      	contact.setText(strcontact);
                      	email.setText(stremail);
                      	cperson.setText(strp);
                      	dob.setText(dob1);
                      	sal.setText(sal1);
                      	com.setText(comm);
                      	dob.setEditable(false);sal.setEditable(false);com.setEditable(false);
                      	eid.setEditable(false);add.setEditable(false);contact.setEditable(false); email.setEditable(false);cperson.setEditable(false);
                      }
                      catch(Exception e)
                      {
                      	JOptionPane.showMessageDialog(null,e);
                      }
      		//================================Button add===========================
                      JLabel lborder1=new JLabel();
              		lborder1.setBounds(15, 163,980,457);
              		lborder1.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
              		jf.add(lborder1);
      		
      		                
                      
      		btnnext.setBounds(550, 620, 75, 25);
      		jf.add(btnnext);
                      
                      
              btnprevious.setBounds(400, 620, 90, 25);
      		jf.add(btnprevious);
      		
      				
      		jf.setVisible(true);
      		jf.setBounds(5, 5,1020, 730);
      		
      		JLabel lborder=new JLabel();
      		lborder.setBounds(5, 5, 1000, 645);
      		lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
      		jf.add(lborder);
      		  
      		 
      		
      	} 
      		 


      	public void actionPerformed(ActionEvent ae)
      	{
      		if(ae.getSource()==btnnext)
      		{
      			int j=i+27;
      			try
                   {                	
                   	while(rs.next())
                   	{
                   		if(i<j)
                   		{
                  			str=str+"  "+ rs.getInt(1)+"        "+rs.getString(2)+"\n";
                  			stradd=stradd+rs.getString(3)+"\n";
                  			strcontact=strcontact+rs.getString(4)+"\n";
                  			stremail=stremail+rs.getString(5)+"\n";   
                  			dob1=dob1+rs.getString(6)+"\n";
                  			sal1=sal1+rs.getString(7)+"\n";
                  			comm=comm+rs.getString(8)+"\n";
                  			strp=strp+rs.getString(9)+"\n";
                   			i++;
                   		}
                   		else
                   			break;
                   	}
                   	eid.setText(str);
                  	add.setText(stradd);
                  	contact.setText(strcontact);
                  	email.setText(stremail);
                  	cperson.setText(strp);
                  	dob.setText(dob1);
                  	sal.setText(sal1);
                  	com.setText(comm);
                  	dob.setEditable(false);sal.setEditable(false);com.setEditable(false);
                  	eid.setEditable(false);add.setEditable(false);contact.setEditable(false); email.setEditable(false);cperson.setEditable(false);
                   }
                   catch(Exception e)
                   {
                   	JOptionPane.showMessageDialog(null,e);
                   }
      		}
      		
      		
      		if(ae.getSource()==btnprevious)
      		{
      			i=i-27;
      			int j=i+27;
      			try
                   {  
      				for(int k=0;k<j;k++)
      					rs.previous();
                   	while(rs.next())
                   	{
                   		if(i<j)
                   		{
                  			str=str+"  "+ rs.getInt(1)+"        "+rs.getString(2)+"\n";
                  			stradd=stradd+rs.getString(3)+"\n";
                  			strcontact=strcontact+rs.getString(4)+"\n";
                  			stremail=stremail+rs.getString(5)+"\n";   
                  			dob1=dob1+rs.getString(6)+"\n";
                  			sal1=sal1+rs.getString(7)+"\n";
                  			comm=comm+rs.getString(8)+"\n";
                  			strp=strp+rs.getString(9)+"\n";
                   			i++;
                   		}
                   		else
                   			break;
                   	}
      			eid.setText(str);
              	add.setText(stradd);
              	contact.setText(strcontact);
              	email.setText(stremail);
              	cperson.setText(strp);
              	dob.setText(dob1);
              	sal.setText(sal1);
              	com.setText(comm);
              	dob.setEditable(false);sal.setEditable(false);com.setEditable(false);
              	eid.setEditable(false);add.setEditable(false);contact.setEditable(false); email.setEditable(false);cperson.setEditable(false);
                   }
                   catch(Exception e)
                   {
                   	JOptionPane.showMessageDialog(null,e);
                   }
      		}
      	}

    
      }

