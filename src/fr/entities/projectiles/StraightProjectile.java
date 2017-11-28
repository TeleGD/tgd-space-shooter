package fr.entities.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;

public class StraightProjectile extends Projectile{

	
	public StraightProjectile(double centerPointX, double centerPointY, int radius,Player player,boolean allied,double speedX,double speedY) {
		super(centerPointX,centerPointY,radius,player,allied);
		this.speedY = speedY;
		this.speedX = speedX;
	}
	
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (!destructed) {
			g.setColor(Color.cyan);
			g.fillOval((float)(x-radius),(float)(y-radius),(float)(2*radius),(float)(2*radius));
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
		moveX(delta);
		outOfBounds();
		if((colPlayer())&&(!allied)){
			destructed=true;
			p.loseHP();
		}
	}
	
	
	

}
