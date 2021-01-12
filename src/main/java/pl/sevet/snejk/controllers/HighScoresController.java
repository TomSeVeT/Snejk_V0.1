package pl.sevet.snejk.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.converter.NumberStringConverter;
import pl.sevet.snejk.highScores.HighScores;
import pl.sevet.snejk.highScores.Player;
import pl.sevet.snejk.settings.Settings;

import java.util.Collections;
import java.util.Optional;

public class HighScoresController {

    @FXML
    private Label scoreLabel;

    @FXML
    private VBox highScoresBox;

    @FXML
    private GameController gameController;

    @FXML
    public void initialize(){
        scoreLabel.textProperty().bindBidirectional(Settings.SCORE, new NumberStringConverter());
    }

    public void setGameController(GameController gameController){
        this.gameController=gameController;
    }

    public void updateList() {
        highScoresBox.getChildren().clear();
        for (Player player : HighScores.getHighScores()){
            HBox highScoreRow = new HBox();
            highScoreRow.setSpacing(15);
            Label nameLabel = new Label(HighScores.getHighScores().indexOf(player)+1 + ". "+player.getName());
            nameLabel.setFont(new Font(20));
            Label scoreLabel = new Label(Integer.toString(player.getScore()));
            scoreLabel.setFont(new Font(20));
            highScoreRow.getChildren().addAll(nameLabel,scoreLabel);
            highScoresBox.getChildren().add(highScoreRow);
        }
    }

    public void scoreCheck(){
        if(HighScores.getHighScores().isEmpty()) addHighScore();
        else if(Settings.SCORE.getValue() > HighScores.getHighScores().get(HighScores.getHighScores().size()-1).getScore()){
            if(HighScores.getHighScores().size()>10)
                HighScores.getHighScores().remove(HighScores.getHighScores().size()-1);
            addHighScore();
        }
    }

    private void addHighScore() {
        Platform.runLater(()->{
            Dialog nameDialog = new TextInputDialog();
            nameDialog.setHeaderText("Osiągnąłeś jeden z najlepszych wyników - " + Settings.SCORE.getValue());
            nameDialog.setContentText("Podaj swój nick");
            Optional<String> result =nameDialog.showAndWait();
            Player player = new Player();
            if(result.isPresent()){
                player.setName(result.get());
            }else {
                player.setName("Brak");
            }
            player.setScore(Settings.SCORE.getValue());
            HighScores.getHighScores().add(player);
            Collections.sort(HighScores.getHighScores());
            HighScores.saveHighScores(Settings.HIGHSCORES_PATH);
            updateList();
        });
    }
}
