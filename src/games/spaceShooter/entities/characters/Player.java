package games.spaceShooter.entities.characters;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceShooter.entities.projectiles.Projectile;
import games.spaceShooter.entities.projectiles.StraightProjectile;
import games.spaceShooter.util.Circle;

public class Player extends games.spaceShooter.entities.Movable implements Circle{

	private int radius;
	private boolean upPress,downPress,leftPress,rightPress,hautbas,droitegauche;
	private double speedBonus=0.3;
	private Image image;
	private Image imageInvul;
	private int HP;
	private ArrayList<Projectile> ProjectileList;
	private double compt;
	private double speedshoot;
	private int invulnerability;
	private String nameSprite = "/images/spaceShooter/projectiles/playerShot.png";

	public Player(double centerPointX, double centerPointY, int radius, ArrayList<Projectile> ProjectileList) {
		this.x=centerPointX;
		this.y=centerPointY;
		this.radius=radius;
		this.HP = 10;
		this.ProjectileList = ProjectileList;
		this.speedshoot = 0.5;
		this.invulnerability = 0;
		image=AppLoader.loadPicture("/images/spaceShooter/ships/En3.png");
		image=image.getScaledCopy(.05f);
		imageInvul = AppLoader.loadPicture("/images/spaceShooter/ships/En3Invulnerable.png");
		imageInvul=imageInvul.getScaledCopy(.05f);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		arg2.setColor(Color.green);
		if ( ((invulnerability/10) & 1) == 0){
			arg2.drawImage(image, (float)(x-image.getWidth()/2), (float)(y-image.getHeight()/1.8));
		} else {
			arg2.drawImage(imageInvul, (float)(x-imageInvul.getWidth()/2), (float)(y-imageInvul.getHeight()/1.8));
		}
		//arg2.fillOval((float)(x-radius),(float)( y-radius),(float)( 2*radius),(float)( 2*radius));
		for (int i=0;i<HP;i++) {
			arg2.drawImage(image, i*(10+image.getWidth()), 10);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) {
		move();
		x+=speedX*delta;
		y+=speedY*delta;
		if(compt>5){
			compt=0;
			Shoot();
		}
		compt=compt + speedshoot;
		if (invulnerability>0){
			invulnerability-=1;
		}
	}

	public void setBonus(int bonus) {
		switch (bonus)
		{
		  case 1:
		    System.out.println("Bonus 1");
		    break;
		  case 2:
			System.out.println("Bonus 2");
		    break;
		  case 3:
			System.out.println("Bonus 3");
		    break;
		}
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public int getRadius() {
		return radius;
	}

	public double getSpeedBonus() {
		return speedBonus;
	}

	public void setSpeedBonus(double newSpeedBonus) {
		speedBonus=newSpeedBonus;
	}

	public void upSpeedBonus() {
		setSpeedBonus(speedBonus*1.2);
	}

	public void downSpeedBonus() {
		setSpeedBonus(speedBonus/1.2);
	}

	public int getHP() {
		return HP;
	}

	public void loseHP() {
		if (invulnerability==0){
			HP -= 1;
			invulnerability = 90;
		}

		/*if (HP <= 0) {
			System.out.println("you loose");

			System.exit(0);

		}*/
	}

	public void Shoot() {

		ProjectileList.add(new StraightProjectile(nameSprite, x,y,2,this,true,0,-0.5));
	}

	private void move() {
		//Pour determiner les vitesses horizontales et verticales

		speedY = 0;
		if ((upPress && !downPress) || (upPress && downPress && hautbas)) {
			if (y > radius) {
				speedY = -speedBonus;
			}

		}
		if ((!upPress && downPress) || (upPress && downPress && !hautbas)) {
			if (y < 720 - radius) {

				speedY = speedBonus;
			}
		}

		speedX = 0;
		if ((leftPress && !rightPress) || (leftPress && rightPress && !droitegauche)) {
			if (x > radius) {
				speedX = -speedBonus;
			}

		}
		if ((!leftPress && rightPress) || (leftPress && rightPress && droitegauche)) {
			if (x < 1280 - radius) {

				speedX = speedBonus;
			}
		}
	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			upPress = false;
			break;

		case Input.KEY_DOWN:
			downPress = false;
			break;

		case Input.KEY_LEFT:
			leftPress = false;
			break;

		case Input.KEY_RIGHT:
			rightPress = false;
			break;
		}
	}

	public void keyPressed(int key, char c) {
		if (getHP() <= 0) {
			return;
		}
		switch (key) {
		case Input.KEY_UP:
			upPress = true;
			hautbas=true;
			break;

		case Input.KEY_DOWN:
			downPress = true;
			hautbas=false;
			break;

		case Input.KEY_LEFT:
			leftPress = true;
			droitegauche = false;
			break;
		case Input.KEY_RIGHT:
			rightPress = true;
			droitegauche = true;
			break;
		}
	}

	public void reset(double centerPointX, double centerPointY, int radius, ArrayList<Projectile> ProjectileList) {
		this.HP=5;
		this.x=centerPointX;
		this.y=centerPointY;
		this.radius=radius;
		this.ProjectileList = ProjectileList;
		this.speedshoot = 0.5;
		this.invulnerability = 0;
	}

}
