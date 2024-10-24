package brickbreaker.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import brickbreaker.standards.StandardHandler;
import javax.swing.JOptionPane;


public class Game extends Canvas implements Runnable{
	private Thread thread;
	private boolean running = false;
	public static Window window;
	private StandardHandler sh;
	private Paddle paddle;
	public static int score = 00;
	private Font font;
        
	public Game(int width, int height){   
	Game.window = new Window(width, height, "Brick Breaker by IC2DA CP112 PROJECT", this);
        JOptionPane.showMessageDialog(null, "WELCOME TO BRICK BREAKER"); 
        JOptionPane.showMessageDialog(null, "USE ARROW KEYS TO MOVE THE PADDLE");
        JOptionPane.showMessageDialog(null, "ENJOY THE GAME.");
	this.sh = new StandardHandler();
	this.paddle = new Paddle(500, 700, this.sh);
	new Ball(400, 600, this.sh);
	new Level("Resources/Levels/level1.txt", this.sh);
	this.font = new Font("Arial", Font.TRUETYPE_FONT, 30);
	this.addKeyListener(paddle);
	this.start();
	}
	
	private synchronized void start(){
	if(running) return;
	else{
	this.running = true;
	this.thread = new Thread(this);
	this.thread.start();
		}
	}
	
	private synchronized void stop(){
	if(!running) return;
	try{
	this.thread.join();
	}catch(Exception e){
	e.printStackTrace();
    }
		
        this.running = false;
	System.exit(0);
	}

	public void run(){
	this.requestFocus();
	while(running){
	try {
	Thread.sleep(17);
	} catch (InterruptedException e) {
	e.printStackTrace();
}
	tick();
	render();
}
		
     this.stop();
}
	
	private void tick(){
	this.sh.tick();	
	}
	
	private void render(){
	BufferStrategy bs = this.getBufferStrategy();	
	if(bs == null){
	this.createBufferStrategy(3);
	return;
}	
	Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();	
	g2.setColor(Color.BLACK);
	g2.fillRect(0, 0, Game.window.width(), Game.window.height());	
	this.sh.render(g2);	
	g2.setColor(Color.WHITE);
        g2.setFont(this.font);
	g2.drawString("Your Score: "+Game.score, 20, 45);	
	g2.dispose();
	bs.show();
	}
	public static void main(String[] args) {
		new Game(800, 800);
	}
}
