package figures.drawable.subwindowFigures;

import figures.drawable.basicShapes.AddButtonFigure;
import figures.drawable.basicShapes.DownButtonFigure;
import figures.drawable.basicShapes.RemoveButtonFigure;
import figures.drawable.basicShapes.UpButtonFigure;
import window.dialogbox.InvocationMessageDialogBox;
import window.elements.ListBox;
import window.elements.textbox.TextBox;

import java.awt.*;
import java.util.List;

public class InvocationMessageDialogBoxFigure extends SubwindowFigure {

    private InvocationMessageDialogBox dialogBox;

    public InvocationMessageDialogBoxFigure(InvocationMessageDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        super.draw(graphics, minX, minY, maxX, maxY);
        drawTextBoxes(graphics, minX, minY, maxX, maxY);
        drawButtons(graphics, minX, minY, maxX, maxY);
        drawListBox(graphics, minX, minY, maxX, maxY);
    }

    private void drawListBox(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        //TODO teken list box en contents.
        List<String> arguments = dialogBox.getArgumentListBox().getArguments();
        new figures.drawable.basicShapes.Rectangle(dialogBox.getArgumentListBox().getCoordinate(), ListBox.HEIGHT, ListBox.WIDTH);
    }

    private void drawButtons(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new AddButtonFigure(dialogBox.getAddArgument().getPosition(), dialogBox.getAddArgument().getWidth(), dialogBox.getAddArgument().getHeight())
                .draw(graphics, minX, minY, maxX, maxY);
        new RemoveButtonFigure(dialogBox.getDeleteArgument().getPosition(), dialogBox.getDeleteArgument().getWidth(), dialogBox.getDeleteArgument().getHeight())
                .draw(graphics, minX, minY, maxX, maxY);
        new UpButtonFigure(dialogBox.getMoveUp().getPosition(), dialogBox.getMoveUp().getWidth(), dialogBox.getMoveUp().getHeight())
                .draw(graphics, minX, minY, maxX, maxY);
        new DownButtonFigure(dialogBox.getMoveDown().getPosition(), dialogBox.getMoveDown().getWidth(), dialogBox.getMoveDown().getHeight())
                .draw(graphics, minX, minY, maxX, maxY);

    }

    private void drawTextBoxes(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        //TODO description of static title dingetje toevoegen. EN .Wat er in de textBox sta/getypt wordt
        new figures.drawable.basicShapes.Rectangle(dialogBox.getArgumentTextBox().getCoordinate(), TextBox.HEIGHT, TextBox.WIDTH).draw(graphics, minX, minY, maxX, maxY);
        new figures.drawable.basicShapes.Rectangle(dialogBox.getMethodTextBox().getCoordinate(), TextBox.HEIGHT, TextBox.WIDTH).draw(graphics, minX, minY, maxX, maxY);
    }


    //TODO A dialog box for an invocation message shows a text box for the method name, a list box
    //for the arguments, a text box and a button for adding a new argument to the end
    //of the argument list, buttons for moving the selected argument up or down in the
    //argument list, and a button for deleting the selected argument.
}
