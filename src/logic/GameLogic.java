package logic;

import java.util.ArrayList;
import java.util.List;
import logic.StoneTile;
import render.RenderableHolder;

public class GameLogic {
	public List<Entity> gameContainer;
	public Player player;
	public Cross cross;
	public Ghost1 ghost1;
	public Ghost2 ghost2;
	public Ghost3 ghost3;
	public Potion potion;
	public StoneTile tile;
	public MapPoint mapPoint;

	public GameLogic() {
		this.gameContainer = new ArrayList<Entity>();
		player = new Player(375, 320);
		cross = new Cross();
		ghost1 = new Ghost1();
		ghost2 = new Ghost2();
		ghost3 = new Ghost3();
		mapPoint = new MapPoint();
		potion = new Potion();
		tile = new StoneTile();
		addObject(player);
		addObject(potion);
		addObject(cross);
		addObject(ghost1);
		addObject(ghost2);
		addObject(ghost3);
		addObject(tile);
	}

	protected void addObject(Entity entity) {
		gameContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void logicUpdate() {
		for (Entity e : this.gameContainer) {
			if (!(e instanceof StoneTile) && !e.isDestroy()) {
				player.update(e);
			}
		}
		player.walk(tile);
		ghost1.walk();
		ghost2.walk();
		ghost3.walk();

	}

}
