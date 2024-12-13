package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import main.GamePanel;
import utilz.LoadSave;

public class LevelManager {

	// TODO: all fields are private
	// TODO: create a Game called game
	private Game game;
	// TODO: create a BufferedImage[] called levelSprite
	private BufferedImage[] levelSprite;
	// TODO: create a Level called levelOne
	private Level levelOne;


	public LevelManager(Game game) {
		// TODO: set this game to game
		this.game = game;
		// TODO: call importOutsideSprites()
		importOutsideSprites();
		// TODO: set levelOne to newLevel(LoadSave.GetLevelData()
		levelOne = new Level(LoadSave.GetLevelData());
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
	}

	public void update() {

	}

	public Level getCurrentLevel() {
		// TODO: return levelOne
		return levelOne;
	}

}
