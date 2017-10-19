package fr.entities.projectiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.Movable;
import fr.entities.characters.Player;
import fr.util.Circle;

public class StraightProjectile extends Movable implements Circle{

	private double radius;
	
	public StraightProjectile(double centerPointX, double centerPointY, double radius,Player player) {
		
	}
	
	@Override
	public int getRadius() {
		return 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}
	
	public boolean colPlayer() {
		//Renvoie true si il y a collision avec le vaisseau du joueur
		return true;
	}

}
