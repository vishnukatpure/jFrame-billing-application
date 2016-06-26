import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
@SuppressWarnings("serial")
public class deluser extends JInternalFrame implements ActionListener
{
    static final int xOffset = 90, yOffset = 100;
    JLabel title,lid,lpass;
    public JPasswordField tpass;
    JButton delete,exitb;
    @SuppressWarnings("rawtypes")
    JComboBox sid;
    
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    JDesktopPane jf;
     @SuppressWarnings({ "rawtypes", "unchecked" })
	public deluser() 
    {
    
    	//super("Document #" + (++openFrameCount),resizable,closable,maximizable,iconifiable);
        super("Delete User"  ,false,true,false,true);
        jf	= new JDesktopPane();
        getContentPane().add(jf,BorderLayout.CENTER);
        setSize(355,295);
        setLocation(xOffset , yOffset) ;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        connect c=new connect();
        con=c.getConnection(con);
    	
        title=new JLabel("Delete User");
        title.setFont(new Font("comicsansms",Font.BOLD,25));
        title.setBounds(100, 5, 220, 50);
        jf.add(title);
        
        lid=new JLabel("SELECT USER_ID");
        lid.setBounds(20,100,250,20);
       
     		
        sid=new JComboBox();
        sid.setBounds(140,100,170,20);
        jf.add(lid);
        jf.add(sid);
        		try
        		{
        			st=con.createStatement();
        			rs=st.executeQuery("select User_ID from login");
        			while(rs.next())
        			{
        				String str1=rs.getString(1);
        				if(str1.equals("Admin")){}
        				else
        				sid.addItem(str1);
        			}
        		}
        catch(Exception e)
        {
     	   
        }
        lpass=new JLabel("Admin PASSWORD");
        lpass.setBounds(20,130,300,20);
      
       
        tpass=new JPasswordField(50);
        tpass.setBounds(140,130,170,20);
        
        jf.add(lpass);
        jf.add(tpass);
          
        delete=new JButton("DELETE");
        delete.setBounds(150,180,100,20);
        delete.addActionListener(this);
        jf.add(delete);

        sid.addActionListener(this);
        jf.setVisible(true);
        jf.setBounds(300, 250, 350, 300);
        
        JLabel lborder=new JLabel();
        lborder.setBounds(5, 5, 335, 255);
        lborder.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.gray));
        jf.add(lborder);
     	
       
        
      }
      
      
      		  @SuppressWarnings({ "deprecation", "unused" })
     		public void actionPerformed(ActionEvent ae)
     	       {
      			 			 
     	         if(ae.getSource()==delete) 
     	         {       
     	        	 String strid="",strpass="";
     	        	 try
     	        	 {
     	        		 strid=(String)sid.getSelectedItem();
     	        	      strpass=(String)tpass.getText();
     	        		  if(!(strpass.equals(""))) 
     	        			  {
     	        				  st=con.createStatement();
     		        			  rs=st.executeQuery("select * from login where User_ID='Admin' and Password='"+strpass+"'");
     		        			   	if(rs.next())
     		        			   	{
     		        			   		st.executeUpdate("delete * from login where User_ID='"+strid+"'");
     		        			   		JOptionPane.showMessageDialog(null,"Deleted");
     		        			   		jf.setVisible(false);
     		        					con.close();
     		        					st.close();
     		        			   		rs.close();
     		        			   		deluser d=new deluser();
     		        			   	}
     		        		        else
     	   		         		      	JOptionPane.showMessageDialog(jf,"Not authorized user","Error Message",JOptionPane.ERROR_MESSAGE);
     	        			  }
     	        			  else
     	        			  {
     	        				  JOptionPane.showMessageDialog(jf,"Enter Password","Error Message",JOptionPane.ERROR_MESSAGE);
     	        				  tpass.setFocusable(true);
     	        		      }
     	        		 	        		  
     	           	  }
     	        	catch(Exception e)
     	        	{
     	        		JOptionPane.showMessageDialog(null,e);      	
     	        	}  
     	       
     	          }//action performed close
     	       }//login close
     }
     	
