package fr.entities.characters.enemies.boss;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;
import fr.entities.characters.enemies.Enemy;
import fr.entities.projectiles.Projectile;
import fr.entities.projectiles.StraightProjectile;

public class BossTurret extends Enemy {
	
	double nextProjY;
	double projY;
	private Image img;
	private float bossX,bossY;
	
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
		
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		if(!this.isDestructed()){
			arg2.setColor(Color.pink);
			//arg2.fillRect((float)x, (float)y, (float)width, (float)height);
			showLife(arg2);
			arg2.drawImage(img, bossX-25, bossY-50);
		}
		
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		if(!isDestructed()){
			if(compt>20){
				compt=0;
				projectiles.add(new StraightProjectile(x+width/2,y+height/2,2,player,false,projY,Math.sqrt(0.20-projY*projY)));
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
