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

public class WelcomeMenu extends BasicGameState {

	public static int ID = 1;
	protected static StateBasedGame game;
	protected GameContainer container;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		this.container = arg0;
		this.game = arg1;
		container.setShowFPS(false);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.white);
		arg2.drawString("PRESS ENTER PLEASE", 550, 350);
		arg2.drawRect(525, 330, 205, 50);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
	}

	@Override
	public int getID() {
		return ID;
	}
	
	
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}else if (key == Input.KEY_ENTER) {
			game.enterState(MenuPrincipal.ID, new FadeOutTransition(),new FadeInTransition());
		}
	}

}
