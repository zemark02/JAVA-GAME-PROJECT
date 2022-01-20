package logic;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.Entity;
import render.RenderableHolder;

public class Player extends CollidableEntity implements Walkable {
	public static int speed;
	public static boolean collideCross;
	public static boolean collidePotion;
	public static boolean collideGhost1;
	public static boolean collideGhost2;
	public static boolean collideGhost3;
	public int slot;

	public Player(double x, double y) {
		// TODO Auto-generated constructor stub
		setcollidePotion(false);
		setcollideCross(false);
		setcollideGhost1(false);
		setcollideGhost2(false);
		setcollideGhost3(false);
		setSpeed(1);
		setSlot(3);
		this.x = x;
		this.y = y;
	}

	public void forward() {
		this.y += speed;
		setSlot(3);
	}

	public void turnLeft() {
		this.x -= speed;
		setSlot(4);
	}

	public void turnRight() {
		this.x += speed;
		setSlot(1);
	}

	public void backWard() {
		this.y -= speed;
		setSlot(2);
	}

	@Override
	public int getz() {
		// TODO Auto-generated method stub
		return 999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (this.slot == 1) {
			if (Potion.ishasPotion()) {
				gc.drawImage(RenderableHolder.bigPlayerTurnRight, x, y);
			} else {
				gc.drawImage(RenderableHolder.playerTurnRight, x, y);
			}

		} else if (this.slot == 2) {
			if (Potion.ishasPotion()) {
				gc.drawImage(RenderableHolder.bigPlayerBackward, x, y);
			} else {
				gc.drawImage(RenderableHolder.playerBackward, x, y);
			}

		} else if (this.slot == 3) {
			if (Potion.ishasPotion()) {
				gc.drawImage(RenderableHolder.bigPlayerForward, x, y);
			} else {
				gc.drawImage(RenderableHolder.playerForward, x, y);
			}

		} else if (this.slot == 4) {
			if (Potion.ishasPotion()) {
				gc.drawImage(RenderableHolder.bigPlayerTurnLeft, x, y);
			} else {
				gc.drawImage(RenderableHolder.playerTurnLeft, x, y);
			}
		}

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public void update(Entity other) {

		if (this.collideWith(other) && other instanceof Cross) {
			setcollideCross(false);
			Cross other1 = (Cross) other;
			other1.visible = false;
			other1.update();

		} else if ((this.collideWith(other) && other instanceof Potion)) {
			Potion.faster();
			setcollidePotion(true);
			Potion.hasPotion = true;
			Potion other1 = (Potion) other;
			other1.update();
			RenderableHolder.potionSound.play();

		} else if (this.collideWith(other) && other instanceof Ghost1) {
			setcollideGhost1(true);
		}

		else if (this.collideWith(other) && other instanceof Ghost2) {
			setcollideGhost2(true);
		} else if (this.collideWith(other) && other instanceof Ghost3) {
			setcollideGhost3(true);
		}

		if (Cross.getScore() != 0 && Cross.getScore() % 4 == 0 && iscollidePotion()) {
			if (!Potion.ishasPotion()) {
				setcollidePotion(false);
			}

		}

	}

	public void walk(StoneTile tile) {

		if (InputUtility.getKeyPressed(KeyCode.W) && !tile.collideWithAboveStone(this.x, this.y)) {
			backWard();
		} else if (InputUtility.getKeyPressed(KeyCode.S) && !tile.collideWithUnderStone(this.x, this.y)) {
			forward();
		} else if (InputUtility.getKeyPressed(KeyCode.A) && !tile.collideWithLeftStone(this.x, this.y)) {
			turnLeft();
		} else if (InputUtility.getKeyPressed(KeyCode.D) && !tile.collideWithRightStone(this.x, this.y)) {
			turnRight();
		}
	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return (iscollideGhost1() || iscollideGhost3() || iscollideGhost2());
	}

	public static void smaller() {
		Potion.sethasPotion(false);
		setSpeed(1);
	}

	public static boolean iscollideCross() {
		return collideCross;
	}

	public static boolean iscollidePotion() {
		return collidePotion;
	}

	public static boolean iscollideGhost1() {
		return collideGhost1;
	}

	public static boolean iscollideGhost2() {
		return collideGhost2;
	}

	public static boolean iscollideGhost3() {
		return collideGhost3;
	}

	public static void setcollideCross(boolean collideCross) {
		Player.collideCross = collideCross;
	}

	public static void setcollidePotion(boolean collidePotion) {
		Player.collidePotion = collidePotion;
	}

	public static void setcollideGhost1(boolean collideGhost1) {
		Player.collideGhost1 = collideGhost1;
	}

	public static void setcollideGhost2(boolean collideGhost2) {
		Player.collideGhost2 = collideGhost2;
	}

	public static void setcollideGhost3(boolean collideGhost3) {
		Player.collideGhost3 = collideGhost3;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Player.speed = speed;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

}