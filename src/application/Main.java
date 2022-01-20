package application;

import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import logic.Cross;
import logic.GameLogic;
import logic.Player;
import render.RenderableHolder;
import CrossHunterButton.MenuButton;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	public Image MenuImage;
	public BackgroundImage MenuBackground;
	public Image HelpImage;
	public BackgroundImage HelpBackground;
	public Image EndImage;
	public BackgroundImage EndBackground;
	public AnchorPane root;
	public MenuButton button1;
	public MenuButton button2;
	public MenuButton button3;
	public Text text;
	private Canvas canvas;
	private int currentTime;
	private Thread timerThread;
	// private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;
	// -fx-background-image:
	// url('/DiamondHunterButton/resources/yellow_button01.png');";

	@Override
	public void start(Stage primaryStage) {
		MenuImage = new Image("halloween-background.gif", 600, 600, false, true);
		MenuBackground = new BackgroundImage(MenuImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);
		root = new AnchorPane();
		root.setPrefSize(600, 600);
		root.setBackground(new Background(MenuBackground));
		button1 = new MenuButton("start");
		button2 = new MenuButton("Help");
		button3 = new MenuButton("Exit");
		text = new Text("CROSS HUNTER");
		text.setFont(Font.font("Chiller", FontWeight.EXTRA_BOLD, 100));
		text.setFill(Color.WHITE);
		text.setLayoutX(60);
		text.setLayoutY(170);

		button1.setLayoutX(220);
		button1.setLayoutY(200);
		button2.setLayoutX(220);
		button2.setLayoutY(300);
		button3.setLayoutX(220);
		button3.setLayoutY(400);

		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				/// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showGameScreen();
				RenderableHolder.start.stop();
				primaryStage.close();
			}
		});
		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showHowToPlay();
				primaryStage.close();
			}
		});
		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Platform.exit();

			}
		});
		root.getChildren().addAll(text, button1, button2, button3);
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("CrossHunter");
		RenderableHolder.start.play();
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

	public void showGameScreen() {

		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();
		GameLogic logic = new GameLogic();
		GameScreen gameScreen = new GameScreen(800, 600);
		Scene scene2 = new Scene(root, 800, 640);
		stage.setScene(scene2);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
		stage.setTitle("CrossHunter");
		stage.show();

		this.canvas = new Canvas(200, 100);
		canvas.setLayoutX(550);
		canvas.setLayoutY(0);
		root.getChildren().add(canvas);
		this.currentTime = 0;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.timerThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
					currentTime++;
					drawCurrentTimeString(gc);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Stop Timer Thread");
					Platform.exit();
					break;
				}
			}
		});
		timerThread.setDaemon(true);
		this.timerThread.start();

		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.paintComponent();
				logic.logicUpdate();
				if (!RenderableHolder.gameSound.isPlaying()) {
					RenderableHolder.gameSound.play();
				}
				if (Player.iscollideGhost1() || Player.iscollideGhost2() || Player.iscollideGhost3()) {
					RenderableHolder.gameSound.stop();
					RenderableHolder.gameOver.play();
					try {
						Thread.sleep(2000);
						RenderableHolder.gameOver.stop();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stage.close();
					showLoseScreen();
					stop();
				} else if (Cross.getScore() == 25) {
					RenderableHolder.gameSound.stop();
					RenderableHolder.winsound.play();
//					RenderableHolder.winSound.start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stage.close();
					showWinScreen();
					stop();
				}
			}

		};
		animation.start();
		stage.setResizable(false);
	}

	public void showHomeScreen() {
		Stage stage = new Stage();
		MenuImage = new Image("halloween-background.gif", 600, 600, false, true);
		MenuBackground = new BackgroundImage(MenuImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);
		root = new AnchorPane();
		root.setPrefSize(600, 600);
		root.setBackground(new Background(MenuBackground));
		button1 = new MenuButton("start");
		button2 = new MenuButton("Help");
		button3 = new MenuButton("Exit");
		text = new Text("CROSS HUNTER");
		text.setFont(Font.font("Chiller", 100));
		text.setFill(Color.WHITE);
		text.setLayoutX(60);
		text.setLayoutY(170);
		button1.setLayoutX(220);
		button1.setLayoutY(200);
		button2.setLayoutX(220);
		button2.setLayoutY(300);
		button3.setLayoutX(220);
		button3.setLayoutY(400);
		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				/// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showGameScreen();
				RenderableHolder.start.stop();
				// logic.Player.collideMonster = false;
				stage.close();
			}
		});

		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showHowToPlay();
				stage.close();
			}
		});

		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.close();

			}
		});
		root.getChildren().addAll(text, button1, button2, button3);
		Scene scene = new Scene(root, 600, 600);
		stage.setScene(scene);
		stage.setTitle("CrossHunter");

		stage.setResizable(false);
		stage.show();
	}

	public void showHowToPlay() {

		HelpImage = new Image("howtoplay2.png", 600, 600, false, true);
		HelpBackground = new BackgroundImage(HelpImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);

		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();
		root.setBackground(new Background(HelpBackground));
		root.setPrefSize(600, 600);

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("CrossHunter");
		stage.setResizable(false);

		button2 = new MenuButton("BACK");
		button1 = new MenuButton("START");

		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showHomeScreen();
				stage.close();
			}
		});

		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showGameScreen();
				RenderableHolder.start.stop();
				stage.close();
			}
		});

		button2.setLayoutX(1);
		button2.setLayoutY(550);
		button1.setLayoutX(470);
		button1.setLayoutY(550);

		root.getChildren().addAll(button2, button1);
		stage.setResizable(false);
		stage.show();

	}

	public void showLoseScreen() {
		Font font = Font.font("chiller", FontWeight.EXTRA_BOLD, 65);
		EndImage = new Image("gameover.png", 600, 600, false, true);
		EndBackground = new BackgroundImage(EndImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);

		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();

		root.setBackground(new Background(EndBackground));
		root.setPrefSize(600, 600);

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("CrossHunter");
		stage.setResizable(false);

		Label score = new Label();
		score.setText(Integer.toString(Cross.getScore()));
		score.setFont(font);
		score.setLayoutX(280);
		score.setLayoutY(290);

		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.close();

			}
		});
		button3.setLayoutY(525);
		button3.setLayoutX(430);

		root.getChildren().addAll(button3, score);
		stage.show();

	}

	public void showWinScreen() {
		Font font = Font.font("chiller", FontWeight.EXTRA_BOLD, 65);
		EndImage = new Image("youwin.png", 600, 600, false, true);
		EndBackground = new BackgroundImage(EndImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);

		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();

		root.setBackground(new Background(EndBackground));
		root.setPrefSize(600, 600);

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("CrossHunter");
		stage.setResizable(false);

		Text timeUsed = new Text("" + (this.currentTime - 2) + " s");

		timeUsed.setLayoutX(270);
		timeUsed.setLayoutY(362);
		timeUsed.setFont(font);

		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Platform.exit();

			}
		});

		button3.setLayoutY(525);
		button3.setLayoutX(430);

		root.getChildren().addAll(button3, timeUsed);
		stage.show();

	}

	public void drawCurrentTimeString(GraphicsContext gc) {

		gc.setFill(Color.BLACK);
		gc.setFont(RenderableHolder.font2);
		gc.clearRect(0, 0, 200, 100);
		gc.fillText("" + this.currentTime, 30, 65);
	}

}