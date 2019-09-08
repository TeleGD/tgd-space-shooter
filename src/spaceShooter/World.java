package spaceShooter;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import spaceShooter.decor.Decor;
import spaceShooter.entities.bonuses.Bonus;
import spaceShooter.entities.characters.Player;
import spaceShooter.entities.characters.enemies.Enemy;
import spaceShooter.entities.characters.enemies.Enemy2;
import spaceShooter.entities.characters.enemies.boss.Boss;
import spaceShooter.entities.characters.enemies.generators.Enemy12Gen;
import spaceShooter.entities.projectiles.Projectile;

public class World extends BasicGameState {

	private int ID;
	private int state;

	private Player Nico;
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy12Gen> enemieGen;
	private ArrayList<Bonus> bonus;
	private int nombre_bonus;
	private int score;
	private int compt;//compteur qui nous permet de gérer les événements dans le temps
	private Image bossBody,bossTowerL,bossTowerR;
	private Decor decor;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play (container, game);
		} else if (this.state == 2) {
			this.resume (container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause (container, game);
		} else if (this.state == 3) {
			this.stop (container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2 /* Pause */, new FadeOutTransition(), new FadeInTransition());
		}
		if (Nico.getHP()>0) {
			compt++;
			for(Enemy12Gen e:enemieGen)
				e.update(container, game, delta);
			Nico.update(container, game, delta);
			for(Enemy e:enemies)
				e.update(container, game, delta);
			for(Projectile p:projectiles)
				p.update(container, game, delta);
			for (int i=0;i<projectiles.size();i++){
				if(projectiles.get(i).isDestructed()){
					projectiles.remove(i);
				}
			}
			for(Bonus b:bonus)
				b.update(container, game, delta);
			for (int i=0;i<bonus.size();i++){
				if(bonus.get(i).isDestructed()){
					bonus.remove(i);
				}
			}
			decor.update(container, game, delta);
			for (int i=0;i<enemies.size();i++){
				if(enemies.get(i).isDestructed()){
					if (Math.random() <= 0.2) {
						bonus.add(new Bonus(enemies.get(i).getX()+(enemies.get(i).getWidth()/2),enemies.get(i).getY()+(enemies.get(i).getHight()/2),15,Nico,(int)(nombre_bonus*Math.random()+1)));
					}
					score+=enemies.get(i).getScore();
					enemies.remove(i);
				}
			}
			if (compt==500) { //a ce moment on fait apparaitre le boss
				Image i[]={bossBody,bossTowerL,bossTowerR};
				new Boss(this,100,100,Nico,projectiles,i);
			}
		} else if (input.isKeyDown(Input.KEY_ENTER)) {
			this.setState(3);
			game.enterState(1 /* Choice */, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		decor.render(container, game, context);
		if(Nico.getHP()>0) {
			Nico.render(container, game, context);
			for(Enemy e:enemies)
				e.render(container, game, context);
			for(Bonus b:bonus)
				b.render(container,game,context);
			for(Projectile p:projectiles)
				p.render(container, game, context);
			context.drawString(""+projectiles.size(), 500, 700);
			context.drawString("score : "+score, 1100, 15);
		}else {
			context.drawString("Score: "+score, 900, 320);
		}
	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		projectiles= new ArrayList<Projectile>();
		Nico=new Player(600,600,15,projectiles);
		enemies=new ArrayList<Enemy>();
		decor=new Decor();
		enemies.add(new Enemy2(100,100,100,400,300,Nico,projectiles));
		enemies.add(new Enemy2(500,500,70,100,100,Nico,projectiles));
		enemieGen=new ArrayList<Enemy12Gen>();
		enemieGen.add(new Enemy12Gen(1300,350,Nico,projectiles,enemies));
		bonus=new ArrayList<Bonus>();
		nombre_bonus=3;
		score=0;
		compt=0;
		bossBody=AppLoader.loadPicture("/images/ships/BossBody.png");
		bossTowerL=AppLoader.loadPicture("/images/ships/BossTowerL.png");
		bossTowerR=AppLoader.loadPicture("/images/ships/BossTowerR.png");
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
		projectiles.clear();
		Nico.reset(600,600,15,projectiles);
		enemies.clear();
		decor.reset();
		bonus.clear();
		score=0;
		compt=0;
	}

	public void setState (int state) {
		this.state = state;
	}

	public int getState () {
		return this.state;
	}

	@Override
	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
	}

	@Override
	public void keyPressed(int key, char c) {
		Nico.keyPressed(key, c);
	}

	public Player getPlayer() {
		return Nico;
	}

	public void setPlayer(Player playerP) {
		Nico = playerP;
	}

	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

}
