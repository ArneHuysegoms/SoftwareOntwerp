package window.elements.textbox;

import exception.UIException;
import window.Clickable;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public abstract class TextBox extends DialogboxElement implements Clickable {

    public static final int HEIGHT = 14;
    public static final int WIDTH = 70;

    private String contents;

    public TextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
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

    public boolean hasValidContents(){
        return ! this.getContents().isEmpty();
    }

    public void addCharToContents(char toAdd){
        this.contents += toAdd;
    }

    public void deleteLastCharFromContents(){
        if(contents.length() > 1){
            this.setContents(this.getContents().substring(0, this.getContents().length() - 1));
        }
        else{
            this.setContents("");
        }
    }
}
