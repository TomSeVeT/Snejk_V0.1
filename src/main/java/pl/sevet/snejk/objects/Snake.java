package pl.sevet.snejk.objects;

import javafx.scene.paint.Color;
import pl.sevet.snejk.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<Field> snake = new ArrayList<Field>();

    public Snake(){
        snake.add(new Field(Settings.getMapSize()/2,Settings.getMapSize()/2, Color.DARKGREEN));
    }

    public List<Field> getSnake() {
        return snake;
    }

    public void eatFood(Field field){
        List<Field> newSnake = new ArrayList<Field>();
        field.setColor(Color.DARKGREEN);
        newSnake.add(field);
        newSnake.addAll(snake);
        snake.clear();
        snake.addAll(newSnake);
    }

    public void move(Field field){
        for(int i=snake.size()-1; i>0; i--){
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }
        field.setColor(Color.DARKGREEN);
        snake.set(0,field);
    }

    public void setSnake(List<Field> snake) {
        this.snake = snake;
    }
}
