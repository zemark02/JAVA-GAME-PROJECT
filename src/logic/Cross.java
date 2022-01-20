package logic;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Entity;
import render.RenderableHolder;

public class Cross extends CollidableEntity {

	public static int score;

	public Cross() {

		this.x = 170;
		this.y = 200;
		this.score = 0;

	}

	public double randomy() {
		int randomy = (int) (Math.random() * MapPoint.createField().size());
		this.y = MapPoint.createField().get(randomy).getY();
		return y;
	}

	public double randomx() {
		int randomx = (int) (Math.random() * MapPoint.createField().size());
		this.x = MapPoint.createField().get(randomx).getX();
		return x;
	}

	public int getz() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (score == 24) {
			gc.drawImage(RenderableHolder.flagImage, x, y);
		} else {
			gc.drawImage(RenderableHolder.CrossImage, x, y);
		}

	}

	@Override
	public boolean isVisible() {

		return false;
	}

	public void update() {
		if (!this.visible) {
			RenderableHolder.ding.play();
			score += 1;
			this.x = randomx();
			this.y = randomy();
			if (MapPoint.Field[(int) this.y / 32][(int) this.x / 32].equals("8")) {
				this.x = 170;
				this.y = 200;
			}
		}
	}

	public boolean isDestroy() {
		return Player.collideCross;
	}

	public static int getScore() {
		return score;
	}

}
