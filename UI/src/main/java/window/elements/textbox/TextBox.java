package window.elements.textbox;

import exception.UIException;
import window.Clickable;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public abstract class TextBox extends DialogboxElement implements Clickable {

    public static final int HEIGHT = 14;
    public static final int WIDTH = 70;

    private String contents;

    public TextBox(Point2D coordinate) throws UIException {
        super(coordinate);
        this.setContents("");
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public boolean isClicked(Point2D location) {
        double startX = getCoordinate().getX();
        double endX = getCoordinate().getX() + WIDTH;
        double startY = getCoordinate().getY() ;
        double endY = getCoordinate().getY() + HEIGHT;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }

    public abstract boolean hasValidContents();

    public void addCharToContents(char toAdd){
        this.contents += toAdd;
    }

    public void deleteLastCharFromContents(){
        if(contents.length() > 1){
            this.setContents(this.getContents().substring(0, this.getContents().length() - 2));
        }
        else{
            this.setContents("");
        }
    }
}
