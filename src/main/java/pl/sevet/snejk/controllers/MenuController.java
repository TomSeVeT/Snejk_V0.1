package pl.sevet.snejk.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.converter.NumberStringConverter;
import pl.sevet.snejk.settings.Settings;
import pl.sevet.snejk.settings.Difficulty;

public class MenuController {

    @FXML
    private LevelController levelController;

    @FXML
    private Button startButton;

    @FXML
    private ToggleButton easyButton;

    @FXML
    private ToggleGroup DifficultySettings;

    @FXML
    private ToggleButton mediumButton;

    @FXML
    private ToggleButton hardButton;



    private GameController gameController;

    @FXML
    void easy() {
        Settings.setDifficulty(Difficulty.EASY);
    }

    @FXML
    void medium() {
        Settings.setDifficulty(Difficulty.MEDIUM);
    }

    @FXML
    void hard() {
        Settings.setDifficulty(Difficulty.HARD);
    }

    @FXML
    void start() {
        levelController = gameController.getLevelController();
        levelController.start();
        disableButtons();
    }

    private void disableButtons() {
        startButton.setDisable(true);
        easyButton.setDisable(true);
        mediumButton.setDisable(true);
        hardButton.setDisable(true);
    }

    public void enableButtons() {
        startButton.setDisable(false);
        easyButton.setDisable(false);
        mediumButton.setDisable(false);
        hardButton.setDisable(false);
    }

    public void setGameController(GameController gameController){
        this.gameController=gameController;
    }


}
