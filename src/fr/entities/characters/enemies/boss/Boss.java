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
import fr.main.World;

public class Boss extends Enemy {


	Boolean invincible;
	protected ArrayList<BossTurret> parts;
	private Image body;
	int time;
	double startX;
	double startY;
	boolean arrive;
	private String nameSprite = "img/projectiles/bossShot01.png";

	public Boss(double x, double y, Player player,ArrayList<Projectile> projectiles,Image[] img){
		super(x,y,player,projectiles);
		
		this.x=-100;
		this.y=-100;
		arrive = false;
		startX = x;
		startY = y;
		
		World.addEnemy(this);
		time=0;
		this.width = 100;
		this.height = 100;
		this.life=50;
		this.lifeInit=50;
		speedX = 0.1;
		this.body=img[0];
		this.score=100;
		invincible = true;

		parts = new ArrayList<BossTurret>();

		//tourelle gauche
		parts.add(new BossTurret(this.x-20, this.y+height/2, player, projectiles,speedX,speedY,this,img[1]));
		//tourelle droite
		parts.add(new BossTurret(this.x+width, this.y+height/2, player, projectiles,speedX,speedY,this,img[2]));
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.pink);
		//arg2.fillRect((float)x, (float)y, (float)width, (float)height);
		arg2.drawImage(body, (float)x-25, (float)y-50);

		if(!invincible){
			showLife(arg2);
		}

		for(BossTurret e : parts){
			e.setBossPos((float)x, (float)y);
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
			shoot();
		}

	}

	private void shoot() {

		if (time>50) {
			time=0;
			projectiles.add(new StraightProjectile(nameSprite, x+width/2-10, y+height, 2, player, false,0,0.5));
			
			projectiles.add(new StraightProjectile(nameSprite, x+width/2+10, y+height, 2, player, false,0	,0.5));
		}
		time++;
	}

	public void move(int delta){
		if(!arrive)
			entry();
		
		if(this.x+width >= 1280)
			this.speedX=-0.1;
		if(this.x < 0)
			this.speedX=0.1;

		moveX(delta);
		moveY(delta);
	}
	
	public void entry(){
		if(x<startX)
			speedX=0.1;
		else
			speedX=-0.1;
		
		if(y<startY)
			speedY=0.1;
		else
			speedY=-0.1;

		
		if((Math.abs(x-startX) < 1 && Math.abs(y-startY) < 1)){
			arrive = true;
			speedX =0.1;
			speedY=0;
		}
	}


}
