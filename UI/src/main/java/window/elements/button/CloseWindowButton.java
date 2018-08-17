package window.elements.button;

import command.closeWindow.CloseSubwindowCommand;
import window.elements.button.Button;

/**
 * class for buttons that close windows
 */
public class CloseWindowButton extends Button {

    /**
     * creates a new closeWindowButton with the given command
     *
     * @param closeSubwindowCommand the command for this CloseSubwindowButton
     */
    public CloseWindowButton(CloseSubwindowCommand closeSubwindowCommand) {
        super(closeSubwindowCommand);
    }
}
