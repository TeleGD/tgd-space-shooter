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
import fr.util.Rectangle;

public class Enemy extends Movable implements Rectangle{

	protected int height;
	protected int width;
	protected boolean destructed;
	protected Player player;
	protected int life,lifeInit;
	protected ArrayList<Projectile> projectiles;
	protected int compt;
	protected int score;
	protected char dirX,dirY;

	public Enemy(double x, double y, Player player,ArrayList<Projectile> projectiles) {
		this.x=x;
		this.y=y;
		this.height=30;
		this.width=30;
		this.dirX = 'n';
		this.dirY = 'n';
		this.destructed=false;
		this.player=player;
		this.projectiles=projectiles;
	}


	public double getWidth() {
		return width;
	}


	public double getHight() {
		return height;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	}


	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}

	protected void showLife(Graphics g){
		g.setColor(Color.red);
		g.fillRect((float)x, (float)(y+height+10), width, 5);
		g.setColor(Color.green);
		g.fillRect((float)x, (float)(y+height+10), width*life/lifeInit, 5);
	}

	public void loseHP() {
		this.life--;
		if(this.life<=0)
			this.destructed=true;
	}
	
	public boolean isDestructed() {
		return destructed;
	}
	
	public int getScore() {
		return score;
	}

	protected void colProj() {
		for(Projectile p:projectiles) {
			if(p.isAllied()) {
				if((p.getX()>this.x-p.getRadius())&&(p.getX()<this.x+this.width+p.getRadius())&&(p.getY()>this.y-p.getRadius())&&(p.getY()<this.y+this.height+p.getRadius())) {
					p.setDestructed(true);
					loseHP();
				}
			}
		}
	}
	
	protected void whereToGo(double speed, double depart,double position, char dir,char axe) {
		if (position < depart ) {   //On est a gauche de l'objectif, il faut aller vers la droite
			speed = 0.25;
			if (dir == 'g') {  // Si on devait aller a gauche, c'est qu'on vient de depasser notre objectif
				dir = 'a';  //on est arrive
				speed = 0; // on arrete de bouger dans cette direction
			}
			else
				dir = 'd';
			
		}
		else {                   // Sinon on est a droite et on doit aller a gauche
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

