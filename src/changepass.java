import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
@SuppressWarnings("serial")
public class changepass extends JInternalFrame implements ActionListener
{
	 static final int xOffset = 120, yOffset = 100;
    JLabel title,lid,lpass,lpass1,lpassa,l6;
    public JTextField tid;
    public JPasswordField tpass,tpass1,tpassa,txtpasscon;
    JButton badduser,exitb;    
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    JDesktopPane jf	;
     public changepass() 
    {
    
    	//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
        super("Password Change" ,false,true,false,true);
        jf	= new JDesktopPane();
        getContentPane().add(jf,BorderLayout.CENTER);
        setSize(400,295);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setLocation(xOffset, yOffset);
        connect c=new connect();
        con=c.getConnection(con);
    	
        title=new JLabel("Change Password");
        title.setFont(new Font("comicsansms",Font.BOLD,25));
        title.setBounds(100, 2, 220, 50);
        jf.add(title);
        
        lpassa=new JLabel("Admin Password");
        lpassa.setBounds(20,80,300,20);
       
       
        tpassa=new JPasswordField(50);
        tpassa.setBounds(200,80,170,20);
        jf.add(lpassa);
        jf.add(tpassa);

        
        lid=new JLabel("USER_ID");
        lid.setBounds(20,100,250,20);
       
        tid=new JTextField(50);
        tid.setBounds(200,100,170,20);
        jf.add(lid);
        jf.add(tid);



        lpass=new JLabel("OLD PASSWORD ");
        lpass.setBounds(20,120,300,20);
       
        tpass=new JPasswordField(50);
        tpass.setBounds(200,120,170,20);
        jf.add(lpass);
        jf.add(tpass);
        
        lpass1=new JLabel("NEW PASSWORD ");
        lpass1.setBounds(20,140,300,20);
        tpass1=new JPasswordField(50);
        tpass1.setBounds(200,140,170,20);
        jf.add(lpass1);
        jf.add(tpass1);
        
        l6=new JLabel("ReEnter PASSWORD ");
        l6.setBounds(20,160,300,20);
        txtpasscon=new JPasswordField(50);
        txtpasscon.setBounds(200,160,170,20);
        jf.add(l6);
        jf.add(txtpasscon);
          
        badduser=new JButton("Change password");
        badduser.setBounds(120,200,150,20);
        badduser.addActionListener(this);
        jf.add(badduser);

          
      
        jf.setVisible(true);
        jf.setBounds(300, 250, 400, 300);
       
        JLabel lborder=new JLabel();
        lborder.setBounds(5, 5, 380, 255);
        lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
        jf.add(lborder);
      } 		 
     @SuppressWarnings({ "deprecation", "unused" })
     public void actionPerformed(ActionEvent ae)
     {
     	
     	
     	if(ae.getSource()==badduser) 
     	{       
     		String strida="",strid="",strpass="",strpass1="",strpass2="";
     		
     		strida=tpassa.getText();
     		strid=tid.getText();
     		strpass=tpass.getText();
     		strpass1=tpass1.getText();
     		strpass2=txtpasscon.getText();
     		try
     		{   
     			if(!(strida.equals(""))) 
     			{
     				if(!(strid.equals("")))
     				{
     					if(!(strpass1.equals(""))&&(!(strpass2.equals(""))))
     						{
     							if(strpass1.equals(strpass2))
     							{
     								st=con.createStatement();
     								rs=st.executeQuery("select * from login where Password='"+ tpassa+ "'");
     								if(rs.next())
     								{	
     									int i=st.executeUpdate("update from login set Password='"+strpass1+"' where User_ID='"+strid+"'");
     									JOptionPane.showMessageDialog(jf,"Authorized user","Authentication Message",JOptionPane.YES_NO_OPTION);
     								}
     								else
     									JOptionPane.showMessageDialog(jf,"Admin pass Wrong","Error Message",JOptionPane.ERROR_MESSAGE);
     							}
     							else
     							{
     								JOptionPane.showMessageDialog(jf,"Password Mismatch","Error Message",JOptionPane.ERROR_MESSAGE);
     								tpass.setText("");
     								tpass1.setText("");
     							}
     						}
     						else
     							JOptionPane.showMessageDialog(jf,"Enter Password`s","Error Message",JOptionPane.ERROR_MESSAGE);
     						}
     				else
     				{
     					JOptionPane.showMessageDialog(jf,"Enter User ID","Error Message",JOptionPane.ERROR_MESSAGE);
     				}
     			}
     			else
     			{
     				JOptionPane.showMessageDialog(jf,"Enteradmin pass","Error Message",JOptionPane.ERROR_MESSAGE);
     			}
     		
     	}
     	catch(Exception e)
     	{
     		JOptionPane.showMessageDialog(null,e);      	
     	}  
     				
     }//action performed close
     }//login close
     		
     	
     }	
     		