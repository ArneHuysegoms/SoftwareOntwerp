package window.dialogbox;

import diagram.label.InvocationMessageLabel;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.button.FakeButton;
import window.elements.button.TextualFakeButton;
import window.elements.textbox.ArgumentTextBox;
import window.elements.textbox.MethodTextBox;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class InvocationMessageDialogBox extends DialogBox {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private MethodTextBox methodTextBox;
    private ArgumentTextBox argumentTextBox;

    private ListBox argumentListBox;

    private FakeButton addArgument;
    private FakeButton deleteArgument;
    private FakeButton moveUp;
    private FakeButton moveDown;

    private DialogboxElement selected;

    private List<DialogboxElement> dialogboxElements;

    private InvocationMessageLabel invocationMessageLabel;
    private DiagramSubwindow subwindow;

    public InvocationMessageDialogBox(Point2D pos, InvocationMessageLabel invocationMessageLabel, DiagramSubwindow subwindow) throws UIException {
        super(pos);
        this.invocationMessageLabel = invocationMessageLabel;
        methodTextBox = new MethodTextBox(new Point2D.Double(10, 50), "method");
        argumentTextBox = new ArgumentTextBox(new Point2D.Double(10, 75), "argument");
        addArgument = new TextualFakeButton(new Point2D.Double(100, 60), "Add");
        deleteArgument = new TextualFakeButton(new Point2D.Double(10, 100), "Del");
        moveDown = new TextualFakeButton(new Point2D.Double(40, 100), "Down");
        moveUp = new TextualFakeButton(new Point2D.Double(80, 100), "Up");

        argumentListBox = new ListBox(new Point2D.Double(10, 140), "");

        dialogboxElements = new ArrayList<>();
        dialogboxElements.add(methodTextBox);
        dialogboxElements.add(argumentTextBox);
        dialogboxElements.add(addArgument);
        dialogboxElements.add(deleteArgument);
        dialogboxElements.add(moveDown);
        dialogboxElements.add(moveUp);
        dialogboxElements.add(argumentListBox);

        selected = null;
    }

    @Override
    public void handleMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getMouseEventType()) {
            case PRESSED:
                handlePressed(mouseEvent.getPoint());
                break;
            default:
                break;
        }
    }

    private void handlePressed(Point2D point) {
        if (methodTextBox.isClicked(point)) {
            selected = methodTextBox;
        } else if (argumentTextBox.isClicked(point)) {
            selected = argumentTextBox;
        } else if (addArgument.isClicked(point)) {
            handleAddArgument();
        } else if (argumentListBox.isClicked(point)) {
            handleArgumentListBox(point);
        }
        if (additionalButtonsAreActive()) {
            if (deleteArgument.isClicked(point)) {
                handleDeleteArgument();
            } else if (moveDown.isClicked(point)) {
                handleMoveDown();
            } else if (moveDown.isClicked(point)) {
                handleMoveUp();
            }
        }
    }

    @Override
    public void handleKeyEvent(KeyEvent keyEvent) {
        try {
            switch (keyEvent.getKeyEventType()) {
                case BACKSPACE:
                    handleBackSpace();
                    break;
                case CHAR:
                    handleChar(keyEvent);
                    break;
                case ARROWKEYDOWN:
                    handleArrowKeyDown();
                    break;
                case ARROWKEYUP:
                    handleArrowKeyUp();
                    break;
                case SPACE:
                    handleSpace();
                    break;
                case TAB:
                    cycleSelectedElement();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleBackSpace() throws DomainException{
        if (selected instanceof TextBox) {
            TextBox t = (TextBox) selected;
            t.deleteLastCharFromContents();
            if (t == methodTextBox) {
                changeMethod();
            }
        }
    }

    private void handleChar(KeyEvent keyEvent) throws DomainException{
        if (selected instanceof TextBox) {
            TextBox t = (TextBox) selected;
            t.addCharToContents(keyEvent.getKeyChar());
            if (t == methodTextBox) {
                changeMethod();
            }
        }
    }

    private void changeMethod() throws DomainException {
        if(methodTextBox.hasValidContents()){
            invocationMessageLabel.setLabel(methodTextBox.getContents());
        }
    }

    private void handleSpace(){
        if (addArgument == selected) {
            handleAddArgument();
        }
        else if (additionalButtonsAreActive()) {
            if (deleteArgument == selected) {
                handleDeleteArgument();
            } else if (moveDown == selected) {
                handleMoveDown();
            } else if (moveDown == selected) {
                handleMoveUp();
            }
        }
    }

    private boolean additionalButtonsAreActive() {
        return argumentListBox.hasSelectedArgument();
    }


    private void handleAddArgument() {
        if (argumentTextBox.hasValidContents()) {
            String argumentString = argumentTextBox.getContents();
            argumentListBox.addArgument(argumentString);
            String[] args = argumentString.split(":");
            invocationMessageLabel.addArgument(args[0], args[1]);
        }
    }

    private void handleArgumentListBox(Point2D point) {
        selected = argumentListBox;
        argumentListBox.selectArgument(argumentListBox.getRelativePoint(point));
        invocationMessageLabel.setIndex(argumentListBox.getSelectedIndex());
    }

    private void handleDeleteArgument() {
        invocationMessageLabel.deleteArgument(argumentListBox.getSelectedIndex());
        argumentListBox.removeArgument();
    }

    private void handleMoveUp() {
        argumentListBox.moveUp();
        invocationMessageLabel.moveUp();
    }

    private void handleMoveDown() {
        argumentListBox.moveDown();
        invocationMessageLabel.moveDown();
    }

    private void handleArrowKeyDown(){
        argumentListBox.setSelectedIndex(argumentListBox.getSelectedIndex() + 1);
        invocationMessageLabel.setIndex(invocationMessageLabel.getIndex() + 1);
    }

    private void handleArrowKeyUp(){
        argumentListBox.setSelectedIndex(argumentListBox.getSelectedIndex() - 1);
        invocationMessageLabel.setIndex(invocationMessageLabel.getIndex() - 1);
    }

    private void cycleSelectedElement() {
        int oldIndex = dialogboxElements.indexOf(selected);
        selected = dialogboxElements.get((oldIndex++) % 7);
    }
}
