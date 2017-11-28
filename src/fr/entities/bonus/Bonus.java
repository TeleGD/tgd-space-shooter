package fr.entities.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;
import fr.util.Circle;

public class Bonus extends fr.entities.Movable implements Circle {
	
	private int radius;
	private Player p;
	private boolean destructed;
	private int type;
	
	public Bonus(double x, double y, int radius, Player player, int numeroBonus) {
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.speedX=0;
		this.speedY=0.2;
		this.destructed=false;
		p = player;
		this.type=numeroBonus;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.yellow);
		// g.drawImage(image, (float)(x-image.getWidth()/2), (float)(y-image.getHeight()/2));
		g.fillOval((float)(x-radius),(float)(y-radius),(float)(2*radius),(float)(2*radius));
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		y+=speedY*delta;
		if (y>=720+radius) {
			this.destructed = true;
		}
		if (isPicked()) {
			this.destructed = true;
			p.setBonus(this.type);
		}
	}
	

	@Override
	public int getRadius() {
		return radius;
	}
	
	
	public boolean isDestructed() {
		return destructed;
	}
	
	public boolean isPicked() {
		double distance = Math.sqrt( Math.pow(p.getX() - x , 2) + Math.pow(p.getY() - y, 2)) ;
		return (distance <= p.getRadius() + radius);
	}
	
}
