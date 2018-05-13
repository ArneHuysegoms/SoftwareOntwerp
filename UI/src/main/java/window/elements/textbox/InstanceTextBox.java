package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;

import java.awt.geom.Point2D;

public class InstanceTextBox extends TextBox {

    public InstanceTextBox(Point2D coordinate) throws UIException {
        super(coordinate);
    }

    @Override
    public boolean hasValidContents(){
        return Party.isValidInstanceString(this.getContents());
    }
}
