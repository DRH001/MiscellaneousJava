/*
 *Daniel Holmes
 *Fredericton High School
 *Ms. Brooks
 *Assignment: J11
 *2019/05/30
 */
 
 
 
import java.awt.*;
import java.applet.Applet;

 
public class J11Q3 extends Applet implements Runnable{
	int noOfSnowflakes = 2000;//number of snowflakes - I can change this number (10000 or above is usually incredibly slow on this slow school computer)
	
	Snow[] snowflakes = new Snow[noOfSnowflakes];
	Thread animation;
	Graphics backbuffergc;//double buffering! (some double buffering code is heavily inspired by StackOverflow)
	Image backbuffer;//double buffering!
	
	
	
	
	
	public void start(){//for buffering/movement

		animation = new Thread (this);
		if (animation != null){
			animation.start();//starts the thread
		}
	}
	
	public void init(){//for buffering/movement
		
		
		for(int j=0;j<noOfSnowflakes;j++){//creates all the snowflakes
			snowflakes[j] = new Snow();
		}
		backbuffer = createImage(800, 800);//creates the image/buffer
		backbuffergc = backbuffer.getGraphics();
		
		
	}
	
	public void run(){//for buffering/movement
		while(true){
			for(int i=0;i<noOfSnowflakes;i++){
				snowflakes[i].update();//updates the snowflakes
			}
			
			paint(getGraphics());
			Thread.currentThread().yield();//gives other threads priority again
			try{
				animation.sleep(6);//to get a solid 60fps!
			}catch(InterruptedException e){
				
			}
			
			
		}
	}
	
	
	
	
	
	int r1 = 100;//radii of the three snowballs
	int r2 = 65;
	int r3 = 45;
	
	
	
	public void paint(Graphics gr){
		backbuffergc.setColor(new Color(100,100,130));//draws the background (I can't use setBackground with double buffering)
		backbuffergc.fillRect(0,0,800,800);
		
		backbuffergc.setColor(new Color(240,240,240));//draws the ground
		backbuffergc.fillRect(0,500,800,800);
		
		
		//this block does all the trees
		int treeY = 0;
		
		for(int treeX=50;treeX<910;treeX+=130){//a loop for drawing each tree (draws one at a time)
			backbuffergc.setColor(new Color(135,12,12));
			backbuffergc.fillRect(treeX-10,150+treeY,20,354+treeY);//draws the tree trunk
			treeY+=3;
			
			//int[] branchX1 = {20+treeX,20+treeX,90+treeX};
			//int[] branchX2 = {0+treeX,0+treeX,-70+treeX};
			//int[] branchY = {0+treeY+402,16+treeY+402,8+treeY+402};    these three^ lines are for the old tree shape, might need them later
			
			
			int[] branchY = {0+treeY+402,102+treeY+402,102+treeY+402}; //this is the y position of the initial branch (top)
			int[] branchShapeX = {treeX,-70+treeX,70+treeX};//this is actually useless now, just used as a reference in the loop below
			int[] a = new int[3];//these two arrays are used in the loop below
			int[] b = new int[3];
			
			
			if(treeY%2==0){//this block varies the colour of the trees slightly
				backbuffergc.setColor(new Color(1,50,32));
			}else{
				backbuffergc.setColor(new Color(11,60,42));
			}
			
			
			
			for(int i=0;i<280;i+=50){//actually draws each branch (i is the spacing between branches)
				a[0] = branchY[0]+i-280;//a[] is the y position 
				a[1] = branchY[1]+i-280;
				a[2] = branchY[2]+i-280;
				
				b[0] = treeX;//b[] is the x position (the branches get wider as they move down)
				b[1] = treeX - 56 - i*4/25;
				b[2] = treeX + 56 + i*4/25;
				
				
				
				backbuffergc.fillPolygon(b, a, 3);
				//backbuffergc.fillPolygon(branchX2, a, 3);
			}
			
			
		}//end of tree block
		
		
		
		
		backbuffergc.setColor(Color.white);//this block does the snowballs
		backbuffergc.fillOval(400-r1,500-r1,2*r1,2*r1);
		backbuffergc.fillOval(400-r2,370-r2,2*r2,2*r2);
		backbuffergc.fillOval(400-r3,275-r3,2*r3,2*r3);
		
		backbuffergc.setColor(Color.black);//this block does the eyes
		backbuffergc.fillOval(380-4,265,8,8);
		backbuffergc.fillOval(420-4,265,8,8);
		
		backbuffergc.fillOval(400-9,475,18,18);//this block does the buttons
		backbuffergc.fillOval(400-9,420,18,18);
		backbuffergc.fillOval(400-9,365,18,18);
		
		backbuffergc.setColor(new Color(165,42,42));//draws the arms, 3 pixels thickness
		backbuffergc.drawLine(335,370,270,415);//these three lines are the left arm
		backbuffergc.drawLine(335,371,270,416);
		backbuffergc.drawLine(335,372,270,417);
		
		backbuffergc.drawLine(465,370,530,415);//these three lines are the right arm
		backbuffergc.drawLine(465,371,530,416);
		backbuffergc.drawLine(465,372,530,417);
		
		backbuffergc.setColor(Color.black);
		backbuffergc.fillRect(355,275-r3,90,15);//bottom of tophat
		backbuffergc.fillRect(375,275-r3-50,50,50);//top of tophat
		
		backbuffergc.setColor(new Color(150,150,150));
		backbuffergc.drawRect(355,275-r3,90,15);//bottom of outline of tophat
		backbuffergc.drawRect(375,275-r3-50,50,50);//top of outline of tophat
		
		backbuffergc.setColor(Color.red);
		backbuffergc.fillRect(365, 302, 70, 16);
		
		backbuffergc.setColor(Color.orange);//draws the nose
		int[] noseX = {395,395,420};
		int[] noseY = {270,280,275};
		backbuffergc.fillPolygon(noseX, noseY, 3);
		
		
		
		
		
		
		backbuffergc.setColor(new Color(234,234,242));
		for(int i=0;i<noOfSnowflakes;i++){//this loop draws all the snowflakes
			
			backbuffergc.drawLine(snowflakes[i].x,snowflakes[i].y,snowflakes[i].x+6,snowflakes[i].y+6);//diagonal left>right
			backbuffergc.drawLine(snowflakes[i].x,snowflakes[i].y-1,snowflakes[i].x+6,snowflakes[i].y+5);
			
			backbuffergc.drawLine(snowflakes[i].x+6,snowflakes[i].y,snowflakes[i].x,snowflakes[i].y+6);//diagonal right>left
			backbuffergc.drawLine(snowflakes[i].x+6,snowflakes[i].y-1,snowflakes[i].x,snowflakes[i].y+5);
			
			backbuffergc.drawLine(snowflakes[i].x+3,snowflakes[i].y-2,snowflakes[i].x+3,snowflakes[i].y+8);//vertical
			
			backbuffergc.drawLine(snowflakes[i].x-2,snowflakes[i].y+3,snowflakes[i].x+8,snowflakes[i].y+3);//horizontal
		}
		
		
		gr.drawImage(backbuffer,0,0,this);//buffering is neat
		
    }
	
	
}
 
 
 
 
class Snow{//this class makes each snowflake
	int x = (int)(Math.random()*1400);//random starting location
	int y = (int)(Math.random()*800);//random starting location
	double realY = y;//real Y is stored as a double so I can have more random speeds (other than 1,2,3). Same for realX
	double realX = x;
	double yVelocity = (Math.random()+1);
	double xVelocity = (Math.random()+.5);
	
	void update(){
		if(y>800){//if the snowflake hits the bottom, it moves back up to a random spot on the top
			realY=-9;
			y=(int)realY;
			realX=(int)(Math.random()*1400);
			x=(int)realX;
			
		if(x<-9){//if the snowflake hits the side, it moves to the other side
			x=800;
			realX = 800;
		}
		
		}else{
			realX-=xVelocity;
			x=(int)realX;//x (an integer) is given back to the draw methods to draw the actual snowflakes
			realY+=yVelocity;
			y=(int)realY;//y (an integer) is given back to the draw methods to draw the actual snowflakes
		}
	}
	
	
	
	
	
	
	
}
 