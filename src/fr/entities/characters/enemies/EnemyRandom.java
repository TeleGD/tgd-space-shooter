package fr.entities.characters.enemies;

import java.util.ArrayList;

import fr.entities.characters.Player;
import fr.entities.projectiles.Projectile;

public class EnemyRandom extends Enemy{
	
	
	public EnemyRandom(double x, double y, Player player, ArrayList<Projectile> projectiles) {
		super(x, y, player);
		this.projectiles=projectiles;
		compt=0;
		this.speedX=0.25;
		this.life=35;
		this.lifeInit=50;
	}
}
