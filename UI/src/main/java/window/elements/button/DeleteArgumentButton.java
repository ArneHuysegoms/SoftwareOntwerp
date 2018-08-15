package window.elements.button;

import command.Command;
import command.InvocationCommand.AddArgumentCommand;
import command.InvocationCommand.DeleteArgumentCommand;
import diagram.label.InvocationMessageLabel;
import exception.UIException;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

import java.awt.geom.Point2D;

public class DeleteArgumentButton extends DialogBoxButton {
    public DeleteArgumentButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate,"");
    }

    @Override
    public DialogboxElement clone() {
        try {
            return new DeleteArgumentButton(getCommand(), getCoordinate(), getDescription());
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(DiagramSubwindow subwindow, InvocationMessageLabel label, ListBox lb, ArgumentTextBox atb) {
        this.setCommand(new DeleteArgumentCommand(lb,label,subwindow));
    }
}
