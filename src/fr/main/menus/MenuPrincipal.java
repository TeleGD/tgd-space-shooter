package fr.main.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.main.World;


public class MenuPrincipal extends BasicGameState {
	public static int ID = 2;
	int choix = 0;
	protected static StateBasedGame game;
	protected GameContainer container;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		this.container = arg0;
		container.setShowFPS(false);
		game=arg1;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		afficheMenus(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
	}

	@Override
	public int getID() {
		return ID;
	}
	
	public void afficheMenus(Graphics arg2) {
		String[] menus = {"JOUER","HIGH SCORE","CREDITS","QUITTER"};
		
		for (int i = 0; i < menus.length; i++) {
			if (i == choix) {arg2.setColor(Color.green);}
			else {arg2.setColor(Color.white);}
			arg2.drawString(menus[i], 600, 50 + i*155);
			arg2.drawRect(525, 45+i*155, 205, 50);
		}
	}
	
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
		else if (key == Input.KEY_DOWN){
			choix = Math.min(choix +1, 3) ;
		}
		else if (key == Input.KEY_UP){
			choix = Math.max(choix -1, 0) ;
		}
		else if (key == Input.KEY_ENTER) {
			switch (choix) {
			case 0 : 
				World.reset();
				game.enterState(World.ID, new FadeOutTransition(),new FadeInTransition());
			break;
			case 3 : System.exit(0);
			break;
			}
		}
	}

}

