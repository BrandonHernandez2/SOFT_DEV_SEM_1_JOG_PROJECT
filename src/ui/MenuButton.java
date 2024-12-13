package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
	// TODO: create private fields
	// TODO: int xPos, yPos, rowIndex, index
	private int xPos, yPos, rowIndex, index;
	// TODO: xOffsetCenter set to B_WIDTH / 2
	private int xOffsetCenter = B_WIDTH / 2;
	// TODO: Gamestate called state
	private Gamestate state;
	// TODO: BufferedImage[] imgs
	private BufferedImage[] imgs;
	// TODO: boolean mouseOver, mousePressed
	private boolean mouseOver, mousePressed;
	// TODO: Rectangle bounds
	private Rectangle bounds;

	public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
		// TODO: set this xPos to xPos
		this.xPos = xPos;
		// TODO: set this yPos to yPos
		this.yPos = yPos;
		// TODO: set this rowIndex to rowIndex
		this.rowIndex = rowIndex;
		// TODO: set this state to state
		this.state = state;
		// TODO: call loadImgs()
		loadImgs();
		// TODO: call initBounds()
		initBounds();
	}

	private void initBounds() {
		// TODO: set bounds to newRectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT)
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
	}

	private void loadImgs() {
		// TODO: set img to new BufferedImage[3];
		imgs = new BufferedImage[3];
		// TODO: create a BufferedImage called temp and set to LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
	}

	public void draw(Graphics g) {
		// TODO: call g.drawImage passing in
		// TODO: imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null)
		g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}

	public void update() {
		// TODO: set index to 0
		index = 0;
		// TODO if mouseOver
		// TODO: set index to 1
		if (mouseOver)
			index = 1;
		// TODO: end of if statement
		// TODO: if mousePressed
		// TODO: set index to 2
		if (mousePressed)
			index = 2;
	}

	public boolean isMouseOver() {
		// TODO: return mouseOver
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		// TODO: set this mouseOver to mouseOver
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		// TODO: return mousePressed
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		// TODO: set this mousePressed to mousePressed
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		// TODO: return bounds
		return bounds;
	}

	public void applyGamestate() {
		// TODO: set Gamestate.state to state
		Gamestate.state = state;
	}

	public void resetBools() {
		// set mouseOver and mousePressed to false
		mouseOver = false;
		mousePressed = false;
	}

}
