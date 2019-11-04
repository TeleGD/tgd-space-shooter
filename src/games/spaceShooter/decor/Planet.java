package games.spaceShooter.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceShooter.entities.Movable;

public class Planet extends Movable{

	private double x,y,speedY;
	private Image image;
	private Boolean destructed;

	public Planet(double x,double y) {
		this.destructed=false;
		this.x=x;
		speedY=0.5;
		this.y=y;
		image=AppLoader.loadPicture("/images/spaceShooter/planets/planet12.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(image, (float)x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		y+=speedY*delta;
		if (y>=1000) {
			destructed=true;
		}
	}

	public Boolean getDestructed() {
		return destructed;
	}

}
