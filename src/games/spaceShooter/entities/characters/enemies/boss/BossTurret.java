package games.spaceShooter.entities.characters.enemies.boss;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.spaceShooter.entities.characters.Player;
import games.spaceShooter.entities.characters.enemies.Enemy;
import games.spaceShooter.entities.projectiles.Projectile;
import games.spaceShooter.entities.projectiles.StraightProjectileAnimated;

public class BossTurret extends Enemy {

	double nextProjY;
	double projY;
	private Image img;
	private float bossX,bossY;
	private ArrayList <String> nameSpriteList;

	Enemy parent;

	public BossTurret(double x, double y, Player player,ArrayList<Projectile> projectiles,double speedX,double speedY, Enemy parent, Image img){
		super(x,y,player,projectiles);
		this.width = 20;
		this.height = 20;
		this.life=10;
		this.lifeInit=10;

		this.nextProjY = 0.1;
		this.projY = 0;
		this.img=img;
		this.parent = parent;
		nameSpriteList = new ArrayList<String>();
		nameSpriteList.add("/images/spaceShooter/projectiles/bossShot00.png");
		nameSpriteList.add("/images/spaceShooter/projectiles/bossShot01.png");
		nameSpriteList.add("/images/spaceShooter/projectiles/bossShot02.png");
		nameSpriteList.add("/images/spaceShooter/projectiles/bossShot03.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		if(!this.isDestructed()){
			arg2.setColor(Color.pink);
			//arg2.fillRect((float)x, (float)y, (float)width, (float)height);
			arg2.drawImage(img, bossX-25, bossY-50);
			showLife(arg2);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) {
		if(!isDestructed()){
			if(compt>20){
				compt=0;
//				projectiles.add(new StraightProjectile(nameSprite, x+width/2,y+height/2,2,player,false,projY,Math.sqrt(0.20-projY*projY)));
				projectiles.add(new StraightProjectileAnimated(nameSpriteList, x+width/2,y+height/2,2,player,false,projY,Math.sqrt(0.20-projY*projY)));
				if(Math.abs(projY) >= 0.4)
					nextProjY=-nextProjY;
				projY+=nextProjY;
			}
			compt++;
			colProj();
		}

		move(delta);
	}

	public void move(int delta){
		this.speedX = parent.getSpeedX();
		this.speedY = parent.getSpeedY();

		moveX(delta);
		moveY(delta);
	}

	public void setBossPos(float x,float y) {
		this.bossX=x;
		this.bossY=y;
	}

}
