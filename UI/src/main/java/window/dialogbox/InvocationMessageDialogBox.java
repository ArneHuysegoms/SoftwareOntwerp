package window.dialogbox;

import action.*;
import diagram.label.InvocationMessageLabel;
import diagram.message.InvocationMessage;
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
import java.util.stream.Collectors;

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
        moveDown = new TextualFakeButton(new Point2D.Double(50, 100), "Down");
        moveUp = new TextualFakeButton(new Point2D.Double(90, 100), "Up");

        this.subwindow = subwindow;

        argumentListBox = new ListBox(new Point2D.Double(10, 140), "");

        dialogboxElements = new ArrayList<>();
        dialogboxElements.add(methodTextBox);
        dialogboxElements.add(argumentTextBox);
        dialogboxElements.add(addArgument);
        dialogboxElements.add(deleteArgument);
        dialogboxElements.add(moveDown);
        dialogboxElements.add(moveUp);
        dialogboxElements.add(argumentListBox);

        selected = methodTextBox;

        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);

        updateFields((InvocationMessage) subwindow.getFacade().findParentElement(invocationMessageLabel));
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public MethodTextBox getMethodTextBox() {
        return methodTextBox;
    }

    public ArgumentTextBox getArgumentTextBox() {
        return argumentTextBox;
    }

    public ListBox getArgumentListBox() {
        return argumentListBox;
    }

    public FakeButton getAddArgument() {
        return addArgument;
    }

    public FakeButton getDeleteArgument() {
        return deleteArgument;
    }

    public FakeButton getMoveUp() {
        return moveUp;
    }

    public FakeButton getMoveDown() {
        return moveDown;
    }

    public DialogboxElement getSelected() {
        return selected;
    }

    public List<DialogboxElement> getDialogboxElements() {
        return dialogboxElements;
    }

    public InvocationMessageLabel getInvocationMessageLabel() {
        return invocationMessageLabel;
    }

    public DiagramSubwindow getSubwindow() {
        return subwindow;
    }

    @Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getMouseEventType()) {
            case PRESSED:
                return handlePressed(mouseEvent.getPoint());
            default:
                break;
        }
        return new EmptyAction();
    }

    private Action handlePressed(Point2D point) {
        if (methodTextBox.isClicked(point)) {
            selected = methodTextBox;
        } else if (argumentTextBox.isClicked(point)) {
            selected = argumentTextBox;
        } else if (addArgument.isClicked(point)) {
            selected = addArgument;
            return handleAddArgument();
        } else if (argumentListBox.isClicked(point)) {
            selected = argumentListBox;
            handleArgumentListBox(point);
        }
        if (additionalButtonsAreActive()) {
            if (deleteArgument.isClicked(point)) {
                selected = deleteArgument;
                return handleDeleteArgument();
            } else if (moveDown.isClicked(point)) {
                selected = moveDown;
                return handleMoveDown();
            } else if (moveUp.isClicked(point)) {
                selected = moveUp;
                return handleMoveUp();
            }
        }
        return new EmptyAction();
    }

    @Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
        try {
            switch (keyEvent.getKeyEventType()) {
                case BACKSPACE:
                    return handleBackSpace();
                case CHAR:
                    return handleChar(keyEvent);
                case ARROWKEYDOWN:
                    handleArrowKeyDown();
                    break;
                case ARROWKEYUP:
                    handleArrowKeyUp();
                    break;
                case SPACE:
                    return handleSpace();
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
        return new EmptyAction();
    }

    private Action handleBackSpace() throws DomainException{
        if (selected instanceof TextBox) {
            TextBox t = (TextBox) selected;
            t.deleteLastCharFromContents();
            if (t == methodTextBox) {
                return changeMethod();
            }
        }
        return new EmptyAction();
    }

    private Action handleChar(KeyEvent keyEvent) throws DomainException{
        if (selected instanceof TextBox) {
            TextBox t = (TextBox) selected;
            t.addCharToContents(keyEvent.getKeyChar());
            if (t == methodTextBox) {
                return changeMethod();
            }
        }
        return new EmptyAction();
    }

    private Action changeMethod() throws DomainException {
        if(methodTextBox.hasValidContents()){
            invocationMessageLabel.setLabel(methodTextBox.getContents());
            return new UpdateLabelContainersAction(invocationMessageLabel);
        }
        return new EmptyAction();
    }

    private Action handleSpace(){
        if (addArgument == selected) {
            return handleAddArgument();
        }
        else if (additionalButtonsAreActive()) {
            if (deleteArgument == selected) {
                return handleDeleteArgument();
            } else if (moveDown == selected) {
                return handleMoveDown();
            } else if (moveUp == selected) {
                return handleMoveUp();
            }
        }
        return new UpdateLabelContainersAction(invocationMessageLabel);
    }

    private boolean additionalButtonsAreActive() {
        return argumentListBox.hasSelectedArgument();
    }


    private Action handleAddArgument() {
        if (argumentTextBox.hasValidContents()) {
            String argumentString = argumentTextBox.getContents();
            argumentListBox.addArgument(argumentString);
            String[] args = argumentString.split(":");
            invocationMessageLabel.addArgument(args[0], args[1]);
            return new UpdateLabelContainersAction(invocationMessageLabel);
        }
        return new EmptyAction();
    }

    private void handleArgumentListBox(Point2D point) {
        selected = argumentListBox;
        argumentListBox.selectArgument(point);
        invocationMessageLabel.setIndex(argumentListBox.getSelectedIndex());
    }

    private Action handleDeleteArgument() {
        invocationMessageLabel.deleteArgument(argumentListBox.getSelectedIndex());
        argumentListBox.removeArgument();
        return new UpdateLabelContainersAction(invocationMessageLabel);
    }

    private Action handleMoveUp() {
        argumentListBox.moveUp();
        invocationMessageLabel.moveUp();
        return new UpdateLabelContainersAction(invocationMessageLabel);
    }

    private Action handleMoveDown() {
        argumentListBox.moveDown();
        invocationMessageLabel.moveDown();
        return new UpdateLabelContainersAction(invocationMessageLabel);
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
        selected = dialogboxElements.get((oldIndex + 1) % 7);
    }

    @Override
    public void handleAction(Action action) {
        if(action instanceof RemoveInReposAction) {
            RemoveInReposAction a = (RemoveInReposAction) action;
            InvocationMessage invocationMessage = (InvocationMessage) subwindow.getFacade().findParentElement(invocationMessageLabel);
            if(a.getDeletedElements().contains(invocationMessage)){
                this.getFrame().close();
            }
        }
        if(action instanceof UpdateLabelAction){
            UpdateLabelAction a = (UpdateLabelAction) action;
            InvocationMessage invocationMessage = (InvocationMessage) subwindow.getFacade().findParentElement(invocationMessageLabel);
            if(a.getElement().equals(invocationMessage)){
                updateFields((InvocationMessage) a.getElement());
            }
        }
    }

    private void updateFields(InvocationMessage invocationMessage) {
        if(invocationMessage != null) {
            InvocationMessageLabel label = (InvocationMessageLabel) invocationMessage.getLabel();
            methodTextBox.setContents(label.getLabel());
            argumentListBox.setArguments(label.getArguments().stream()
                    .map(a -> a.toString())
                    .collect(Collectors.toList()));
        }
    }

    public void setSelected(DialogboxElement dialogboxElement){
        this.selected = dialogboxElement;
    }
}
