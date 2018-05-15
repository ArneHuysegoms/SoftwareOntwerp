package window.dialogbox;

import command.ChangeToCommunicationCommand;
import command.ChangeToSequenceCommand;
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
        toCommunicationDiagram = new RadioButton(new ChangeToCommunicationCommand(subwindow), new Point2D.Double(WIDTH + 20, HEIGHT  + 30), TOCOMMUNICATIONDIAGRAM_DESPCRIPTION);
        toSequenceDiagram = new RadioButton(new ChangeToSequenceCommand(subwindow), new Point2D.Double(WIDTH + 20, HEIGHT  + 60), TOSEQUENCEDIAGRAM_DESCRIPTION);
        selected = toCommunicationDiagram;
    }

    @Override
    public void handleMouseEvent(MouseEvent mouseEvent) {
        if(toCommunicationDiagram.isClicked(mouseEvent.getPoint())){
            toCommunicationDiagram.performAction();
        }
        else if(toSequenceDiagram.isClicked(mouseEvent.getPoint())){
            toSequenceDiagram.performAction();
        }
    }

    @Override
    public void handleKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getKeyEventType()){
            case TAB:
                changeSelectedRadioButton();
                break;
            case SPACE:
                selected.performAction();
                break;
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
}
