package fr.entities.characters.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.Movable;
import fr.entities.characters.Player;
import fr.util.Rectangle;

public class Enemy1 extends Enemy{
	
	
	


	public Enemy1(double x, double y, Player player) {
		super(x, y, player);
	}
	
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.red);
		arg2.fillRect((float)x, (float)y, (float)width, (float)height);
	}
	

	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		move(delta);
		moveX(delta);
		moveY(delta);
	}


	public void move(int delta){
		if(this.x < 0)
			speedX = -speedX;
		if(this.x > 1280-width)
			speedX = -speedX;
		this.x+=speedX*delta;
		
	}
	
}
