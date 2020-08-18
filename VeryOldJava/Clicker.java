
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*; 
import java.util.Timer;
import java.util.TimerTask;

class Clicker{
	
	Clicker(){
		JFrame f = new JFrame("click speed test");
		JButton b = new JButton("click me!");
		b.setBounds(0,0,140,140);
		JLabel labe = new JLabel();
		labe.setBounds(150, 0, 300, 140);
		labe.setText("yes pls");
		
		
		
		
		 
		
		
		
		
		
		
		
		
		f.add(labe);
		f.add(b);
		f.setSize(300,140);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Timer timer;
		timer.scheduleAtFixedRate(new TimerTask() {
            int i = Integer.parseInt(args[0]);
            public void run() {
                labe.setText((String)(i-1));
                if (i< 0)
                    timer.cancel();
            }
        }, 0, 1000);
		
		
		
		
		b.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				
				
				
				
				
			}
			
			
			
			
		});
		
		
		
	}
	
	
	
	
	
	
	
	public static void main(String[]args){
		
		
		
		
		new Clicker();
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}