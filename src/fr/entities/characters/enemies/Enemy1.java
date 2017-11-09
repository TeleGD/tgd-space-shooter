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

public class Enemy1 extends Enemy{
	
	private double startX, startY; //Coordonnees autour desquels le mouvement s'effectue
	private char dirX,dirY; 
	private double range; //range du deplacement
	

	public Enemy1(double x, double y,double range,double startX,double startY, Player player, ArrayList<Projectile> projectiles) {
		super(x, y, player,projectiles);
		compt=0;
		this.speedX=0.25;
		this.life=50;
		this.lifeInit=50;
		this.startX = startX;
		this.startY = startY;
		this.dirX = 'n';
		this.dirY = 'n';
		this.range=range;
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
	
		if ((dirX != 'a') && (dirY != 'a')) {  // Si on est encore en phase de placement :
			whereToGo(speedX, startX, this.x, dirX, 'x');
			whereToGo(speedY, startY, this.y, dirY, 'y');
		}
		else                 // Sinon on suis le patern
			move();
		
		moveX(delta);
		moveY(delta);
	}


	public void move(){
		if((Math.abs(this.x-this.startX) > range)&&(this.x<this.startX))
			speedX = 0.25;
		else if((Math.abs(this.x-this.startX) > range)&&(this.x>this.startX))
			speedX = -0.25;
	}
	
	public void whereToGo(double speed, double depart,double position, char dir,char axe) {
		if (position < depart ) {   //On est à gauche de l'objectif, il faut aller vers la droite
			speed = 0.25;
			if (dir == 'g') {  // Si on devait aller à gauche, c'est qu'on vient de dépasser notre objectif
				dir = 'a';  //on est arrivé
				speed = 0; // on arrête de bouger dans cette direction
			}
			else
				dir = 'd';
			
		}
		else {                   // Sinon on est à droite et on doit aller à gauche
			speed = -0.25;	
			if (dir == 'd') {
				dir = 'a';
				speed = 0;
			}
			else
				dir = 'g';
		}
		if (axe == 'x') {   // On regarde sur quel axe il faut appliquer nos calculs
			dirX = dir;
			speedX = speed;
		} else {
			dirY = dir;
			speedY = speed;
		}
			
	}
	
}