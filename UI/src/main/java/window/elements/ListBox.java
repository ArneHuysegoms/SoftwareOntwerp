package window.elements;

import exception.UIException;
import window.Clickable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListBox extends DialogboxElement implements Clickable {

    public static final int WIDTH = 120;
    public static final int HEIGHT = 75;
    public static final int ARGUMENT_HEIGHT = 14;

    private List<String> arguments;

    private int selectedIndex;

    public ListBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
        arguments = new ArrayList<>();
        selectedIndex = -1;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        if(selectedIndex < 0){
            this.selectedIndex = 0;
        }
        else if( selectedIndex > arguments.size() - 1){
            this.selectedIndex = arguments.size() - 1;
        }
        else {
            this.selectedIndex = selectedIndex;
        }
    }

    public void setArguments(List<String> newArguments){
        this.arguments = newArguments;
    }

    @Override
    public boolean isClicked(Point2D location) {
        double startX = getCoordinate().getX();
        double endX = getCoordinate().getX() + WIDTH;
        double startY = getCoordinate().getY() ;
        double endY = getCoordinate().getY() + HEIGHT;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }

    /**
     * returns a relative point based on the given location and the location of the subwindow
     * @param location the location that needs to be translated
     * @return a relative point to this subwindow based on the given location
     */
    public Point2D getRelativePoint(Point2D location) {
        return new Point2D.Double(location.getX() - this.getCoordinate().getX(), location.getY() - this.getCoordinate().getY());
    }

    public void selectArgument(Point2D location){
        selectedIndex = (int) location.getY()/14;
        if(selectedIndex > arguments.size() - 1){
            selectedIndex = arguments.size() - 1;
        }
    }

    public void addArgument(String argument){
        arguments.add(argument);
    }

    public void removeArgument(){
        arguments.remove(selectedIndex);
    }

    public void moveUp(){
        if(selectedIndex != 0) {
            Collections.swap(arguments, selectedIndex, selectedIndex - 1);
            selectedIndex++;
        }
    }

    public void moveDown(){
        if(selectedIndex != arguments.size() - 1) {
            Collections.swap(arguments, selectedIndex, selectedIndex + 1);
            selectedIndex--;
        }
    }

    public boolean hasSelectedArgument(){
        return selectedIndex > -1;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
