package CrossHunterButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import render.RenderableHolder;

public class MenuButton extends Button {
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/CrossHunterButton/resources/yellow_button01.png');";
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/CrossHunterButton/resources/yellow_button00.png');";
	private final String FONT_PATH = "src/CrossHunterButton/resources/XGhostX-d990l.otf";

	public MenuButton(String text) {
		setText(text);
		setButtonFont();
		setPrefHeight(45);
		setPrefWidth(150);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
	}

	private void setButtonFont() {
		setFont(RenderableHolder.font3);
		// setFont(Font.font ("Chiller",FontWeight.EXTRA_BOLD, 40));
	}

	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}

	private void setButtonReleasedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}

	private void initializeButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
		});

		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setEffect(new DropShadow());

			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setEffect(null);

			}
		});
	}

}
