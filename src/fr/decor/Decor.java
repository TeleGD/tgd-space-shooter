package fr.decor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Decor {

	private Image[] background;
	private float y;
	private int i;

	public Decor() {//ArrayList<Planet> planets) {
		//this.planets=new Ima;
		background=new Image[4];
		for (int i=0;i<4;i++) {
			try {
				background[i]=new Image("img/background/Space_"+(i+1)+".png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
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

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		/*for (Planet p : planets) {
			p.render(container, game, g);
		}*/
		g.drawImage(background[i],0,y);
		if (y>-20) {
			g.drawImage(background[(i+1)%4], 0, y-background[i].getHeight());
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
