package window.elements.button;

import command.CloseSubwindowCommand;

public class CloseWindowButton extends Button{

    public CloseWindowButton(CloseSubwindowCommand closeSubwindowCommand){
        super(closeSubwindowCommand);
    }
}
