package window.elements.button;

import command.closeWindow.CloseSubwindowCommand;

public abstract class CloseWindowButton extends Button{

    public CloseWindowButton(CloseSubwindowCommand closeSubwindowCommand){
        super(closeSubwindowCommand);
    }
}
