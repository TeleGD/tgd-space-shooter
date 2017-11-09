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

public class EnemyRandom extends Enemy{
	
	
	private double xbox;
	private double ybox;
	private double widthbox;
	private double heightbox;
	private double angle;
	private double oldAngularSpeed;
	private double angularSpeed;
	private double speed;
	private double alea;
	private double va; //vitesse angulaire fixée
	
	public EnemyRandom(double x, double y, Player player, ArrayList<Projectile> projectiles, double xbox, double ybox, double widthbox, double heightbox) {
		super(x, y, player,projectiles);
		this.angle=0;
		this.speed=0.25;
		this.oldAngularSpeed=0;
		this.angularSpeed=0;
		this.va=0.005;
		compt=0;
		this.life=35;
		this.lifeInit=50;
		this.xbox=xbox;
		this.ybox=ybox;
		this.widthbox=widthbox;
		this.heightbox=heightbox;
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
	
	public void move(int delta) {
		alea=Math.random();
		angularSpeed=oldAngularSpeed;
		
		if (x>=xbox && x<=xbox+widthbox && y>=ybox && y<=ybox+heightbox ) {
			if (oldAngularSpeed==0) {
				if (alea<0.1) angularSpeed=va;
				if (alea>0.9) angularSpeed=-va;
			}
			if (oldAngularSpeed==va) {
				if (alea<0.02) angularSpeed=0;
				if (alea>0.98) angularSpeed=-va;
			}
			if (oldAngularSpeed==-va) {
				if (alea<0.02) angularSpeed=0;
				if (alea>0.98) angularSpeed=va;
			}
		} else {
			angularSpeed=va;
		}
		
		oldAngularSpeed=angularSpeed;
		angle+=angularSpeed*delta;
		speedX = this.speed*Math.cos(angle);
		speedY= this.speed*Math.sin(angle);
	}
}
