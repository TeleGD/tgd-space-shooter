package games.spaceShooter.entities.projectiles;

import games.spaceShooter.entities.characters.Player;

public class SinusoidalProjectile extends Projectile{

	public SinusoidalProjectile(double centerPointX, double centerPointY, int radius, Player player,boolean allied) {
		super(centerPointX, centerPointY, radius, player,allied);
	}

}
