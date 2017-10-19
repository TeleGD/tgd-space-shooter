package fr.entities.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.Movable;
import fr.entities.characters.Player;
import fr.util.Circle;

public class StraightProjectile extends Movable implements Circle{

	private double radius;
	
	public StraightProjectile(double centerPointX, double centerPointY, double radius,Player player) {
		x = 500;
		y = 500;
		this.radius = radius;
		speedY = 5;
	}
	
	@Override
	public int getRadius() {
		return (int)radius;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillOval((float)(x-radius),(float)(y-radius),(float)(2*radius),(float)(2*radius));
	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
	}
	
	public boolean colPlayer() {
		//Renvoie true si il y a collision avec le vaisseau du joueur
		return true;
	}

}
