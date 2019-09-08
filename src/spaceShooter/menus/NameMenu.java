package spaceShooter.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Font;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

public class NameMenu extends BasicGameState{

	private int ID;

	private int compt;
	private static Font font;

	static {
		NameMenu.font = AppLoader.loadFont("/fonts/space-age.ttf", AppFont.PLAIN, 55);
	}

	public NameMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		compt = 0;
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		compt = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(Color.white);
		context.setFont(font);
		context.drawString("Space Shooter !",(1280-font.getWidth("Space Shooter !"))/2 , (720-font.getHeight("Space Shooter !"))/2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		compt += delta;
		if (compt > 3000) {
			game.enterState(4 /* World */, new FadeOutTransition(), new FadeInTransition());
		}
	}

}
