import java.applet.*;
import java.awt.*;

public class GameOfLife extends Applet implements Runnable {
	Thread runner;
	boolean[][] shownGame = new boolean[128][128];
	boolean[][] updatedGame = new boolean[128][128];
	
	
	Graphics bufferGraphics; 
	 Image offscreen; 
     // To get the width and height of the applet. 
     Dimension dim; 
     int curX, curY;
	
	
	public void updateBoard() {
		
		for (int i = 0; i<128; i++) {
			for (int j = 0; j<128; j++) {
				
				int neighbourCount = 0; //count neighbours
				if (i!= 0) {
					if (shownGame[i-1][j] == true) {
						neighbourCount++;
					}
				}
				
				if (j!= 0) {
					if (shownGame[i][j-1] == true) {
						neighbourCount++;
					}
				}
				
				if (i!= 127) {
					if (shownGame[i+1][j] == true) {
						neighbourCount++;
					}
				}
				
				if (j!= 127) {
					if (shownGame[i][j+1] == true) {
						neighbourCount++;
					}
				}
				
				if (i!=0 && j!= 0) {
					if (shownGame[i-1][j-1] == true) {
						neighbourCount++;
					}
				}
				
				if (i!=0 && j!= 127) {
					if (shownGame[i-1][j+1] == true) {
						neighbourCount++;
					}
				}
				
				if (i!=127 && j!= 0) {
					if (shownGame[i+1][j-1] == true) {
						neighbourCount++;
					}
				}
				
				if (i!=127 && j!= 127) {
					if (shownGame[i+1][j+1] == true) {
						neighbourCount++;
					}
				}
				
				if (shownGame[i][j] == true) { //if live cell
					if (neighbourCount < 2) {
						updatedGame[i][j] = false;
					}else if (neighbourCount <4) {
						updatedGame[i][j] = true;
					}else {
						updatedGame[i][j] = false;
					}
					
				}else {// if dead cell
					if (neighbourCount == 3) {
						updatedGame[i][j] = true;
					}
				}
				
			}
		}
		
		for (int i =0; i<128; i++) {
			for (int j =0; j<128; j++) {
				shownGame[i][j] = updatedGame[i][j];
			}
		}
		
	}
	
	public void init() {
		
		 dim = getSize(); 
		    offscreen = createImage(dim.width,dim.height); 
          // by doing this everything that is drawn by bufferGraphics 
          // will be written on the offscreen image. 
          bufferGraphics = offscreen.getGraphics(); 
		
		for (int i = 0; i<128; i++) {
			for (int j = 0; j<128; j++) {
				double yeet = Math.random();
				if (yeet>0.8) {
					shownGame[i][j] = true;
				}else {
					shownGame[i][j] = false;
				}
				
			}
		}
		
	}
	
	
	public void start() {
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}
	}
	public void run() {
		while(true) {
			
			updateBoard();
			paint(getGraphics());
			Thread.currentThread().yield();
			try {
				runner.sleep(20);
			}catch (Exception e) {
				
			}
			
			
		}
		
		
	}
	
	
	
	public void paint(Graphics g) {
		bufferGraphics.clearRect(0,0,dim.width,dim.width); 
		for (int i = 0; i<128; i++) {
			for (int j = 0; j<128; j++) {
				if(shownGame[i][j] == true) {
					bufferGraphics.fillRect(j*5, i*5, 5, 5);
				}
				
			}
		}
		g.drawImage(offscreen,0,0,this); 
	}
	
}