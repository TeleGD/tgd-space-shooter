package fr.entities.characters.enemies;

import java.util.ArrayList;

import fr.entities.characters.Player;
import fr.entities.projectiles.Projectile;

public class Enemy2 extends Enemy{
	
	
	public Enemy2(double x, double y, Player player, ArrayList<Projectile> projectiles) {
		super(x, y, player);
		this.projectiles=projectiles;
		compt=0;
		this.speedX=0.25;
		this.life=35;
		this.lifeInit=50;
	}
}
