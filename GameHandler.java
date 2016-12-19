package hangmangameGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public abstract class GameHandler implements EventHandler<ActionEvent> {

	WordGame game;	

	Label messageLabel = new Label("Let's play HangMan!");		//used to display the message after very user-move
	Text clueWordLabel = new Text();	 						//displays clueWord
	GridPane userGrid= new GridPane();			//holds the alphaGrid and the scoreGrid
	Label[] scoreValueLabels;					//scores used in scoreGrid	

	abstract void setupUserGrid();
	abstract void setupInputGrid();

	@Override
	public void handle(ActionEvent event) {
		
		//enter code here
		game = new Hangman();
		setupClueWordStack();
		setupUserGrid();
	}		

	void setupClueWordStack() {
		//enter code here
		StackPane clueWordStack = new StackPane(); //holds clueWordLabel
		clueWordStack.setBackground(new Background(new BackgroundFill(Color.WHITE,
				CornerRadii.EMPTY, Insets.EMPTY)));
		clueWordStack.setStyle("-fx-font-size: 40;");
		//Put clueWordLabels inside stackpane
		clueWordLabel.setText(game.clueWord);
		clueWordStack.getChildren().add(clueWordLabel);
		WordNerd.root.setCenter(clueWordStack);
	}

	void setupScoreGrid() {
		//enter code here
		Text[] texts = new	Text[3];//labels for scores
		texts[0] = new Text("Trials to go		");
		texts[1] = new Text("Hit n Miss		");
		texts[2] = new Text("Game Score		");
		for(int i =0; i<texts.length;i++){
			texts[i].setStyle("-fx-font-size: 15;");
		}
		//set properties for scoreValueLabels
		scoreValueLabels = new Label[4];
		scoreValueLabels[0] = new Label("10");
		scoreValueLabels[0].setPrefSize(80,20);
		scoreValueLabels[1] = new Label("0");
		scoreValueLabels[1].setPrefSize(40, 20);
		scoreValueLabels[2] = new Label("0");
		scoreValueLabels[2].setPrefSize(40, 20);
		scoreValueLabels[3] = new Label("0");
		scoreValueLabels[3].setPrefSize(80,20);
		scoreValueLabels[0].setStyle("-fx-font-size:20; -fx-background-color: antiquewhite;-fx-alignment: baseline-center");
		scoreValueLabels[3].setStyle("-fx-font-size:20; -fx-background-color: antiquewhite;-fx-alignment: baseline-center");
		scoreValueLabels[1].setStyle("-fx-font-size:20;-fx-background-color: cornflowerblue;-fx-alignment: baseline-center");
		scoreValueLabels[2].setStyle("-fx-font-size:20;-fx-background-color: palevioletred;-fx-alignment: baseline-center");
		//Initialize scoreGrid and put elements in it
		GridPane scoreGrid = new GridPane();
		scoreGrid.setStyle("-fx-start-margin: 20;-fx-alignment: center");
		scoreGrid.add(texts[0],0,0);
		scoreGrid.add(texts[1],0,1);
		scoreGrid.add(texts[2],0,2);
		scoreGrid.add(scoreValueLabels[0],1,0,2,1);
		scoreGrid.add(scoreValueLabels[1],1,1);
		scoreGrid.add(scoreValueLabels[2],2,1);
		scoreGrid.add(scoreValueLabels[3],1,2,2,1);
		userGrid.add(scoreGrid,1,0,2,1);
		scoreGrid.setPadding(new Insets(50,10,20,10));
	}
}
