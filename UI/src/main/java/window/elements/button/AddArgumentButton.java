package window.elements.button;

import command.Command;
import command.InvocationCommand.AddArgumentCommand;
import diagram.label.InvocationMessageLabel;
import exception.UIException;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

import java.awt.geom.Point2D;

public class AddArgumentButton extends DialogBoxButton {
    public AddArgumentButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate,"");
    }

    @Override
    public DialogboxElement clone() {
        try {
            return new AddArgumentButton(getCommand(), getCoordinate(), getDescription());
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(DiagramSubwindow subwindow, InvocationMessageLabel label, ListBox lb, ArgumentTextBox atb) {
        this.setCommand(new AddArgumentCommand(lb,atb,label,subwindow));
    }
}
