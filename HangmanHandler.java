package hangmangameGUI;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.awt.*;


public class HangmanHandler extends GameHandler {

	Button[] alphaButtons;						

	public void handle(ActionEvent event) {
		//enter code here
		super.handle(event);
	}
	
	void setupUserGrid() {
		//enter code here
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setFillWidth(true);
		columnConstraints.setHgrow(Priority.ALWAYS);
		userGrid.getColumnConstraints().add(columnConstraints);
		//Initialize the userGrid with the inputGrid, scoreGrid and messageLabel
		WordNerd.root.setBottom(userGrid);
		//set the padding for userGrid
		userGrid.setPadding(new Insets(20,0,20,30));
		setupInputGrid();
		setupScoreGrid();
		messageLabel.setPrefSize(500,20);
		messageLabel.setPadding(new Insets(0,100,20,0));
		messageLabel.setStyle("-fx-font-size: 18;-fx-alignment: bottom-center");
		userGrid.add(messageLabel,1,1,2,1);
	}

	void setupInputGrid() {
		//enter code here
		alphaButtons = new Button[26];
		GridPane inputGrid = new GridPane();
		Text title = new Text("Choose next letter\n");//title for the inputGrid
		title.setStyle("-fx-font-size: 15; -fx-hgap: 10");
		inputGrid.add(title, 0, 0, 6, 1);

		//create alphaButtons and set their positions
		int col=0,row=0;
		for (int i = 0;i<26;i++){
			row = (i/6)+1;
			if(i % 6 ==0) col=0;
			alphaButtons[i] = new Button();
			alphaButtons[i].setText(String.format("%s",(char)('a'+i)));
			alphaButtons[i].setStyle("-fx-font-size:20");
			alphaButtons[i].setPrefSize(60,30);
			inputGrid.add(alphaButtons[i],col++,row);
			alphaButtons[i].setOnAction(new AlphaButtonHandler());

			//set disable button for clueword
			for(int j=0;j<game.clueWord.length();j++){
				char alpha = game.clueWord.charAt(j);
				if(alpha==alphaButtons[i].getText().charAt(0)){
					alphaButtons[i].setDisable(true);
					break;
				}
			}
		}
		userGrid.add(inputGrid,0,0);

	}

	private void disableAlphaButtons() {
		//enter code here
		//disable all the alphaButtons
		for(int i=0;i<alphaButtons.length;i++){
			alphaButtons[i].setDisable(true);
		}
	}

	private class AlphaButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			//Get the pressed button as thisButton
			Button thisButton = ((Button) event.getSource());
			thisButton.setDisable(true);
			String input = ((Button)event.getSource()).getText();
			int result = game.nextTry(input);

			//change the color of the button
			if(result==0)
				thisButton.setStyle("-fx-background-color: red");
			if(result==1)
				thisButton.setStyle("-fx-background-color: green");
			//Assign values to scoreValueLabels

			scoreValueLabels[0].setText(String.valueOf(game.MAX_TRIALS - game.trialCount));
			scoreValueLabels[1].setText(String.valueOf(game.hit));
			scoreValueLabels[2].setText(String.valueOf(game.miss));

			//determine whether the game ends
			if( game.won==true){
				disableAlphaButtons();
				scoreValueLabels[3].setText(String.format("%.2f",game.calcScore()));
				game.message="Congratulations! You got it!";
			}else if(game.trialCount==10){
				disableAlphaButtons();
				scoreValueLabels[3].setText(String.format("%.2f",game.calcScore()));
				game.message="Sorry! You missed it. It's " + game.gameWord;
			}
			clueWordLabel.setText(game.clueWord);
			messageLabel.setText(game.message);
		}
	}

}
