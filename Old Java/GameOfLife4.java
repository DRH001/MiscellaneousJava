import java.applet.*;
import java.awt.*;
//import java.io.*;
//import java.util.Arrays; 

public class GameOfLife4 extends Applet implements Runnable{
	//TO DO: hard-code in some shapes to start with
	
	//if you want to have different animations, change these values below
	
	Thread runner;

	
	/*
	note: only one boolean of mazeFlag,singlePixel,singleLine, or customPixelLayout can be true at one time. If more than one is true, only one will take effect.
	
	
	*/
	

	int frameSkip = 1;//shows every n frames (2 is good for most applications with 2 periodicity)
	int desiredFPS = 150;//maxes out at like 500 ish 
	int screenSize = 750;//usually 600, max 750 on my laptop screen
	int threadSleepInMilliSeconds = 1000/desiredFPS;//usually 10, 16 for almost perfect 60fps (which compensates for system lag/execution time)
	int tickReset = 250000000;//set to like 1000000000 if you don't want a periodic reset
	boolean deadReset = true;
	int checkEveryNFrames = 60; //how many frames to check for repetition/dead detection (usually 6) //recently implemented, could be glitchy
	//note: for best results on a laptop screen, use xSize = ySize, xSize*pixelSize<=600
	int xSize = 250;//usually 600, is how many pixelsize pixels the screen is//keep this odd if using custom stuff for more symmetric results
	int ySize = xSize;//usually 600, ^^^^    <--change xSize to a numerical value if you don't want it to be square
	int pixelSize = screenSize/xSize;//usually 1, can be changed to a numerical value (not recommended to change)
	boolean mazeFlag = false; //keep this true for a maze generator
	int finalPasses = 2; //final passes to filter the maze (only takes effect if mazeFlag), usually 2-3
	boolean singlePixel = false;//spawns one single pixel in the middle of the screen
	boolean singleLine = false;//spawns one line in the middle of the screen (not too exciting)
	double spawnFactor = 0.51; //usually 0.8 - 1-spawnFactor is the percentage chance each pixel will be false when spawning randomly
	
	
	boolean customPixelLayout = false;//turn this on to use the following x and y values as starting pixels (note: keep 1 and 3 in regenNumberList when using this or singlePixel)
	int[] customPixelX = {0,xSize-1,0,xSize-1};//indices must be [0,xSize)
	int[] customPixelY = {0,ySize-1,ySize-1,0};//indices must be [0,ySize)
	
	boolean[][] shownGame = new boolean[xSize][ySize];
	boolean[][] updatedGame = new boolean[xSize][ySize];
	boolean[][] oldGame = new boolean[xSize][ySize];
	/* cool stuff (simple, random pixelation) - note: this is old, the first two numbers represent a range of integers to put in liveNumbers, the numbers after are values for regenNumberList, spf is spawn factor
	for below:
	
	int[] liveNumbers = {3,4,6,7,8,0,-1,-1,-1};
	int[] regenNumberList = {3,6,7,-8,-4,-1,-1,-1,-1};
	
	int[] liveNumbers = {3,4,6,7,8,-1,-1,-1,-1};
	int[] regenNumberList = {3,6,7,8,-4,-1,-1,-1,-1};
	
	
	
	8,5, (3,5), .45spf
	darkness consumes me - 8,5,(2,3,5,8),spf 0.9
	cool static - 8,5,(2,6,5,8),spf 0.9 (cool with .5)
	darkness consumes me v2 - 8,5, (3,4,5,6),spf0.8 or .795 <--better with .7975 (VERY sensitive)
	cool static v2 - 7,5,(2,4,5,5),spf.795 and small variations
	5,3,{3},spf.8
	neat 5,4,{4,5,3,3}spf.8
	6,5,{3,4,5,6},spf.5
	
	*/
	
	//rules:
	//*
	//must absolutely have 9 digits, cells will not die with this many neighbors - usually 2 and 3
	int[] liveNumbers = {3,4,6,7,8,-1,-1,-1,-1};
	int[] regenNumberList = {3,6,7,8,-4,-1,-1,-1,-1};
	//the number of neighbors which resurrects a dead cell, usually 3 (MUST ABSOLUTELY BE 9 numbers!!!!!!!)
	/* 
	//copy/paste:	-1,-1,-1,-1,-1,-1,-1,-1,-1  			Note: -1 works as a null value
	//System.out.println("input x size, y size, pixel size on separate lines");
	*/
	
	
	//BELOW IS THE CODE NECESSARY TO SPAWN MAZES
	
	
	//liveNumbers = {-1,1,2,3,-1,-1,-1,-1,-1};
	//regenNumberList = {2,-1,-1,-1,-1,-1,-1,-1,-1};
		
	//*/
	
	/* single pixel:
	int[] liveNumbers = {5,4,3,-1,-1,-1,-1,-1,-1};//must absolutely have 9 digits, cells will not die with this many neighbors - usually 2 and 3
	int[] regenNumberList = {-1,3,-1,1,2,-1,-1,-1,-1};
	for this one^^ use xSize of 91
	
	
	int[] liveNumbers = {4,3,9,6,-1,-1,-1,-1,-1};//must absolutely have 9 digits, cells will not die with this many neighbors - usually 2 and 3
	int[] regenNumberList = {1,0,8,5,-1,-1,-1,-1,-1};
	
	
	holy shit...
	int[] liveNumbers = {2,-1,3,-1,-1,-1,-1,-1,-1};//must absolutely have 9 digits, cells will not die with this many neighbors - usually 2 and 3
	int[] regenNumberList = {-1,-1,-1,1,2,-1,-1,-1,-1};
	*/
	
	
	
	
	int dead = 0; //do not change this!
	
	Graphics bufferGraphics; //bufferGraphics is used for double buffering
	 Image offscreen; 
     
     Dimension dim; 
     int curX, curY;
	 int ticks = 0; //tick counter, because why not
	
	
	public void updateBoard(boolean lastTick) {//contains reset/dead detection info, as well as rules
		ticks++;
		
		
		if(lastTick){
			//finds special case...
			if(mazeFlag){
				for (int x = 0; x<finalPasses;x++){//some of this is Noah's code originally
					for (int i = 0; i<xSize; i++) {//i is on the x-axis
						for (int j = 0; j<ySize; j++) {//j is on the y-axis
							int neighbourCount = 0; //counts neighbouring pixels
							if (i!= 0) {
								//System.out.println("outerPing");
								if (shownGame[i-1][j] == true) {//mid left
									neighbourCount++;
									//System.out.println("ping");
								}
							}
							
							if (j!= 0) {
								if (shownGame[i][j-1] == true) {//centre top
									neighbourCount++;
								}
							}
							
							if (i!= xSize-1) {
								if (shownGame[i+1][j] == true) {//mid right
									neighbourCount++;
								}
							}
							
							if (j!= ySize-1) {
								if (shownGame[i][j+1] == true) {//centre bottom
									neighbourCount++;
								}
							}
					
							if(x == finalPasses-1){//this post-renders the mazes (to get rid of extra white spaces), only for the final, final pass
								if(neighbourCount == 4){
									updatedGame[i][j] = true;
									//System.out.println("not updating");
									
								}
							}else{
								if(neighbourCount == 3 || neighbourCount == 4){//post-renders the maze
									updatedGame[i][j] = true;
									//System.out.println("not updating");
									
								}
								
							}
							//System.out.println("we got this far");
					
							
							
					
						}
					}
					for (int i =0; i<xSize; i++) {
						for (int j =0; j<ySize; j++) {
							shownGame[i][j] = updatedGame[i][j];//updates the post-rendered maze
						}
					}
				}
				return;
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		if(ticks%checkEveryNFrames==0){//(if not mazeflag)
			for(int i = 0; i<xSize; i++){
				for(int j = 0; j<ySize; j++){
					oldGame[i][j] = shownGame[i][j];//copies the current game to the old game for dead detection checkEveryNFrames frames later
				}
			}
		}
		if(!lastTick){//normal tick case
			for (int i = 0; i<xSize; i++) {//x iteration
				for (int j = 0; j<ySize; j++) {//y iteration
					
					int neighbourCount = 0; //count neighbours
					if (i!= 0) {
						if (shownGame[i-1][j] == true) {//mid left
							neighbourCount++;
						}
					}
					
					if (j!= 0) {
						if (shownGame[i][j-1] == true) {//centre top
							neighbourCount++;
						}
					}
					
					if (i!= xSize-1) {
						if (shownGame[i+1][j] == true) {//mid right
							neighbourCount++;
						}
					}
					
					if (j!= ySize-1) {
						if (shownGame[i][j+1] == true) {//centre bottom
							neighbourCount++;
						}
					}
					
					if (i!=0 && j!= 0) {
						if (shownGame[i-1][j-1] == true) {//top left
							neighbourCount++;
						}
					}
					
					if (i!=0 && j!= ySize-1) {
						if (shownGame[i-1][j+1] == true) {//bottom left
							neighbourCount++;
						}
					}
					
					if (i!=xSize-1 && j!= 0) {
						if (shownGame[i+1][j-1] == true) {//top right
							neighbourCount++;
						}
					}
					
					if (i!=xSize-1 && j!= ySize-1) {
						if (shownGame[i+1][j+1] == true) {//bottom right
							neighbourCount++;
						}
					}
					/*
					if (shownGame[i][j] == true) { //if live cell
						if (neighbourCount < lowerNeighborLimit) {
							updatedGame[i][j] = false;
						}else if (neighbourCount <= upperNeighborLimit) {
							updatedGame[i][j] = true;
						}else {
							updatedGame[i][j] = false;
						}
					*/
					
					if(shownGame[i][j] == true){//if cell is alive
						if(liveNumbers[0] == neighbourCount || liveNumbers[1] == neighbourCount || liveNumbers[2] == neighbourCount || liveNumbers[3] == neighbourCount || liveNumbers[4] == neighbourCount || liveNumbers[5] == neighbourCount || liveNumbers[6] == neighbourCount || liveNumbers[7] == neighbourCount || liveNumbers[8] == neighbourCount ){
							updatedGame[i][j] = true;//if cell has a certain number of neightbors (defined in liveNumbers), it will stay alive
							
						}else{
							updatedGame[i][j] = false;//if cell doesn't have specific number of neighbors it dies
						}
						
						
						
						
					}else {// if dead cell
						if (regenNumberList[0] == neighbourCount || regenNumberList[1] == neighbourCount || regenNumberList[2] == neighbourCount ||regenNumberList [3] == neighbourCount || regenNumberList[4] == neighbourCount || regenNumberList[5] == neighbourCount || regenNumberList[6] == neighbourCount || regenNumberList[7] == neighbourCount || regenNumberList[8] == neighbourCount ){
							updatedGame[i][j] = true;//if dead cell can regen
						}
					}
				}
			}
		}
		
		dead = 0;//do not change this!!!!!!!!!!!!!!!!!
		
		if(ticks%checkEveryNFrames==checkEveryNFrames-1){//if it has been checkEveryNFrames frames
			System.out.println(ticks);
			outerloop: //I think this line might be obsolete
			for(int i=0;i<xSize;i++){//x iteration
				for(int j=0;j<ySize;j++){//y iteration
					if(oldGame[i][j] == updatedGame[i][j]){//if the pixel is the same in oldGame and updatedGame, it is dead
						
						dead++;
						
						
					}
					
					
					
					
				}
			}
			
			if((dead==xSize*ySize||ticks >= tickReset)&&deadReset){//this code is a mess, make a new function to reset the board or something
				dead = 0;//the code above checks to see if the board is dead (if every pixel is dead) or if the ticks are more than the tick reset
				ticks = 0;
				if(singlePixel){//restarts based on single pixel
					for(int i=0;i<xSize;i++){
						for(int j=0;j<ySize;j++){
							oldGame[i][j] = false;
							updatedGame[i][j] = false;
						}
					}
					updatedGame[xSize/2][ySize/2] = true;
				}else if(singleLine){//restarts based on single line
					for(int i=0;i<xSize;i++){
						for(int j=0;j<ySize;j++){
							oldGame[i][j] = false;
							updatedGame[i][j] = false;
						}
					}
					
					
					for (int i=0;i<xSize;i++){
						updatedGame[i][ySize/2] = true;
						
						
						
					}
					
					
					
				}else if(customPixelLayout){//restarts based on custom pixel layout
					for(int i=0;i<xSize;i++){
						for(int j=0;j<ySize;j++){
							oldGame[i][j] = false;
							updatedGame[i][j] = false;
						}
					}
					
					
					for(int i=0;i<customPixelX.length;i++){
						updatedGame[customPixelX[i]][customPixelY[i]] = true;
					}
					
					
				}else{	//random spawning
					for (int i = 0; i<xSize; i++) {
						for (int j = 0; j<ySize; j++) {
							double yeet = Math.random();
							if (yeet>spawnFactor) {//usually 0.8
								updatedGame[i][j] = true;
							}else {
								updatedGame[i][j] = false;
							}
							
						}
					}
				}
			}	
				
			
			if((dead==xSize*ySize||(mazeFlag==true && ticks>60))&&!deadReset){//if dead or if mazeflag and ticks exceed max maze ticks (usually 60)
				System.out.println("dead");
				updateBoard(true);//true means it's the last tick, and updates the board one last time
				try {
					runner.sleep(threadSleepInMilliSeconds);
				}catch (Exception e) {
					
				}
				paint(getGraphics());
				Thread.currentThread().yield();
				
				System.exit(0);
			
				
				
				
			}
			
			
			
		}
		
		
		
		for (int i =0; i<xSize; i++) {
			for (int j =0; j<ySize; j++) {
				shownGame[i][j] = updatedGame[i][j];
			}
		}
		
	}
	
	public void init() { //contains initialization info
		
		 dim = getSize(); 
		    offscreen = createImage(dim.width,dim.height); 
          // by doing this everything that is drawn by bufferGraphics 
          // will be written on the offscreen image. 
          bufferGraphics = offscreen.getGraphics(); 
		if(singlePixel){
			shownGame[xSize/2][ySize/2] = true;
		}else if(singleLine){
			for (int i=0;i<xSize;i++){
				shownGame[i][ySize/2] = true;
				
				
				
			}
		
		}else if(customPixelLayout){
			for(int i=0;i<customPixelX.length;i++){
				shownGame[customPixelX[i]][customPixelY[i]] = true;
			}
		}else{	
			for (int i = 0; i<xSize; i++) {
				for (int j = 0; j<ySize; j++) {
					double yeet = Math.random();
					if (yeet>spawnFactor) {//usually 0.8
						shownGame[i][j] = true;
					}else {
						shownGame[i][j] = false;
					}
					
				}
			}
		}
	}
	
	
	public void start() {//thread starter
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}
		
	}
	public void run() {//thread runner
		while(true) {
			
			updateBoard(false);
			paint(getGraphics());
			Thread.currentThread().yield();
			try {
				runner.sleep(threadSleepInMilliSeconds);
			}catch (Exception e) {
				
			}
			
			
		}
		
		
	}
	
	
	
	public void paint(Graphics g) {//paints graphics
		if(ticks%frameSkip==0){
			bufferGraphics.clearRect(0,0,dim.width,dim.width); 
			for (int i = 0; i<xSize; i++) {
				for (int j = 0; j<ySize; j++) {
					if(shownGame[i][j] == true) {
						bufferGraphics.fillRect(j*pixelSize, i*pixelSize, pixelSize, pixelSize);
					}
					
				}
			}
			g.drawImage(offscreen,2*pixelSize,2*pixelSize,this); 
		}
	}
	
}