package brickbreaker.standards;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import brickbreaker.main.Game;

public class StandardHandler {

private ArrayList<StandardGameObject> entities;

    public StandardHandler(){
	this.entities = new ArrayList<StandardGameObject>();
}

	public void tick(){	
	if(this.countBricksAndDetermineWin()){
	JOptionPane.showMessageDialog(null, "GOOD JOB!! YOU WON! THANKS FOR PLAYING!");
        JOptionPane.showMessageDialog(null, "PLAY AGAIN? JUST RUN THE PROGRAM");
	System.exit(0);
		}
	for(int i = 0; i < entities.size(); i++){
	if(this.entities.get(i).id == StandardID.Player){
	for(int j = 0; j < entities.size(); j++){
        if(entities.get(j).id == StandardID.Ball){
            
	if(entities.get(i).getBounds().intersects(entities.get(j).getBounds())){
	entities.get(j).velY = -entities.get(j).velY;
            }

	}

    }

}

	if(entities.get(i).id == StandardID.Ball){
        for(int j = 0; j < entities.size(); j++){
	if(entities.get(j).id == StandardID.Brick){
	if(entities.get(i).getBounds().intersects(entities.get(j).getBounds())){
	Game.score += 100;		
	entities.remove(j);
	j--;
        entities.get(i).velY = -entities.get(i).velY;
		}
            }

	}
}
	this.entities.get(i).tick();

	}
}

	public void render(Graphics2D g2){
	for(int i = 0; i < entities.size(); i++){
            entities.get(i).render(g2);
	}
}

	public void addEntity(StandardGameObject obj){
	this.entities.add(obj);
	}

	public void removeEntity(StandardGameObject obj){
	for(int i = 0; i < entities.size(); i++){
	if(obj == entities.get(i)){
	entities.remove(i);
	i--;
		}
	}
}

	public void remove(StandardGameObject obj){
	this.entities.remove(obj);
	}
	
	public boolean countBricksAndDetermineWin(){
	int brick = 0;
	for(int i = 0; i < entities.size(); i++){
	if(entities.get(i).id == StandardID.Brick){
	brick++;
		}
	}		
	return brick == 0;
    }
}
