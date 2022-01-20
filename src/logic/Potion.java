package logic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import render.RenderableHolder;
import logic.Player;

public class Potion extends CollidableEntity {
	public static boolean hasPotion;

	public Potion() {

		sethasPotion(false);
		this.x = 190;
		this.y = 140;
	}

	public double randomx() {
		int randomx = (int) (Math.random() * MapPoint.getPoint().size());
		this.x = MapPoint.getPoint().get(randomx).getX();

		return this.x;
	}

	public double randomy() {
		int randomy = (int) (Math.random() * MapPoint.getPoint().size());
		this.y = MapPoint.getPoint().get(randomy).getY();
		return this.y;
	}

	public int getz() {
		// TODO Auto-generated method stub
		return 600;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.PotionImage, x, y);
	}

	public boolean isVisible() {
		return false;

	}

	public boolean isDestroy() {
		return Player.iscollidePotion();
	}

	public void update() {
		randomx();
		randomy();
		if (MapPoint.Field[(int) randomy() / 32][(int) randomx() / 32].equals("8")) {
			this.x = 190;
			this.y = 140;
		}
	}

	public static void faster() {
		Player.speed = 2;
		new Timeline(new KeyFrame(Duration.millis(10000), then -> Player.smaller())).play();
	}

	public static boolean ishasPotion() {
		return hasPotion;
	}

	public static void sethasPotion(boolean hasPotion) {
		Potion.hasPotion = hasPotion;
	}

}
