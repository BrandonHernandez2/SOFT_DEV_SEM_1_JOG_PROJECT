package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {

	private MenuButton[] buttons = new MenuButton[3];
	private BufferedImage backgroundImg;
	private int menuX, menuY, menuWidth, menuHeight;

	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
		menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (45 * Game.SCALE);
	}

	private void loadButtons() {
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
		buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 1, Gamestate.QUIT);
	}

	@Override
	public void update() {
		// TODO: this is an enhanced for loop or a foreach loop
		for (MenuButton mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
		// TODO: for each MenuButton mb in loadButtons()
		// TODO: call mb.draw(g)
		if (MenuButton = true){
		mb.draw(g);}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO: for each MenuButton mb in buttons
		// TODO: if isIn(e, mb) then call mb.setMousePressed(true)
	}

	@Override
	public void mouseReleased(MouseEvent e) {
			// TODO: foreach MenuButton mb  in buttons
			// TODO: if isIn(e, mb) then if (mb.isMousePressed()) call mb.applyGameState() and break
			// TODO: if isIn(e, mb)
			// TODO: if (mb.isMousePressed())
			// TODO: call mb.applyGameState()
			// TODO: break
		// TODO: call resetButtons()

	}

	private void resetButtons() {
		// TODO: for each MenuButton mb in buttons
		// TODO: call mb.resetBools()
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO: for each MenuButton mb in buttons
		// TODO: call mb.setMouseOver(false)


		// TODO: for each MenuButton mb in buttons
		// TODO: if isIn(e, mb) then call mb.setMouseOver(false) and then break;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
				Gamestate.state = Gamestate.PLAYING;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}