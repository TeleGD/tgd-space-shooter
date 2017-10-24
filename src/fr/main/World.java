package fr.main;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;
import fr.entities.characters.enemies.Enemy;
import fr.entities.characters.enemies.Enemy1;


public class World extends BasicGameState {

	public static int ID = 0;
	private static Player Nico;
	private ArrayList<Enemy> enemies;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		Nico=new Player(100,100,16);
		enemies=new ArrayList<Enemy>();
		enemies.add(new Enemy1(100,100,Nico));
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		Nico.render(arg0, arg1, arg2);
		for(Enemy e:enemies)
			e.render(arg0, arg1, arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Nico.update(arg0, arg1, arg2);
		for(Enemy e:enemies)
			e.update(arg0, arg1, arg2);
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
		Nico.keyPressed(key, c);
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

	

}
