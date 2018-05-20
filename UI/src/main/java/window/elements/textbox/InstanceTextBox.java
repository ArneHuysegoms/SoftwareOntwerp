package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;

import java.awt.geom.Point2D;

public class InstanceTextBox extends TextBox {

    public InstanceTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    @Override
    public boolean hasValidContents(){
        return getContents().equals("") || Party.isValidInstanceString(this.getContents());
    }
}
