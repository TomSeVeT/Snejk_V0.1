package pl.sevet.snejk.settings;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Settings {

    public static final String HIGHSCORES_PATH = "HighScores.db";
    public static SimpleIntegerProperty SCORE = new SimpleIntegerProperty(0);
    private static Difficulty DIFFICULTY = Difficulty.EASY;
    private static Direction DIRECTION = Direction.RIGHT;
    private static int MAP_SIZE = 600;
    private static int BLOCK_SIZE = 30;
    private static int SPEED = 1;
    private static int POINTS = 1;
    private static Paint BACKGROUND_COLOR = Color.GRAY;

    public static void setDifficulty(Difficulty DIFFICULTY) {
        Settings.DIFFICULTY = DIFFICULTY;
        switch (DIFFICULTY){
            case EASY:
                Settings.SPEED = 1;
                Settings.BLOCK_SIZE = 30;
                Settings.POINTS = 1;
                break;
            case MEDIUM:
                Settings.SPEED = 3;
                Settings.BLOCK_SIZE = 25;
                Settings.POINTS = 3;
                break;
            case HARD:
                Settings.SPEED = 4;
                Settings.BLOCK_SIZE = 20;
                Settings.POINTS = 5;
                break;
        }
    }

    public static void setDirection(Direction DIRECTION) {
        Settings.DIRECTION = DIRECTION;
    }

    public static Difficulty getDifficulty() {
        return DIFFICULTY;
    }

    public static Direction getDirection() {
        return DIRECTION;
    }

    public static int getMapSize() {
        return MAP_SIZE;
    }

    public static int getBlockSize() {
        return BLOCK_SIZE;
    }

    public static int getSpeed() {
        return SPEED;
    }

    public static Paint getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    public static int getPoints() {
        return POINTS;
    }
}
