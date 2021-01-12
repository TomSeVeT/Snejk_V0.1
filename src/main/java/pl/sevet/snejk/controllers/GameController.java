package pl.sevet.snejk.controllers;

import javafx.fxml.FXML;
import pl.sevet.snejk.highScores.HighScores;
import pl.sevet.snejk.highScores.Player;
import pl.sevet.snejk.settings.Settings;

public class GameController {
    @FXML
    private LevelController levelController;

    @FXML
    private MenuController menuController;

    @FXML
    private HighScoresController highScoresController;

    @FXML
    public void initialize(){
        menuController.setGameController(this);
        levelController.setGameController(this);
        highScoresController.setGameController(this);
        HighScores.loadHighScores(Settings.HIGHSCORES_PATH);
        for(Player player: HighScores.getHighScores()){
            highScoresController.updateList();
        }
    }

    public LevelController getLevelController() {
        return levelController;
    }

    public MenuController getMenuController(){
        return menuController;
    }

    public HighScoresController getHighScoresController(){
        return highScoresController;
    }

}
