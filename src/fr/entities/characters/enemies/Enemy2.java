package fr.entities.characters.enemies;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;
import fr.entities.projectiles.Projectile;
import fr.entities.projectiles.StraightProjectile;

public class Enemy2 extends Enemy{
	
	private boolean isArrived = false;
	private double startX, startY, signeDiffX, signeDiffY,range;
	/*
	 * StartY,StartX sont les cooredonnées du point autour duquel le mouvement est effectué
	 * range est la distance max entre le point (startX,startY) et l'ennemi
	 */
	
	public Enemy2(double x, double y,double range, double startX, double startY, Player player, ArrayList<Projectile> projectiles) {
		super(x, y, player,projectiles);
		this.startX = startX;
		this.startY = startY;
		this.signeDiffX = (x-startX)/Math.abs(x-startX);
		this.signeDiffY = (y-startY)/Math.abs(y-startY);
		compt=0;
		this.range=range;
		this.life=35;
		this.lifeInit=50;
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.red);
		arg2.fillRect((float)x, (float)y, (float)width, (float)height);
		showLife(arg2);
	}
	

	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		if(compt>50){
			compt=0;
			projectiles.add(new StraightProjectile(x+width/2,y+height/2,2,player,false,0,0.2));
		}
		compt++;
		
		move(delta);
		moveX(delta);
		moveY(delta);
		colProj();
		
	}


	public void move(int delta){
		if(isArrived) {
			if((Math.abs(this.y-this.startY) > range)&&(this.y<this.startY))
				speedY = 0.25;
			else if((Math.abs(this.y-this.startY) > range)&&(this.y>this.startY))
				speedY = -0.25;
		} else {
			if(this.y < startY)
				speedY = 0.25;
			else if(this.y > startY)
				speedY = -0.25;
			if(this.x < startX)
				speedX = 0.25;
			else if (this.x > startX)
				speedX = -0.25;
			
			if((x-startX)/Math.abs(x-startX) == -signeDiffX)
				speedX = 0;
			if((y-startY)/Math.abs(y-startY) == -signeDiffY)
				speedY = 0;
			if((x-startX)/Math.abs(x-startX) == -signeDiffX && (y-startY)/Math.abs(y-startY) == -signeDiffY) {
				isArrived = true;
				speedY = 0.25;
			}
		}
	}
}
