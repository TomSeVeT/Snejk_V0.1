package pl.sevet.snejk.objects;

import javafx.scene.paint.Paint;

public class Field {
    private int x;
    private int y;
    private Paint color;

    public Field(){}



    public Field(int x, int y, Paint color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }
}
