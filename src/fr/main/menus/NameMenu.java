package fr.main.menus;


import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.main.World;
import general.utils.FontUtils;


public class NameMenu extends BasicGameState{

	public static int ID=19;
	private int compt;
	private TrueTypeFont font;
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		compt =0;
		font=FontUtils.loadFont("space_age.ttf",Font.PLAIN,55,false);
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame game) throws SlickException {
		game.addState(new WelcomeMenu());
		game.addState(new MenuPrincipal());
		game.addState(new World());
		game.addState(new Logo());
		//game.addState(new ScoreMenu(game));
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("We need a name !",(1280-font.getWidth("Rogue Like A Virgin"))/2 , (720-font.getHeight("We need a name !"))/2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2) throws SlickException {
		compt++;
		if(compt>200) {
			game.enterState(Logo.ID,new FadeOutTransition(),new FadeInTransition());
		}
		
		if (compt==2) {
			game.getState(WelcomeMenu.ID).init(arg0, game);
			game.getState(MenuPrincipal.ID).init(arg0, game);
			game.getState(World.ID).init(arg0, game);
			game.getState(Logo.ID).init(arg0, game);
			//game.getState(ScoreMenu.ID).init(arg0, game);
		}
	}

	@Override
	public int getID() {
		return ID;
	}

	
}
