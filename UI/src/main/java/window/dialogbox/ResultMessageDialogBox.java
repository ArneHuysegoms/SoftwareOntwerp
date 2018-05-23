package window.dialogbox;

import action.*;
import diagram.message.ResultMessage;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.textbox.MethodTextBox;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;

public class ResultMessageDialogBox extends DialogBox {

    public static final int WIDTH = 120;
    public static final int HEIGHT = 60;

    private TextBox labelTextBox;
    private ResultMessage resultMessage;
    private DiagramSubwindow diagramSubwindow;

    private TextBox selected;

    public ResultMessageDialogBox(Point2D pos, ResultMessage resultMessage, DiagramSubwindow diagramSubwindow) throws UIException {
        super(pos);
        this.resultMessage = resultMessage;
        this.diagramSubwindow = diagramSubwindow;
        this.labelTextBox = new MethodTextBox(new Point2D.Double(10, 40), "Method");
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        selected = labelTextBox;
        updateFields(resultMessage);
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public TextBox getSelected() {
        return selected;
    }

    public TextBox getLabelTextBox() {
        return labelTextBox;
    }

    public ResultMessage getResultMessage() {
        return resultMessage;
    }

    public DiagramSubwindow getDiagramSubwindow() {
        return diagramSubwindow;
    }

    @Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        return new EmptyAction();
    }

    @Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
        try {
            switch (keyEvent.getKeyEventType()) {
                case CHAR:
                    return handleChar(keyEvent);
                case BACKSPACE:
                    return handleBackSpace();
            }
        } catch (DomainException e) {
            e.printStackTrace();
        }
        return new EmptyAction();
    }

    private Action handleBackSpace() throws DomainException {
        if(selected.hasValidContents()) {
            selected.deleteLastCharFromContents();
            return changeResultMessageLabel();
        }
        return new EmptyAction();
    }

    private Action handleChar(KeyEvent keyEvent) throws DomainException {
        selected.addCharToContents(keyEvent.getKeyChar());
        if (selected.hasValidContents()) {
            return changeResultMessageLabel();
        }
        return new EmptyAction();
    }

    private Action changeResultMessageLabel() throws DomainException {
        getResultMessage().getLabel().setLabel(selected.getContents());
        return new UpdateLabelContainersAction(getResultMessage().getLabel());
    }

    @Override
    public void handleAction(Action action) {
        if(action instanceof RemoveInViewsAction) {
            RemoveInViewsAction a = (RemoveInViewsAction) action;
            if(a.getDeletedElements().contains(resultMessage)){
                this.getFrame().close();
            }
        }
        if(action instanceof UpdateLabelAction){
            UpdateLabelAction a = (UpdateLabelAction) action;
            if(a.getElement().equals(resultMessage)){
                updateFields((ResultMessage) a.getElement());
            }
        }
    }

    private void updateFields(ResultMessage resultMessage) {
        labelTextBox.setContents(resultMessage.getLabel().getLabel());
    }
}
