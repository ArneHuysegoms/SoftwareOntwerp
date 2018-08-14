package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import window.Subwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

/**
 * class for an argument textbox
 */
public class ArgumentTextBox extends TextBox {

    public static String DESCRIPTION = "Argument";

    /**
     * creates a new argument textbox with the given parameters
     *
     * @param coordinate  the new coordinate
     * @param description the new description
     * @throws UIException if the coordinate is null
     */
    public ArgumentTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, DESCRIPTION);
    }

    @Override
    public DialogboxElement clone() {
        try {
            return new ArgumentTextBox(getCoordinate(), DESCRIPTION);
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * @return true if he contents are valid
     */
    @Override
    public boolean hasValidContents() {
        if (super.hasValidContents()) {
            String s = this.getContents();
            return ! (s.contains(",") || s.contains("(") || s.contains(")"));
        }
        return false;
    }

    @Override
    public void addCharToDescription(char c){
        DESCRIPTION += c;
        setDescription(DESCRIPTION);
    }

    @Override
    public void deleteCharFromDescription(){
        if(DESCRIPTION.length() > 0){
            DESCRIPTION = DESCRIPTION.substring(0,DESCRIPTION.length()-1);
            setDescription(DESCRIPTION);
        }
    }
}
