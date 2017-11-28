package fr.entities.projectiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.Movable;
import fr.entities.characters.Player;
import fr.util.Circle;

public class Projectile extends Movable implements Circle{

	protected int radius;
	
	protected Player p;
	
	protected boolean destructed,allied;
	
	public Projectile(double centerPointX, double centerPointY, int radius,Player player, boolean allied) {
		x = centerPointX;
		y = centerPointY;
		this.radius = radius;
		speedY = 0.5;
		p = player;
		destructed = false;
		this.allied=allied;
	}
	
	@Override
	public int getRadius() {
		return radius;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	protected void outOfBounds(){
		if (this.speedY>0 && this.y>1300)
			destructed=true;
		if(this.speedY<0 && this.y<-20)
			destructed=true;
	}
	
	public boolean isDestructed(){
		return destructed;
	}
	
	public void setDestructed(boolean destructed) {
		this.destructed=destructed;
	}
	
	public boolean colPlayer() {
		double distance = Math.sqrt( Math.pow(p.getX() - x , 2) + Math.pow(p.getY() - y, 2)) ;
		return (distance <= p.getRadius() + radius);
	}
	
	public boolean isAllied() {
		return allied;
	}
}
