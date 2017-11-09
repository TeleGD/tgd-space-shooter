package fr.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.Movable;

public class Planet extends Movable{
	
	private double x,y,speedY;
	private Image image;
	private Boolean destructed;
	
	public Planet(double x,double y)
	{
		this.destructed=false;
		this.x=x;
		speedY=0.5;
		this.y=y;
		try {
			image=new Image("img/planet/planet12.png");
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(image, (float)x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		y+=speedY*delta;
		if (y>=1000) {
			destructed=true;
		}
	}
	
	public Boolean getDestructed() {
		return destructed;
	}
}
