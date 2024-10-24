package brickbreaker.main;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import brickbreaker.standards.StandardGameObject;
import brickbreaker.standards.StandardHandler;
import brickbreaker.standards.StandardID;

public class Ball extends StandardGameObject{

public Ball(double x, double y, StandardHandler sh){
	super(x, y, StandardID.Ball);
	sh.addEntity(this);
	this.width = 30;
	this.height = 30;	
	this.velX = 20;
	this.velY = -20;
	}

	@Override
	public void tick() {	
	if(this.x < 0 || this.x >= Game.window.width() - this.width){
	this.velX = -this.velX;
}
		
	if(this.y < 0){
	this.velY = -this.velY;
}		
	if(this.y >= Game.window.height()){	
	JOptionPane.showMessageDialog(null, "GAME OVER! YOUR SCORE IS:  "+Game.score);
        JOptionPane.showMessageDialog(null, "PLAY AGAIN? JUST RUN THE PROGRAM");
	System.exit(0);
}	
	this.x += this.velX;
	this.y += this.velY;			
}
	@Override
	public void render(Graphics2D g2) {
	g2.setColor(Color.cyan);
	g2.fillOval((int) x, (int) y, (int) this.width, (int) this.height);	
		
	}
}
