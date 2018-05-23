package window.dialogbox;

import action.Action;
import action.EmptyAction;
import command.changeType.ChangeToCommunicationCommand;
import command.changeType.ChangeToSequenceCommand;
import exception.UIException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.elements.RadioButton;

import java.awt.geom.Point2D;

/**
 * construct a new dialogbox for diagrams
 */
public class DiagramDialogBox extends DialogBox {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 100;

    public static final String TOCOMMUNICATIONDIAGRAM_DESPCRIPTION = "Communication";
    public static final String TOSEQUENCEDIAGRAM_DESCRIPTION = "Sequence";

    private RadioButton toCommunicationDiagram;
    private RadioButton toSequenceDiagram;

    private RadioButton selected;

    private DiagramSubwindow subwindow;

    /**
     * creates a new diagramdialogbox
     *
     * @param position  the new position
     * @param subwindow the subwindow this dialogbox is for
     * @throws UIException if the position is null
     */
    public DiagramDialogBox(Point2D position, DiagramSubwindow subwindow) throws UIException {
        super(position);
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        toCommunicationDiagram = new RadioButton(new ChangeToCommunicationCommand(subwindow), new Point2D.Double(20, 30), TOCOMMUNICATIONDIAGRAM_DESPCRIPTION);
        toSequenceDiagram = new RadioButton(new ChangeToSequenceCommand(subwindow), new Point2D.Double(20, 60), TOSEQUENCEDIAGRAM_DESCRIPTION);
        selected = toCommunicationDiagram;
        this.setDiagramSubwindow(subwindow);
    }

    /**
     * @return the diagramsubwindow for this dialogbox
     */
    public DiagramSubwindow getDiagramSubwindow() {
        return subwindow;
    }

    /**
     * sets the subwindow to the given subwindow
     *
     * @param subwindow the new subwindow
     */
    public void setDiagramSubwindow(DiagramSubwindow subwindow) {
        this.subwindow = subwindow;
    }

    /**
     * @return the default width for this dialogboxtype
     */
    public static int getWIDTH() {
        return WIDTH;
    }

    /**
     * @return the default height for this dialogboxtype
     */
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * @return the button responsible for changing to communication diagrams
     */
    public RadioButton getToCommunicationDiagram() {
        return toCommunicationDiagram;
    }

    /**
     * @return the button responsible for chaning to sequence diagram
     */
    public RadioButton getToSequenceDiagram() {
        return toSequenceDiagram;
    }

    /**
     * @return the selected radiobutton
     */
    public RadioButton getSelected() {
        return selected;
    }

    /**
     * handle mouse event
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing the outcome of the event handling
     */
    @Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getMouseEventType()) {
            case PRESSED:
                handleMousePress(mouseEvent);
                break;
        }
        return new EmptyAction();
    }

    /**
     * handle key event
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing the outcome of the event handling
     */
    @Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getKeyEventType()) {
            case TAB:
                changeSelectedRadioButton();
                break;
            case SPACE:
                selected.performAction();
                break;
        }
        return new EmptyAction();
    }

    /**
     * handles the mousepress event
     *
     * @param mouseEvent the event to handle
     */
    private void handleMousePress(MouseEvent mouseEvent) {
        if (toCommunicationDiagram.isClicked(mouseEvent.getPoint())) {
            selected = toCommunicationDiagram;
            toCommunicationDiagram.performAction();
        } else if (toSequenceDiagram.isClicked(mouseEvent.getPoint())) {
            selected = toSequenceDiagram;
            toSequenceDiagram.performAction();
        }
    }

    /**
     * change the selected radiobutton
     */
    private void changeSelectedRadioButton() {
        if (selected == toCommunicationDiagram) {
            selected = toSequenceDiagram;
        } else {
            selected = toCommunicationDiagram;
        }
    }

    /**
     * handles an action
     *
     * @param action the action to handle
     */
    @Override
    public void handleAction(Action action) {

    }
}
