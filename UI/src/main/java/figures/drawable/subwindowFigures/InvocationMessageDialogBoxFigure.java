package figures.drawable.subwindowFigures;

import figures.drawable.diagramFigures.*;
import window.dialogbox.InvocationMessageDialogBox;

import java.awt.*;

public class InvocationMessageDialogBoxFigure extends SubwindowFigure {

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
        super.handleSelectedElement(graphics,dialogBox.getSelected(),dialogBox.getAbsolutePosition(dialogBox.getSelected().getCoordinate()));
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
        new ListBoxFigure(dialogBox.getArgumentListBox(), dialogBox.getAbsolutePosition(dialogBox.getArgumentListBox().getCoordinate()))
                .draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * draws the list box on the dialog box
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawListBox(Graphics graphics) {
        new ListBoxFigure(dialogBox.getArgumentListBox(), dialogBox.getAbsolutePosition(dialogBox.getArgumentListBox().getCoordinate()))
                .draw(graphics);
    }

    /**
     * draws the buttons on the dialog box
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawButtons(Graphics graphics, int selectedIndex, int minX, int minY, int maxX, int maxY) {
        new AddButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getAddArgument().getCoordinate()), dialogBox.getAddArgument().getWidth(), dialogBox.getAddArgument().getHeight())
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
        graphics.setColor(temp);
    }

    /**
     * draws the buttons on the dialog box
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawButtons(Graphics graphics, int selectedIndex) {
        new AddButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getAddArgument().getCoordinate()), dialogBox.getAddArgument().getWidth(), dialogBox.getAddArgument().getHeight())
                .draw(graphics);

        Color temp = graphics.getColor();
        if (selectedIndex < 0) {
            graphics.setColor(Color.LIGHT_GRAY);
        }
        new RemoveButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getDeleteArgument().getCoordinate()), dialogBox.getDeleteArgument().getWidth(), dialogBox.getDeleteArgument().getHeight())
                .draw(graphics);
        new UpButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getMoveUp().getCoordinate()), dialogBox.getMoveUp().getWidth(), dialogBox.getMoveUp().getHeight())
                .draw(graphics);
        new DownButtonFigure(dialogBox.getAbsolutePosition(dialogBox.getMoveDown().getCoordinate()), dialogBox.getMoveDown().getWidth(), dialogBox.getMoveDown().getHeight())
                .draw(graphics);
        graphics.setColor(temp);
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
        new TextBoxFigure(dialogBox.getMethodTextBox(), dialogBox.getAbsolutePosition(dialogBox.getMethodTextBox().getCoordinate()), "? invokeDBFigure meth")
                .draw(graphics, minX, minY, maxX, maxY);
        new TextBoxFigure(dialogBox.getArgumentTextBox(), dialogBox.getAbsolutePosition(dialogBox.getArgumentTextBox().getCoordinate()), "? invokeDBFigure arg")
                .draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * draws the text boxes on the dialog box
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawTextBoxes(Graphics graphics) {
        new TextBoxFigure(dialogBox.getMethodTextBox(), dialogBox.getAbsolutePosition(dialogBox.getMethodTextBox().getCoordinate()), "? invokeDBFigure meth")
                .draw(graphics);
        new TextBoxFigure(dialogBox.getArgumentTextBox(), dialogBox.getAbsolutePosition(dialogBox.getArgumentTextBox().getCoordinate()), "? invokeDBFigure arg")
                .draw(graphics);
    }
}
