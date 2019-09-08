package spaceShooter.entities.projectiles;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import spaceShooter.entities.characters.Player;

public class StraightProjectileAnimated extends Projectile{

	private int state;
	private Image sprite0;
	private Image sprite1;
	private Image sprite2;
	private Image sprite3;
	private ArrayList <String> nameSpriteList;
	private ArrayList <Image> sprites;

	public StraightProjectileAnimated(ArrayList <String> nameSpriteList, double centerPointX, double centerPointY, int radius,Player player,boolean allied,double speedX,double speedY) {
		super(centerPointX,centerPointY,radius,player,allied);
		this.speedY = speedY;
		this.speedX = speedX;
		this.nameSpriteList = nameSpriteList;
		float a=(float)Math.acos(speedX);

		if(speedY<0)
			a=-a;
		sprite0 = loadImage(a,0);
		sprite1 = loadImage(a,1);
		sprite2 = loadImage(a,2);
		sprite3 = loadImage(a,3);

		sprites = new ArrayList<Image>();

		sprites.add(sprite0);
		sprites.add(sprite1);
		sprites.add(sprite2);
		sprites.add(sprite3);
		state = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		if (!destructed) {
			state += 0.1;
			if (state > 39) {
				state = 0;
			}
			context.drawImage(sprites.get(state / 10), (float)(x), (float)(y));
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		moveY(delta);
		moveX(delta);
		outOfBounds();
		if((colPlayer())&&(!allied)){
			destructed=true;
			p.loseHP();
		}
	}

	public Image loadImage(float a,int i) {
		Image sprite=AppLoader.loadPicture(nameSpriteList.get(i));
		sprite=sprite.getScaledCopy((float) 0.6);
		sprite.rotate((float) (90+a*360/6.28));
		return sprite;
	}

}
