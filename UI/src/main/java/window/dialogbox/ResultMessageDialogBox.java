package window.dialogbox;

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
    public static final int HEIGHT = 50;

    private TextBox labelTextBox;
    private ResultMessage resultMessage;
    private DiagramSubwindow diagramSubwindow;

    private TextBox selected;

    public ResultMessageDialogBox(Point2D pos, ResultMessage resultMessage, DiagramSubwindow diagramSubwindow) throws UIException {
        super(pos);
        this.resultMessage = resultMessage;
        this.diagramSubwindow = diagramSubwindow;
        this.labelTextBox = new MethodTextBox(new Point2D.Double(10, 30), "Method");
        selected = labelTextBox;
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
    public void handleMouseEvent(MouseEvent mouseEvent) {
    }

    @Override
    public void handleKeyEvent(KeyEvent keyEvent) {
        try {
            switch (keyEvent.getKeyEventType()) {
                case CHAR:
                    handleChar(keyEvent);
                    break;
                case BACKSPACE:
                    handleBackSpace();
                    break;
            }
        } catch (DomainException e) {
            e.printStackTrace();
        }
    }

    private void handleBackSpace() throws DomainException {
        selected.deleteLastCharFromContents();
        if (selected.hasValidContents()) {
            changeResultMessageLabel();
        }
    }

    private void handleChar(KeyEvent keyEvent) throws DomainException {
        selected.addCharToContents(keyEvent.getKeyChar());
        if (selected.hasValidContents()) {
            changeResultMessageLabel();
        }
    }

    private void changeResultMessageLabel() throws DomainException {
        getResultMessage().getLabel().setLabel(selected.getContents());
    }
}
