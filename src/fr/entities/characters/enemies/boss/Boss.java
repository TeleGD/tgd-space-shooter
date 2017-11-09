package fr.entities.characters.enemies.boss;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;
import fr.entities.characters.enemies.Enemy;
import fr.entities.projectiles.Projectile;

public class Boss extends Enemy {
	
	
	Boolean invincible;
	protected ArrayList<Enemy> parts;
	
	public Boss(double x, double y, Player player,ArrayList<Projectile> projectiles){
		super(x,y,player,projectiles);
		this.width = 100;
		this.height = 100;
		this.life=50;
		this.lifeInit=50;
		speedX = 0.1;
		
		invincible = true;
		
		parts = new ArrayList<Enemy>();
		
		//tourelle gauche
		parts.add(new BossTurret(x-20, y+height/2, player, projectiles,speedX,speedY,this));
		//tourelle droite
		parts.add(new BossTurret(x+width, y+height/2, player, projectiles,speedX,speedY,this));
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.pink);
		arg2.fillRect((float)x, (float)y, (float)width, (float)height);
		
		if(!invincible){
			showLife(arg2);
		}
		
		for(Enemy e : parts){
			e.render(arg0, arg1, arg2);
				if(!e.isDestructed())
			invincible = true;
		}
		
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		move(delta);
		
		invincible=false;
		
		for(Enemy e : parts){
			e.update(arg0, arg1, delta);
				if(!e.isDestructed())
			invincible = true;
		}
			
		if(!invincible){
			colProj();
		}
		
	}
	
	public void move(int delta){
		if(this.x+width >= 1280)
			this.speedX=-0.1;
		if(this.x < 0)
			this.speedX=0.1;
		
		moveX(delta);
		moveY(delta);
	}

}
