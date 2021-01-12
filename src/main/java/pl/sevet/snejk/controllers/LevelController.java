package pl.sevet.snejk.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import pl.sevet.snejk.objects.Field;
import pl.sevet.snejk.objects.Food;
import pl.sevet.snejk.objects.Snake;
import pl.sevet.snejk.settings.Direction;
import pl.sevet.snejk.settings.Settings;

import java.util.Random;

public class LevelController {

    @FXML
    private GameController gameController;

    @FXML
    private MenuController menuController;

    @FXML
    private HighScoresController highScoresController;

    @FXML
    private Canvas canvas;


    @FXML
    private AnchorPane map;

    @FXML
    void keyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.LEFT) Settings.setDirection(Direction.LEFT);
        if(event.getCode() == KeyCode.RIGHT) Settings.setDirection(Direction.RIGHT);
        if(event.getCode() == KeyCode.UP) Settings.setDirection(Direction.UP);
        if(event.getCode() == KeyCode.DOWN) Settings.setDirection(Direction.DOWN);
    }


    AnimationTimer timer = new AnimationTimer() {
        long temp=0;

        @Override
        public void handle(long now) {
            if(temp == 0)             temp = now;

            if(now - temp > 50000000*(6-Settings.getSpeed())){
                moveSnake();
                temp= now;
            }
        }
    };

    Snake snake = new Snake();

    Food food = new Food();


    @FXML
    public void initialize() {
        drawBackgroundNet();
        drawFood();
        drawSnake();
    }

    public void start(){
        if(menuController == null) menuController = gameController.getMenuController();

        map.requestFocus();
        map.getChildren().clear();
        snake = new Snake();
        drawBackgroundNet();
        drawFood();
        drawSnake();
        Settings.SCORE.setValue(0);
        timer.start();
    }

    private void drawBackgroundNet() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Settings.getBackgroundColor());
        graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        for (int i = 0; i <= Settings.getMapSize(); i += Settings.getBlockSize()) {
            graphicsContext.setStroke(Color.SEAGREEN);
            graphicsContext.setLineWidth(0.5);
            graphicsContext.strokeLine(i, 0, i, Settings.getMapSize());
            graphicsContext.strokeLine(0, i, Settings.getMapSize(), i);
        }
    }


    private void moveSnake() {
        Field nextField = new Field();

        switch(Settings.getDirection()){

            case UP:
            nextField.setX(snake.getSnake().get(0).getX());
            nextField.setY(snake.getSnake().get(0).getY()-Settings.getBlockSize());
                break;

            case DOWN:
            nextField.setX(snake.getSnake().get(0).getX());
            nextField.setY(snake.getSnake().get(0).getY()+Settings.getBlockSize());
                break;

            case LEFT:
            nextField.setX(snake.getSnake().get(0).getX()-Settings.getBlockSize());
            nextField.setY(snake.getSnake().get(0).getY());
                break;

            case RIGHT:
            nextField.setX(snake.getSnake().get(0).getX()+Settings.getBlockSize());
            nextField.setY(snake.getSnake().get(0).getY());
                break;
        }


        if(nextField.getX()>=Settings.getMapSize() || nextField.getY()>=Settings.getMapSize()
        || nextField.getX()<0 || nextField.getY()<0) {
            loose();
            return;
        }

        for(Field field : snake.getSnake()){
            if(field.getX() == nextField.getX()  &&  field.getY() == nextField.getY()){
                loose();
                return;
            }
        }

        if(nextField.getX()==food.getX()  &&  nextField.getY()== food.getY()){
            drawFood();
            snake.eatFood(nextField);
            Settings.SCORE.setValue(Settings.SCORE.getValue()+Settings.getPoints());
        }else snake.move(nextField);
        drawSnake();
    }


    private void drawSnake() {
        if(map.getChildren().size()>1) {
            map.getChildren().remove(1,map.getChildren().size());
        }
        for (Field field : snake.getSnake()) {
            Rectangle tail = new Rectangle();
            tail.setX(field.getX() + 2.5);
            tail.setY(field.getY() + 2.5);
            tail.setHeight(Settings.getBlockSize() - 5);
            tail.setWidth(Settings.getBlockSize() - 5);
            tail.setFill(field.getColor());
            map.getChildren().add(tail);
        }
    }


    private void drawFood() {
        Rectangle actualFood = new Rectangle();
        food = randomFoodField();
        food.setColor(Color.ORANGERED);
        actualFood.setX(food.getX()+0.2*Settings.getBlockSize());
        actualFood.setY(food.getY()+0.2*Settings.getBlockSize());
        actualFood.setHeight(Settings.getBlockSize() - 0.5*Settings.getBlockSize());
        actualFood.setWidth(Settings.getBlockSize() - 0.5*Settings.getBlockSize());
        actualFood.setFill(food.getColor());
        if(map.getChildren().size()==0)
            map.getChildren().add(actualFood);
        else map.getChildren().set(0,actualFood);
    }


    private Food randomFoodField(){
        int x,y;
        boolean check=false;
        do{
            x = randomFieldCoord();
            y = randomFieldCoord();
            for(Field field: snake.getSnake()){
                if(field.getX() == x && field.getY() == y) check = true;
            }
        }while(check);
        return new Food(x,y);
    }

    private int randomFieldCoord(){
        Random rand = new Random();

        int coord = rand.nextInt(Settings.getMapSize()/Settings.getBlockSize())*Settings.getBlockSize();
        return coord;
    }


    private void loose() {
        timer.stop();
        canvas.getGraphicsContext2D().setFill(Color.RED);
        canvas.getGraphicsContext2D().setFont(new Font(50));
        canvas.getGraphicsContext2D().fillText("JEBANY LOOSER", Settings.getMapSize() / 4, Settings.getMapSize() / 2);
        menuController.enableButtons();
        if(highScoresController==null) highScoresController = gameController.getHighScoresController();
        highScoresController.scoreCheck();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
