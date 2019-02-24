package fr.main.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Logo extends BasicGameState{

	public static int ID=19;
	private int compt;
	private Image img;
	private StateBasedGame game;

	@Override
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		compt =0;
		img = new Image("img/Logo.png");
		this.game=game;
	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame game) throws SlickException {
		game.addState(new WelcomeMenu());
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(img, (1280-img.getWidth())/2, (720-img.getHeight())/2); //1280,720 sont les dimensions de la fenetre. cf class main
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2) throws SlickException {
		compt++;
		if(compt>200) {
			game.enterState(WelcomeMenu.ID,new FadeOutTransition(),new FadeInTransition());
		}

	}

	@Override
	public int getID() {
		return ID;
	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}else if(key == Input.KEY_ENTER){
			game.enterState(WelcomeMenu.ID,new FadeOutTransition(),new FadeInTransition());
		}
	}
}
