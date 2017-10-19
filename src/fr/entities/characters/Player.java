package fr.entities.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Circle;

public class Player implements Circle{

	public Player(double centerPointX, double centerPointY, double radius) {
	}
	
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
	}
	

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
	}


	@Override
	public double getX() {
		return 0;
	}


	@Override
	public double getY() {
		return 0;
	}


	@Override
	public int getRadius() {
		return 0;
	}
	
	private void move() {
		//Pour determiner les vitesses horizontales et verticales
	}
	
	public void keyReleased(int key, char c) {
		
	}
	
	public void keyPressed(int key, char c) {
		
	}
}
