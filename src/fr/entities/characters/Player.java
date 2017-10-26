package fr.entities.characters;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.projectiles.Projectile;
import fr.entities.projectiles.StraightProjectile;
import fr.util.Circle;

public class Player extends fr.entities.Movable implements Circle{
	
	private int radius;
	private boolean upPress,downPress,leftPress,rightPress,hautbas,droitegauche;
	private double newX,newY,speedBonus=1;
	private Image image;
	private int HP;
	private ArrayList ProjectileList; 
	private double compt;
	private double speedshoot;
	
	
	public Player(double centerPointX, double centerPointY, int radius, ArrayList<Projectile> ProjectileList) {
		this.x=centerPointX;
		this.y=centerPointY;
		this.radius=radius;
		this.HP = 3;
		this.ProjectileList = ProjectileList;
		this.speedshoot = 0.5;
		try {
			image=new Image("img/planet/En3.png");
			image=image.getScaledCopy((float) 0.05);
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
	}


	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.green);
		arg2.drawImage(image, (float)(x-image.getWidth()/2), (float)(y-image.getHeight()/2));
		arg2.fillOval((float)(x-radius),(float)( y-radius),(float)( 2*radius),(float)( 2*radius));
	}
	

	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		move();
		x+=speedX*delta;
		y+=speedY*delta;
		newX=x+speedX;
		newY=y+speedY;
		if(compt>50){
			compt=0;
			Shoot();
		}
		compt=compt + speedshoot;
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
	
	public void loseHP() {
		HP -= 1;
		if (HP <= 0) {
			System.exit(0);
		}
	}
	
	public void Shoot() {
		
		ProjectileList.add(new StraightProjectile(x,y,2,this,true));
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
}
