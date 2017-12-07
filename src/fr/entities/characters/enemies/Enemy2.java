package fr.entities.characters.enemies;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;
import fr.entities.projectiles.Projectile;
import fr.entities.projectiles.StraightProjectile;

public class Enemy2 extends Enemy{
	
	private double startX, startY; //Coordonnees autour desquelles le mouvement s'effectue 
	private double range; //range du deplacement
	private Image image;
	private boolean place;
	

	public Enemy2(double x, double y,double range,double startX,double startY, Player player, ArrayList<Projectile> projectiles) {
		super(x, y, player,projectiles);
		compt=0;
		this.speedY=0.25;
		this.life=10;
		this.lifeInit=10;
		this.startX = startX;
		this.startY = startY;
		this.range=range;
		this.width=45;
		this.height=65;
		this.score=20;
		this.place=false;
		try {
			image=new Image("img/ship/enemy2.png");
			image=image.getScaledCopy((float) 0.5);
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
	}
	
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.red);
		arg2.drawImage(image, (float)(x+width/2-image.getWidth()/2), (float)(y+height/2-image.getHeight()/2));
		showLife(arg2);
	}
	

	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		if(compt>50){
			compt=0;
			projectiles.add(new StraightProjectile(x+width/2,y+height/2,2,player,false,0,0.2));
		}
		compt++;
		
		if (!(this.place)) {
			whereToGo(speedX, startX, this.x, dirX, 'x');
			whereToGo(speedY, startY, this.y, dirY, 'y');
			this.place=!((dirX != 'a') || (dirY != 'a'));
			if (this.place) {
				speedY=0.25;
			}
		}
		else                 // Sinon on suit le patern
			move();
		
		moveX(delta);
		moveY(delta);
		colProj();
	}


	public void move(){
		if((Math.abs(this.y-this.startY) > range)&&(this.y<this.startY))
			speedY = 0.25;
		else if((Math.abs(this.y-this.startY) > range)&&(this.y>this.startY))
			speedY = -0.25;
	}
	
	
	
}