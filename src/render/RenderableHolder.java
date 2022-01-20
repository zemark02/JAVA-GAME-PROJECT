package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import logic.Cross;
//import logic.NormalTile;
import logic.Player;
import logic.Potion;
import logic.StoneTile;

public class RenderableHolder {
	private List<IRenderable> entities;
	private static final RenderableHolder instance = new RenderableHolder();
	private Comparator<IRenderable> comparator;
	public static Image TileImage;
	public static Image GhostImage1;
	public static Image GhostImage2;
	public static Image GhostImage3;
	public static Image PotionImage;
	public static Image CrossImage;
	public static Image flagImage;
	public static Image playerForward;
	public static Image playerBackward;
	public static Image playerTurnLeft;
	public static Image playerTurnRight;
	public static Image bigPlayerForward;
	public static Image bigPlayerBackward;
	public static Image bigPlayerTurnLeft;
	public static Image bigPlayerTurnRight;

	public static Image floor;
	public static AudioClip ding;
	public static AudioClip click;
	public static AudioClip gameOver;
	public static AudioClip start;
	public static AudioClip gameSound;
	public static AudioClip potionSound;
	public static AudioClip winsound;
	public static Font font;
	public static Font font2;
	public static Font font3;

	static {
		loadResource();
	}

	public RenderableHolder() {
		// TODO Auto-generated constructor stub

		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getz() > o2.getz())
				return 1;
			return -1;
		};

	}

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		for (IRenderable x : entities) {
			if (x instanceof Player)
				System.out.println("player");
			if (x instanceof Cross)
				System.out.println("cross");
			if (x instanceof StoneTile)
				System.out.println("stoneTile");
			if (x instanceof Potion)
				System.out.println("potion");

		}

	}

	public static void loadResource() {
		TileImage = new Image(ClassLoader.getSystemResource("tile.jpg").toString());
		GhostImage1 = new Image(ClassLoader.getSystemResource("ghost1.png").toString());
		GhostImage2 = new Image(ClassLoader.getSystemResource("ghost2.png").toString());
		GhostImage3 = new Image(ClassLoader.getSystemResource("ghost3.png").toString());
		PotionImage = new Image(ClassLoader.getSystemResource("potion.png").toString());
		CrossImage = new Image(ClassLoader.getSystemResource("cross.png").toString());
		playerForward = new Image(ClassLoader.getSystemResource("girl3.png").toString());
		playerBackward = new Image(ClassLoader.getSystemResource("girl2.png").toString());
		playerTurnLeft = new Image(ClassLoader.getSystemResource("girl4.png").toString());
		playerTurnRight = new Image(ClassLoader.getSystemResource("girl1.png").toString());
		bigPlayerForward = new Image(ClassLoader.getSystemResource("girl3Big.png").toString());
		bigPlayerBackward = new Image(ClassLoader.getSystemResource("girl2Big.png").toString());
		bigPlayerTurnLeft = new Image(ClassLoader.getSystemResource("girl4Big.png").toString());
		bigPlayerTurnRight = new Image(ClassLoader.getSystemResource("girl1Big.png").toString());
		flagImage = new Image(ClassLoader.getSystemResource("flag4.png").toString());
		floor = new Image(ClassLoader.getSystemResource("background5.png").toString());
		// MenuBackground = new
		// Image(ClassLoader.getSystemResource("Background.png").toString());
		ding = new AudioClip(ClassLoader.getSystemResource("Ding.wav").toString());
		click = new AudioClip(ClassLoader.getSystemResource("Click.wav").toString());
		gameOver = new AudioClip(ClassLoader.getSystemResource("GameOver.wav").toString());
		start = new AudioClip(ClassLoader.getSystemResource("startSound.wav").toString());
		gameSound = new AudioClip(ClassLoader.getSystemResource("game.wav").toString());
		potionSound = new AudioClip(ClassLoader.getSystemResource("potionSound.wav").toString());
		winsound = new AudioClip(ClassLoader.getSystemResource("smb_stage_clear.wav").toString());
		font = Font.loadFont(ClassLoader.getSystemResource("Minecrafter.Alt.ttf").toExternalForm(), 23);
		font2 = Font.loadFont(ClassLoader.getSystemResource("FaceOff.ttf").toExternalForm(), 40);
		font3 = Font.loadFont(ClassLoader.getSystemResource("XGhostX-d990l.otf").toExternalForm(), 23);

	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

}
