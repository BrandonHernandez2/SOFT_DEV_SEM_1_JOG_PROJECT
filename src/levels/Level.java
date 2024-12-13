package levels;

public class Level {

	// TODO: create a private int[][] called lvlData
	private int[][] lvlData;

	public Level(int[][] lvlData) {
		// TODO: set this lvlData to lvlData
		this.lvlData = lvlData;
	}

	public int getSpriteIndex(int x, int y) {
		// TODO: lvlData[y][x]
        return lvlData[y][x];
	}

	public int[][] getLevelData() {
		// TODO: return lvlData;
		return lvlData;
	}

}
