package fr.decor;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Planetgen {
	
	private ArrayList<Planet> planets;
	
	public Planetgen(ArrayList<Planet> planets) {
		this.planets=planets;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(Math.random()>0.98) {
			createPlanet();
		}
		for (Planet p : planets) {
	     	p.update(container, game, delta);
		}
		//for (int i = 0;i>planets.size();i++) {
		//	if (planets.get(i).getDestructed()) {
		//		planets.remove(i);
		//	}
		//}
		for (Iterator<Planet> it = planets.iterator();it.hasNext();) {
			if (it.next().getDestructed()) {
					it.remove();
			}
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for (Planet p : planets) {
			p.render(container, game, g);
		}
	}
	
	private void createPlanet() {
		planets.add(new Planet(Math.random()*1280,-500));
	}
}
