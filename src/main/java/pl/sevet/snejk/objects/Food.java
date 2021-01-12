package pl.sevet.snejk.objects;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Food extends Field {

    public Food(){}

    public Food(Food food){
        this.setX(food.getX());
        this.setY(food.getY());
    }

    public Food(int x, int y) {
        this.setX(x);
        this.setY(y);
    }


}
