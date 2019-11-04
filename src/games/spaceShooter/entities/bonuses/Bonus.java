package games.spaceShooter.entities.bonuses;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.spaceShooter.entities.characters.Player;
import games.spaceShooter.util.Circle;

public class Bonus extends games.spaceShooter.entities.Movable implements Circle {

	private int radius;
	private Player p;
	private boolean destructed;
	private int type;
	private Image spriteBonus;
	/* liste des bonus :
	 * 1 : vie supplementaire
	 * 2 : bouclier
	 * 3 : augmente la cadence de tir
	 * 4 : diminue la cadence de tir
	 * 5 : augmente largeur des tirs
	 * 6 : diminue largeur des tirs
	 * 7 : explosion qui enleve tous les tirs ennemis et degats aux ennemis
	 *
	 * plus de 10, changement de tirs :
	 * 11 : triple tirs
	 * 12 : tirs sinusoidaux
	 * 13 : tirs plus gros et puissants, mais moins de souvent
	 */

	public Bonus(double x, double y, int radius, Player player, int numeroBonus) {
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.speedX=0;
		this.speedY=0.15;
		this.destructed=false;
		p = player;
		this.type=numeroBonus;
		this.spriteBonus=AppLoader.loadPicture("/images/spaceShooter/bonuses/bonus"+this.type+".png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(Color.yellow);
		// context.drawImage(image, (float)(x-image.getWidth()/2), (float)(y-image.getHeight()/2));
		context.fillOval((float)(x-radius),(float)(y-radius),2*radius,2*radius);
		context.drawImage(spriteBonus,(float) (x-spriteBonus.getWidth()/2),(float) (y-spriteBonus.getHeight()/2));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		y+=speedY*delta;
		if (y>=720+radius) {
			this.destructed = true;
		}
		if (isPicked()) {
			this.destructed = true;
			p.setBonus(this.type);
		}
	}

	@Override
	public int getRadius() {
		return radius;
	}

	public boolean isDestructed() {
		return destructed;
	}

	public boolean isPicked() {
		double distance = Math.sqrt( Math.pow(p.getX() - x , 2) + Math.pow(p.getY() - y, 2)) ;
		return (distance <= p.getRadius() + radius);
	}

}
