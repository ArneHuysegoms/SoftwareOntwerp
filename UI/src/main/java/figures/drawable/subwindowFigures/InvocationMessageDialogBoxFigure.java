package figures.drawable.subwindowFigures;

import figures.drawable.diagramFigures.*;
import window.dialogbox.InvocationMessageDialogBox;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.button.*;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
import window.elements.textbox.TextBox;

import java.awt.*;

public class InvocationMessageDialogBoxFigure extends DialogBoxSubwindowFigure {

    private InvocationMessageDialogBox dialogBox;

    public InvocationMessageDialogBoxFigure(InvocationMessageDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    /**
     * draws an invocation message dialog box
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        drawWindowFrame(graphics);
        drawTextBoxes(graphics, minX, minY, maxX, maxY);
        drawButtons(graphics, dialogBox.getArgumentListBox().getSelectedIndex(), minX, minY, maxX, maxY);
        drawListBox(graphics, minX, minY, maxX, maxY);
        super.handleSelectedElement(graphics,dialogBox);

        if(dialogBox.getDesignerMode()) {
            this.drawOrangeTitleBar(graphics);
        }
    }

    /**
     * draws the list box on the dialog box
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawListBox(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        ListBox temp;
        for(DialogboxElement ele : dialogBox.getElementList()) {
            if(ele instanceof ListBox) {
                temp = (ListBox) ele;
                new ListBoxFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()))
                        .draw(graphics, minX, minY, maxX, maxY);
            }
        }
    }

    /**
     * draws the list box on the dialog box
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawListBox(Graphics graphics) {
        this.drawListBox(graphics, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * draws the buttons on the dialog box
     *
     * @param graphics object used to draw on the program's window
     * @param selectedIndex index to see if an argument is selected, the buttons are drawn gray if no argument is selected
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawButtons(Graphics graphics, int selectedIndex, int minX, int minY, int maxX, int maxY) {
        /*new AddButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getAddArgument().getCoordinate()), dialogBox.getAddArgument().getWidth(), dialogBox.getAddArgument().getHeight())
                .draw(graphics, minX, minY, maxX, maxY);
        Color temp = graphics.getColor();
        if (selectedIndex < 0) {
            graphics.setColor(Color.LIGHT_GRAY);
        }
        new RemoveButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getDeleteArgument().getCoordinate()), dialogBox.getDeleteArgument().getWidth(), dialogBox.getDeleteArgument().getHeight())
                .draw(graphics, minX, minY, maxX, maxY);
        new UpButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getMoveUp().getCoordinate()), dialogBox.getMoveUp().getWidth(), dialogBox.getMoveUp().getHeight())
                .draw(graphics, minX, minY, maxX, maxY);
        new DownButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getMoveDown().getCoordinate()), dialogBox.getMoveDown().getWidth(), dialogBox.getMoveDown().getHeight())
                .draw(graphics, minX, minY, maxX, maxY);
        graphics.setColor(temp);*/

        FakeButton tempBut;
        Color temp = graphics.getColor();
        for(DialogboxElement ele : dialogBox.getElementList()){
            if(ele instanceof AddArgumentFakeButton) {
                tempBut = (FakeButton) ele;
                new AddButtonFigure(dialogBox.getAbsolutePosition(tempBut.getCoordinate()), tempBut.getWidth(), tempBut.getHeight())
                        .draw(graphics, minX, minY, maxX, maxY);
            }

            if (selectedIndex < 0) {
                graphics.setColor(Color.LIGHT_GRAY);
            }
            if(ele instanceof MoveUpFakeButton) {
                tempBut = (FakeButton) ele;
                new UpButtonFigure(dialogBox.getAbsolutePosition(tempBut.getCoordinate()), tempBut.getWidth(), tempBut.getHeight())
                        .draw(graphics, minX, minY, maxX, maxY);
            }
            if(ele instanceof MoveDownFakeButton) {
                tempBut = (FakeButton) ele;
                new DownButtonFigure(dialogBox.getAbsolutePosition(tempBut.getCoordinate()), tempBut.getWidth(), tempBut.getHeight())
                        .draw(graphics, minX, minY, maxX, maxY);
            }
            if(ele instanceof DeleteArgumentFakeButton) {
                tempBut = (FakeButton) ele;
                new RemoveButtonFigure(dialogBox.getAbsolutePosition(tempBut.getCoordinate()), tempBut.getWidth(), tempBut.getHeight())
                        .draw(graphics, minX, minY, maxX, maxY);
            }
            graphics.setColor(temp);
        }
    }

    /**
     * draws the buttons on the dialog box
     *
     * @param graphics object used to draw on the program's window
     * @param selectedIndex index to see if an argument is selected, the buttons are drawn gray if no argument is selected
     */
    private void drawButtons(Graphics graphics, int selectedIndex) {
        this.drawButtons(graphics, selectedIndex, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * draws the text boxes on the dialog box
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawTextBoxes(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        TextBox temp;
        for(DialogboxElement ele : dialogBox.getElementList()){
            if(ele instanceof TextBox) {
                temp = (TextBox) ele;
                new TextBoxFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                        .draw(graphics, minX, minY, maxX, maxY);
            }
        }
    }

    /**
     * draws the text boxes on the dialog box
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawTextBoxes(Graphics graphics) {
        this.drawTextBoxes(graphics, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
