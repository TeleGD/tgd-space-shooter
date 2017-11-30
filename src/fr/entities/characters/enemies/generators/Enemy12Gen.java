package fr.entities.characters.enemies.generators;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;
import fr.entities.characters.enemies.Enemy;
import fr.entities.characters.enemies.Enemy1;
import fr.entities.characters.enemies.Enemy2;
import fr.entities.characters.enemies.EnemyRandom;
import fr.entities.projectiles.Projectile;

/*
 * Generateur d'enemis 1 ou 2:
 * 
 * Genere aleatoirement des enemis 1 et 2 et les fait apparaitre dans le world
 * Ce generateur n'est pas une entite physique et pour cela n'a pas de fonction d'affichage (render)
 */

public class Enemy12Gen {
	
	private double x,y;
	private Player player;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private double alea;
	private int vitesseApparition;
	private int ajoutTemps; //vitesseApparition+ajoutTemps de frames avant le prochain ennemi
	private int compt;//compteur permettant de reguler l'apparition d'enemis
	
	public Enemy12Gen(double x, double y, Player p,ArrayList<Projectile> projo, ArrayList<Enemy> en) {
		this.x=x;
		this.y=y;
		this.player=p;
		this.projectiles=projo;
		this.enemies=en;
		this.alea=0;
		this.vitesseApparition=300;
		this.ajoutTemps =(int) Math.random()*300;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (compt>vitesseApparition+ajoutTemps) {
			compt=0;
			ajoutTemps=(int) Math.random()*ajoutTemps;
			alea=Math.random();
			if(alea>0.666) {
				enemies.add(new Enemy2(x,y,Math.random()*200+40,Math.random()*1160+20,Math.random()*400+20,player,projectiles));
			} else {
				if (alea<0.333) {
					enemies.add(new Enemy1(x,y,Math.random()*400+100,Math.random()*600+50,Math.random()*400+30,player,projectiles));
				} else {
					enemies.add(new EnemyRandom(x,y,player,projectiles,(int) Math.random()*300+50,(int) Math.random()*200+25,(int) Math.random()*700+200,(int) Math.random()*250+150));
				}
				
			}
		}
		compt++;
	}
}
