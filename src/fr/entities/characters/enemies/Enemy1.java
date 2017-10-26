package fr.entities.characters.enemies;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.Movable;
import fr.entities.characters.Player;
import fr.entities.projectiles.Projectile;
import fr.entities.projectiles.StraightProjectile;
import fr.util.Rectangle;

public class Enemy1 extends Enemy{
	
	
	
	

	public Enemy1(double x, double y, Player player, ArrayList<Projectile> projectiles) {
		super(x, y, player);
		this.projectiles=projectiles;
		compt=0;
		this.speedX=0.25;
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
			projectiles.add(new StraightProjectile(x+width/2,y+height/2,2,player,false));
		}
		compt++;
		move(delta);
		moveX(delta);
		moveY(delta);
	}


	public void move(int delta){
		if(this.x < 0)
			speedX = 0.25;
		if(this.x > 1280-width)
			speedX = -0.25;
		
		
	}
	
}
