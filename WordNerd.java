package hangmangameGUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WordNerd extends Application{
	static BorderPane root = new BorderPane();
	Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//enter code here
		Scene scene;
		primaryStage.setTitle("The Word Nerd");
		setupMenus();
		scene = new Scene(root,900,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setupMenus() {
		//enter code here
		//Menus
		MenuBar menuBar = new MenuBar();
		Menu game = new Menu("Game");
		Menu help = new Menu("Help");
		Menu subPlay = new Menu("Play");
		//menuItems
		MenuItem itemHangMan = new MenuItem("HangMan");
		MenuItem itemCloseGame = new MenuItem("Close Game");
		MenuItem itemExit = new MenuItem("Exit");
		MenuItem itemAbout = new MenuItem("About");
		//Assign menuItems to menus and assign menus to menuBar
		subPlay.getItems().addAll(itemHangMan);
		game.getItems().addAll(subPlay,itemCloseGame,itemExit);
		help.getItems().addAll(itemAbout);
		menuBar.getMenus().addAll(game,help);

		itemHangMan.setOnAction(new PlayEventHandler());
		itemCloseGame.setOnAction(new CloseEventHandler());
		itemExit.setOnAction(new ExitEventHandler());
		itemAbout.setOnAction(new AboutEventHandler());
		root.setTop(menuBar);
	}
	

	private class AboutEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//enter code here
			//initialize the alert dialog
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("The Word Nerd");
			alert.setContentText("Version 1.0\nRelease 1.0\nCopyRight CMU\nDeveloped by Eric Lueng");
			//import image into the alert dialog
			Image image = new Image(this.getClass().getResource("picture2.jpg").toString());
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitWidth(300);
			imageView.setFitHeight(300);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);
			alert.setGraphic(imageView);
			alert.setResizable(true);

			alert.showAndWait();
		}
	}

	private class PlayEventHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			//initialize the game
			HangmanHandler hangmanHandler = new HangmanHandler();
			hangmanHandler.handle(event);
		}
	}

	private class CloseEventHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			//close the game, clear all the child nodes in root and then recreate the menubar
			WordNerd.root.getChildren().clear();
			setupMenus();
		}
	}

	private class ExitEventHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			Platform.exit();
		}
	}
}
