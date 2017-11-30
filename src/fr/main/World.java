package fr.main;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.entities.characters.Player;
import fr.entities.characters.enemies.Enemy;
import fr.entities.characters.enemies.Enemy2;
import fr.entities.characters.enemies.boss.Boss;
import fr.entities.characters.enemies.generators.Enemy12Gen;
import fr.entities.projectiles.Projectile;
import fr.main.menus.MenuPrincipal;
import fr.util.Dao;
import fr.decor.Decor;
import fr.entities.bonus.Bonus;

public class World extends BasicGameState {

	public static int ID = 0;
	private static Player Nico;
	private static ArrayList<Enemy> enemies;
	private static ArrayList<Projectile> projectiles;
	private ArrayList<Enemy12Gen> enemieGen;
	private static ArrayList<Bonus> bonus;
	private int nombre_bonus;
	private static int score;
	private static int compt;//compteur qui nous permet de g�rer les �v�nements dans le temps
	private Image bossBody,bossTowerL,bossTowerR;
	private static Decor decor;
	private static String name;
	private StateBasedGame game;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		game=arg1;
		projectiles= new ArrayList<Projectile>();
		Nico=new Player(100,100,8,projectiles);
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
		name="";
		bossBody=new Image("img/ship/BossBody.png");
		bossTowerL=new Image("img/ship/BossTowerL.png");
		bossTowerR=new Image("img/ship/BossTowerR.png");

	}
	
	
	public static void reset() {
		projectiles.clear();
		Nico.reset(100,100,8,projectiles);
		enemies.clear();
		decor.reset();
		enemies.add(new Enemy2(100,100,100,400,300,Nico,projectiles));
		enemies.add(new Enemy2(500,500,70,100,100,Nico,projectiles));
		bonus.clear();
		score=0;
		compt=0;
		name="";
		
		
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		decor.render(arg0, arg1, arg2);
		if(Nico.getHP()>0) {
			Nico.render(arg0, arg1, arg2);
			for(Enemy e:enemies)
				e.render(arg0, arg1, arg2);
			for(Bonus b:bonus)
				b.render(arg0,arg1,arg2);
			for(Projectile p:projectiles)
				p.render(arg0, arg1, arg2);
			arg2.drawString(""+projectiles.size(), 500, 700);
			arg2.drawString("score : "+score, 1100, 15);
		}else {
			arg2.drawString("Score: "+score, 900, 320);
			arg2.drawString("Enter your name: "+name,870,350);
		}

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (Nico.getHP()>0) {
			compt++;
			for(Enemy12Gen e:enemieGen)
				e.update(arg0, arg1, arg2);
			Nico.update(arg0, arg1, arg2);
			for(Enemy e:enemies)
				e.update(arg0, arg1, arg2);
			for(Projectile p:projectiles)
				p.update(arg0, arg1, arg2);
			for (int i=0;i<projectiles.size();i++){
				if(projectiles.get(i).isDestructed()){
					projectiles.remove(i);
				}
			}
			for(Bonus b:bonus)
				b.update(arg0, arg1, arg2);
			for (int i=0;i<bonus.size();i++){
				if(bonus.get(i).isDestructed()){
					bonus.remove(i);
				}
			}
			decor.update(arg0, arg1, arg2);
			for (int i=0;i<enemies.size();i++){
				if(enemies.get(i).isDestructed()){
					if (Math.random() <= 0.2) {
						bonus.add(new Bonus(enemies.get(i).getX()+(enemies.get(i).getWidth()/2),enemies.get(i).getY()+(enemies.get(i).getHight()/2),10,Nico,(int)(nombre_bonus*Math.random()+1)));
					}
					score+=enemies.get(i).getScore();
					enemies.remove(i);
				}
			}
	
			if (compt==500) { //a ce moment on fait apparaitre le boss
				Image i[]={bossBody,bossTowerL,bossTowerR};
				new Boss(100,100,Nico,projectiles,i);
			}
		}


	}

	//Souris*****************************************************************************
	public void mousePressed(int button,int x,int y){
	}


	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		if(Nico.getHP()>0) {
			Nico.keyPressed(key, c);
		} else { 
			name+=c;
			if(key==Input.KEY_ENTER) {
				//Dao.addScore(name, score);
				game.enterState(MenuPrincipal.ID, new FadeOutTransition(), new FadeInTransition());
			}
		}
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	public static Player getPlayer() {
		return Nico;
	}

	public static void setPlayer(Player playerP) {
		Nico = playerP;
	}

	public static void addEnemy(Enemy e) {
		enemies.add(e);
	}



}
