package window.elements.textbox;

import diagram.Argument;
import exception.UIException;

import java.awt.geom.Point2D;

public class ArgumentTextBox extends TextBox{

    public ArgumentTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    @Override
    public boolean hasValidContents() {
        String[] args = this.getContents().split(":");
        if(args.length > 1){
            return Argument.isValidArgument(args[0], args[1]);
        }
        return false;
    }
}
