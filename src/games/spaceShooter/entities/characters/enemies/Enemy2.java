package games.spaceShooter.entities.characters.enemies;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceShooter.entities.characters.Player;
import games.spaceShooter.entities.projectiles.Projectile;
import games.spaceShooter.entities.projectiles.StraightProjectile;

public class Enemy2 extends Enemy{

	private double startX, startY; //Coordonnees autour desquelles le mouvement s'effectue
	private double range; //range du deplacement
	private Image image;
	private boolean place;
	private String nameSprite = "/images/spaceShooter/projectiles/shot.png";

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
		image=AppLoader.loadPicture("/images/spaceShooter/ships/enemy2.png");
		image=image.getScaledCopy(.5f);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		arg2.setColor(Color.red);
		arg2.drawImage(image, (float)(x+width/2-image.getWidth()/2), (float)(y+height/2-image.getHeight()/2));
		showLife(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) {
		if(compt>50){
			compt=0;
			projectiles.add(new StraightProjectile(nameSprite, x+width/2,y+height/2,2,player,false,0,0.2));
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
