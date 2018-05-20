package window.dialogbox;

import action.Action;
import action.EmptyAction;
import command.changeType.ChangeToCommunicationCommand;
import command.changeType.ChangeToSequenceCommand;
import exception.UIException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.RadioButton;

import java.awt.geom.Point2D;

public class DiagramDialogBox extends DialogBox {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 100;

    public static final String TOCOMMUNICATIONDIAGRAM_DESPCRIPTION = "Communication";
    public static final String TOSEQUENCEDIAGRAM_DESCRIPTION = "Sequence";

    private RadioButton toCommunicationDiagram;
    private RadioButton toSequenceDiagram;

    private RadioButton selected;

    public DiagramDialogBox(Point2D position, DiagramSubwindow subwindow) throws UIException {
        super(position);
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        toCommunicationDiagram = new RadioButton(new ChangeToCommunicationCommand(subwindow), new Point2D.Double(20, 30), TOCOMMUNICATIONDIAGRAM_DESPCRIPTION);
        toSequenceDiagram = new RadioButton(new ChangeToSequenceCommand(subwindow), new Point2D.Double(20, 60), TOSEQUENCEDIAGRAM_DESCRIPTION);
        selected = toCommunicationDiagram;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static String getTocommunicationdiagramDespcription() {
        return TOCOMMUNICATIONDIAGRAM_DESPCRIPTION;
    }

    public static String getTosequencediagramDescription() {
        return TOSEQUENCEDIAGRAM_DESCRIPTION;
    }

    public RadioButton getToCommunicationDiagram() {
        return toCommunicationDiagram;
    }

    public RadioButton getToSequenceDiagram() {
        return toSequenceDiagram;
    }

    public RadioButton getSelected() {
        return selected;
    }

    @Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getMouseEventType()) {
            case PRESSED:
                handleMousePress(mouseEvent);
                break;
        }
        return new EmptyAction();
    }

    @Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getKeyEventType()){
            case TAB:
                changeSelectedRadioButton();
                break;
            case SPACE:
                selected.performAction();
                break;
        }
        return new EmptyAction();
    }

    private void handleMousePress(MouseEvent mouseEvent){
        if(toCommunicationDiagram.isClicked(mouseEvent.getPoint())){
            selected = toCommunicationDiagram;
            toCommunicationDiagram.performAction();
        }
        else if(toSequenceDiagram.isClicked(mouseEvent.getPoint())){
            selected = toSequenceDiagram;
            toSequenceDiagram.performAction();
        }
    }

    private void changeSelectedRadioButton(){
        if(selected == toCommunicationDiagram){
            selected = toSequenceDiagram;
        }
        else{
            selected = toCommunicationDiagram;
        }
    }

    @Override
    public void handleAction(Action action) {

    }
}
