package fr.entities.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;

public class StraightProjectile extends Projectile{


	private Image sprite;

	public StraightProjectile(String nameSprite, double centerPointX, double centerPointY, int radius,Player player,boolean allied,double speedX,double speedY) {
		super(centerPointX,centerPointY,radius,player,allied);
		this.speedY = speedY;
		this.speedX = speedX;
		float a=(float)Math.acos(speedX);

		if(speedY<0)
			a=-a;
		try {
			sprite=new Image(nameSprite);
			sprite=sprite.getScaledCopy((float) 0.6);
			sprite.rotate((float) (90+a*360/6.28));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (!destructed) {
			g.drawImage(sprite, (float)(x), (float)(y));
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
