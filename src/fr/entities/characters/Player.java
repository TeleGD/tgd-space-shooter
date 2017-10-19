package fr.entities.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Circle;

public class Player extends fr.entities.Movable implements Circle{
	
	private int radius;
	private boolean upPress,downPress,leftPress,rightPress;
	private double newX,newY;

	public Player(double centerPointX, double centerPointY, int radius) {
		this.x=centerPointX;
		this.y=centerPointY;
		this.radius=radius;
	}
	
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.fillOval((float)(x-radius),(float)( y-radius),(float)( 2*radius),(float)( 2*radius));
	
	}
	

	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		move();
		x+=speedX*delta;
		y+=speedY*delta;
		newX=x+speedX;
		newY=y+speedY;
	}


	@Override
	public double getX() {
		return x;
	}


	@Override
	public double getY() {
		return y;
	}


	@Override
	public int getRadius() {
		return radius;
	}
	
	private void move() {
		//Pour determiner les vitesses horizontales et verticales
		
		speedY = 0;
		if (upPress && !downPress) {
			if (y > radius) {
				speedY = -1;
			}

		}
		if (!upPress && downPress) {
			if (y < 720 - radius) {
				speedY = 1;
			}
		}
		
		speedX = 0;
		if (leftPress && !rightPress) {
			if (x > radius) {
				speedX = -1;
			}

		}
		if (!leftPress && rightPress) {
			if (x < 1280 - radius) {
				speedX = 1;
			}
		}
		
		if (speedX!=0 && speedY!=0) {
			speedX/=Math.sqrt(2);
			speedY/=Math.sqrt(2);
		}
	}
	
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			upPress = false;
			break;

		case Input.KEY_DOWN:
			downPress = false;
			break;

		case Input.KEY_LEFT:
			leftPress = false;
			break;

		case Input.KEY_RIGHT:
			rightPress = false;
			break;
		}
	}
	
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			upPress = true;
			break;

		case Input.KEY_DOWN:
			downPress = true;
			break;

		case Input.KEY_LEFT:
			leftPress = true;
			break;
		case Input.KEY_RIGHT:
			rightPress = true;
			break;
		}
	}
}
