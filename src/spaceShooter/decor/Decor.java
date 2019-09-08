package spaceShooter.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class Decor {

	private Image[] background;
	private float y;
	private int i;

	public Decor() {//ArrayList<Planet> planets) {
		//this.planets=new Image[0];
		background=new Image[4];
		for (int i=0;i<4;i++) {
			background[i]=AppLoader.loadPicture("/images/backgrounds/Space_"+(i+1)+".png");
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		/*if(Math.random()>0.98) {
			createPlanet();
		}
		/*for (Planet p : planets) {
			p.update(container, game, delta);
		}
		for (Iterator<Planet> it = planets.iterator();it.hasNext();) {
			if (it.next().getDestructed()) {
				it.remove();
			}
		}*/
		y++;
		if(y>750) {
			y-=background[i].getHeight();
			i=(i+1)%4;
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/*for (Planet p : planets) {
			p.render(container, game, g);
		}*/
		context.drawImage(background[i],0,y);
		if (y>-20) {
			context.drawImage(background[(i+1)%4], 0, y-background[i].getHeight());
		}
	}

	/*private void createPlanet() {
		planets.add(new Planet(Math.random()*1280,-500));
	}*/

	public void reset() {
		i=0;
		y=0;
	}

}
