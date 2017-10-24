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
	
	private Player p;
	
	private boolean isDestructed;
	
	public StraightProjectile(double centerPointX, double centerPointY, double radius,Player player) {
		x = centerPointX;
		y = centerPointY;
		this.radius = radius;
		speedY = 0.5;
		p = player;
		isDestructed = false;
	}
	
	@Override
	public int getRadius() {
		return (int)radius;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (!isDestructed) {
			g.setColor(Color.red);
			g.fillOval((float)(x-radius),(float)(y-radius),(float)(2*radius),(float)(2*radius));
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
		if (colPlayer()) {
			isDestructed = true;
		}
	}
	
	public boolean colPlayer() {
		double distance = Math.sqrt( Math.pow(p.getX() - x , 2) + Math.pow(p.getY() - y, 2)) ;
		return (distance <= p.getRadius() + radius);
	}

}
