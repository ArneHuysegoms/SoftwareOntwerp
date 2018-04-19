package subwindow;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import exceptions.DomainException;
import facade.DomainFacade;
import mediator.InteractionMediator;
import repo.diagram.DiagramRepo;
import uievents.KeyEvent;
import uievents.MouseEvent;
import windowElements.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Subwindow {
    private int width;
    private int height;
    private Point2D position;
    private boolean labelMode;
    private Label label;
    private DomainFacade facade;
    private String labelContainer = "";
    private InteractionMediator mediator;
    private Button button;

    private DiagramElement selected;
    private Clickable frameElement;

    private SubwindowFrame frame;

    private boolean dragging = false;

    /**
     * default contructor for subwindow with default width and height
     *
     * @param pos
     * @param button
     * @param mediator
     */
    //TODO button positioning
    public Subwindow(Point2D pos, Button button, InteractionMediator mediator) {
        setWidth(600);
        setHeight(600);
        setPosition(pos);
        setLabelMode(false);
        setFacade(new DomainFacade());
        this.setButton(button);
        this.setMediator(mediator);

        createFrame(button);

        button.setSubwindow(this);
        mediator.addSubwindow(this);
    }

    /**
     * contructor for subwindow with default width and height
     *
     * @param pos
     * @param button
     * @param facade
     * @param mediator
     */
    public Subwindow(Point2D pos, Button button, DomainFacade facade, InteractionMediator mediator) {
        setWidth(600);
        setHeight(600);
        setPosition(pos);
        setLabelMode(false);
        setFacade(facade);
        this.setButton(button);
        this.setMediator(mediator);

        createFrame(button);

        button.setSubwindow(this);
        mediator.addSubwindow(this);
    }

    /**
     * creates the frame with corners for resizing, titlebar and close button
     */
    //TODO also works for updating frame
    private void createFrame(Button button) {

        frame = new SubwindowFrame(position, height, width, button);

    }

    /**
     * @return wether or not this diagram is dragging something
     */
    public boolean isDragging() {
        return dragging;
    }

    /**
     * @return mediator for this subwindow
     */
    public InteractionMediator getMediator() {
        return mediator;
    }

    /**
     * sets the mediator for this subwindow
     *
     * @param mediator
     */
    public void setMediator(InteractionMediator mediator) throws IllegalArgumentException {
        if (mediator == null) {
            throw new IllegalArgumentException("mediator may not be null");
        }
        this.mediator = mediator;
    }

    /**
     * @return a copy of the facade
     */
    public DomainFacade getCopyOfFacade() {
        return new DomainFacade(this.getFacade().getDiagram(), DiagramRepo.copy(getFacade().getSequenceRepo()), DiagramRepo.copy(getFacade().getCommunicationRepo()));
    }

    /**
     * @return the absolute postion for a relative point within this subwindow
     */
    public Point2D getAbsolutePosition(Point2D relativePoint) {
        return new Point2D.Double(relativePoint.getX() + this.getPosition().getX(), relativePoint.getY() + this.getPosition().getY());
    }

    /**
     * checks if this button is clicked
     *
     * @param position
     */
    public boolean isClicked(Point2D position) {
        double startX = this.getPosition().getX();
        double endX = this.getPosition().getX() + this.getWidth();
        double startY = this.getPosition().getY();
        double endY = this.getPosition().getY() + this.getHeight();
        return (startX <= position.getX() && endX >= position.getX()) && (startY <= position.getY() && endY >= position.getY());
    }

    /**
     * @return the close button for this subwindow
     */
    public Button getButton() {
        return button;
    }

    /**
     * sets the close button for this subwindow
     *
     * @param button
     */
    private void setButton(Button button) {
        if (button == null) {
            throw new IllegalArgumentException("Button may not be null");
        }
        this.button = button;
    }

    /**
     * update the container with the label
     *
     * @param c
     */
    public void updateLabelContainer(char c) {
        setLabelContainer(labelContainer + c);
    }

    /**
     * checks if this subwindow is in labelmode
     */
    public boolean isInLabelMode() {
        return this.labelMode;
    }

    /**
     * @return the width of this subwindow
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the width of this subwindow
     *
     * @param width
     */
    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width can't be less than zero");
        }
        this.width = width;
    }

    /**
     * @return the height of this subwindow
     */
    public int getHeight() {
        return height;
    }

    /**
     * sets the height of this subwindow
     *
     * @param height
     */
    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height can't be less than zero");
        }
        this.height = height;
    }

    /**
     * @return the position of the upper left corner of this subwindow
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * sets the position of the upper left corner for this subwindow
     *
     * @param position2D
     */
    public void setPosition(Point2D position2D) {
        this.position = position2D;
    }

    /**
     * @return true if the subwindow is in label mode
     */
    public boolean isLabelMode() {
        return labelMode;
    }

    /**
     * sets the subwindow in the given labelmode
     *
     * @param labelMode
     */
    public void setLabelMode(boolean labelMode) {
        this.labelMode = labelMode;
    }

    /**
     * @return the active label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * sets the active label
     *
     * @param label
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * @return the facade for this subwindow
     */
    public DomainFacade getFacade() {
        return facade;
    }

    /**
     * sets the facade for this subwindow
     *
     * @param facade
     */
    public void setFacade(DomainFacade facade) {
        this.facade = facade;
    }

    /**
     * @return the text labelcontainer for the active label
     */
    public String getLabelContainer() {
        return labelContainer;
    }

    /**
     * sets the labelcontainer for the active label
     *
     * @param labelContainer
     */
    public void setLabelContainer(String labelContainer) {
        this.labelContainer = labelContainer;
    }

    public DiagramElement getSelected() {
        return selected;
    }

    public void setSelected(DiagramElement selected) {
        this.selected = selected;
    }

    public SubwindowFrame getFrame() {
        return frame;
    }

    private void setFrame(SubwindowFrame frame) {
        this.frame = frame;
    }

    /**
     * handle the given keyevent accordingly
     *
     * @param keyEvent
     */
    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException {
        if (!labelMode) {
            switch (keyEvent.getKeyEventType()) {
                case TAB:
                    this.getFacade().changeActiveDiagram();
                    break;
                case DEL:
                    this.deleteElement();
                    break;
                case CHAR:
                    if (selectedElementIsLabel()) {
                        this.addCharToLabel(keyEvent.getKeyChar());
                    }
                    break;
                case BACKSPACE:
                    if (selectedElementIsLabel()) {
                        this.removeLastCharFromLabel();
                    }
                    break;
                default:
                    break;
            }
        } else if (selectedElementIsLabel()) {
            switch (keyEvent.getKeyEventType()) {
                case CHAR:
                    this.addCharToLabel(keyEvent.getKeyChar());
                    break;
                case BACKSPACE:
                    this.removeLastCharFromLabel();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Reads a mouse event and alters the active diagram based on it
     *
     * @param mouseEvent the MouseEvent that happened in the UI, comes from the InteractrCanvas
     * @throws DomainException
     */
    public void handleMouseEvent(MouseEvent mouseEvent) {
        if (!labelMode) {
            switch (mouseEvent.getMouseEventType()) {
                case DRAG:
                    dragging = true;
                    if (selectedElementIsParty()) {
                        Party p = (Party) selected;
                        this.getFacade().changePartyPosition(mouseEvent.getPoint(), p);
                    }
                    break;
                case PRESSED:
                    handleMousePressed(mouseEvent);
                    break;
                case RELEASE:
                    handleReleaseClick(mouseEvent);
                    break;
                case LEFTCLICK:
                    handleLeftClick(mouseEvent);
                    break;
                case LEFTDOUBLECLICK:
                    if (selectedElementIsParty()) {
                        Party oldParty = (Party) selected;
                        Party newParty = this.getFacade().changePartyType(oldParty);
                        selected = newParty;
                        mediator.updatePartyTypeInOtherSubwindows(oldParty, newParty, this);
                    }
                    if (this.selected == null) {
                        Party newParty = this.getFacade().addNewParty(mouseEvent.getPoint());
                        selected = newParty.getLabel();
                        startEditingLabel();
                        mediator.addNewPartyToOtherSubwindowRepos(newParty, mouseEvent.getPoint(), this);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * handle a left click on the UI
     *
     * @param mouseEvent the MouseEvent containing the information of the event
     */
    private void handleReleaseClick(MouseEvent mouseEvent) {
        dragging = false;
        if (selectedElementIsMessageStart()) {
            DiagramRepo.MessageStart ms = (DiagramRepo.MessageStart) selected;
            List<Message> newMessages = this.getFacade().addNewMessage(mouseEvent.getPoint(), ms);
            selected = newMessages.get(0).getLabel();
            startEditingLabel();
            mediator.addNewMessagesToOtherSubwindowRepos(newMessages, this);
        }
    }

    public void handleMovement(Point2D movedLocation) {
        if (frameElement != null) {
            if (frameElement instanceof CloseButton) {
                CloseButton c = (CloseButton) frameElement;
                c.performAction();
            } else if (frameElement instanceof SubwindowFrameCorner) {
                SubwindowFrameCorner corner = (SubwindowFrameCorner) frameElement;
                resizeByCorner(corner, movedLocation);
                createFrame(frame.getButton());
            } else if (frameElement instanceof SubwindowFrameRectangle) {
                SubwindowFrameRectangle frameRectangle = (SubwindowFrameRectangle) frameElement;
                resizeByFrameRectangle(frameRectangle, movedLocation);
                createFrame(frame.getButton());
            } else if (frameElement instanceof TitleBarClick) {
                TitleBarClick titleBarClick = (TitleBarClick) frameElement;
                moveSubwindow(titleBarClick, movedLocation);
                createFrame(frame.getButton());
            }
        }
    }

    private void handleLeftClick(MouseEvent mouseEvent) {

    }

    /**
     * resize the subwindow when the user drags by the corner
     *
     * @param corner
     * @param point
     */
    public void resizeByCorner(SubwindowFrameCorner corner, Point2D point) {

        Point2D ogPos = this.getPosition();
        switch (corner.getType()) {
            case TOPLEFT:
                if(! (point.getX() > (ogPos.getX() + width) || point.getY() > (ogPos.getY() + height))) {
                    double deltaTopleftX = -(corner.getCenter().getX() - point.getX());
                    double deltaTopleftY = -(corner.getCenter().getY() - point.getY());
                    this.setPosition(new Point2D.Double(ogPos.getX() + deltaTopleftX, ogPos.getY() + deltaTopleftY));
                    this.setHeight(new Double(getHeight() - deltaTopleftY).intValue());
                    this.setWidth(new Double(getWidth() - deltaTopleftX).intValue());
                }
                break;
            case TOPRIGHT:
                if(! (point.getX() < (ogPos.getX() - width) || point.getY() > (ogPos.getY() + height))) {
                    double deltaToprightX = -(corner.getCenter().getX() - point.getX());
                    double deltaToprightY = -(corner.getCenter().getY() - point.getY());
                    this.setPosition(new Point2D.Double(ogPos.getX(), ogPos.getY() + deltaToprightY));
                    this.setHeight(new Double(getHeight() - deltaToprightY).intValue());
                    this.setWidth(new Double(getWidth() + deltaToprightX).intValue());
                }
                break;
            case BOTTOMLEFT:
                if(! (point.getX() > (ogPos.getX() + width) || point.getY() < (ogPos.getY() - height))) {
                    double deltaBottomleftX = -(corner.getCenter().getX() - point.getX());
                    double deltaBottomleftY = -(corner.getCenter().getY() - point.getY());
                    this.setPosition(new Point2D.Double(ogPos.getX() + deltaBottomleftX, ogPos.getY()));
                    this.setHeight(new Double(getHeight() + deltaBottomleftY).intValue());
                    this.setWidth(new Double(getWidth() - deltaBottomleftX).intValue());
                }
                break;
            case BOTTOMRIGHT:
                if(! (point.getX() < (ogPos.getX() - width) || point.getY() < (ogPos.getY() - height))) {
                    double deltaBottomrightX = -(corner.getCenter().getX() - point.getX());
                    double deltaBottomrightY = -(corner.getCenter().getY() - point.getY());
                    //this.setPosition(new Point2D.Double(ogPos.getX() + deltaBottomrightX, ogPos.getY() + deltaBottomrightY));
                    this.setHeight(new Double(getHeight() + deltaBottomrightY).intValue());
                    this.setWidth(new Double(getWidth() + deltaBottomrightX).intValue());
                }
                break;
            default:
                break;
        }
        createFrame(getButton());
    }

    /**
     * resize the subwindow when the user drags by one of the borders
     *
     * @param rectangle
     * @param point
     */
    public void resizeByFrameRectangle(SubwindowFrameRectangle rectangle, Point2D point) {
        switch (rectangle.getType()) {
            case TOP:
                double topDelta = this.getPosition().getY() - point.getY();
                this.setPosition(new Point2D.Double(position.getX(), position.getY() - topDelta));
                this.setHeight(new Double(getHeight() + topDelta).intValue());
                break;
            case LEFT:
                double leftDelta = this.getPosition().getX() - point.getX();
                this.setPosition(new Point2D.Double(position.getX() - leftDelta, position.getY()));
                this.setWidth(new Double(getWidth() + leftDelta).intValue());
                break;
            case RIGHT:
                double rightDelta = -(this.getPosition().getX() + width - point.getX());
                this.setWidth(new Double(getWidth() + rightDelta).intValue());
                break;
            case BOTTOM:
                double bottomDelta = -(this.getPosition().getY() + height - point.getY());
                this.setHeight(new Double(getHeight() + bottomDelta).intValue());
                break;
            default:
                break;
        }
        createFrame(frame.getButton());
    }

    public boolean selectedElementIsParty() {
        return selected instanceof Party;
    }

    public boolean selectedElementIsLabel() {
        return selected instanceof Label;
    }

    public boolean selectedElementIsMessageStart() {
        return selected instanceof DiagramRepo.MessageStart;
    }

    /**
     * move the subwindow when the user drags by the titlebar
     *
     * @param titleBarClick
     * @param point
     */
    public void moveSubwindow(TitleBarClick titleBarClick, Point2D point) {
        double x = point.getX() - titleBarClick.getInitialClickPosition().getX();
        double y = point.getY() - titleBarClick.getInitialClickPosition().getY();
        setPosition(new Point2D.Double(position.getX() + x, position.getY() + y));
        createFrame(frame.getButton());
    }

    /**
     * Handles MouseEvents of type MouseEvent.Pressed
     *
     * @param mouseEvent the event to handle
     */
    private void handleMousePressed(MouseEvent mouseEvent) {
        DiagramElement oldSelected = this.selected;
        DiagramElement newSelected = this.getFacade().findSelectedElement(mouseEvent.getPoint());
        if (oldSelected != null && oldSelected.equals(newSelected) && oldSelected instanceof Label) {
            selected = newSelected;
            this.startEditingLabel();
        } else if (newSelected instanceof Label){
            selected = newSelected;
        }
        else{
            stopEditingLabel();
            selected = newSelected;
        }
    }

    public Point2D getRelativePoint(Point2D location) {
        return new Point2D.Double(location.getX() - this.getPosition().getX(), location.getY() - this.getPosition().getY());
    }

    public boolean frameIsClicked(Point2D clickLocation) {
        frameElement = frame.getFrameElement(clickLocation);
        return this.frame.isClicked(clickLocation);
    }

    /**
     * start editing a label in the subwindow
     */
    private void startEditingLabel() {
        labelMode = true;
        Label labelInEdit = (Label) selected;
        labelContainer = labelInEdit.getLabel() + "I";
    }

    /**
     * delete the elements in the repos of the other subwindows
     */
    private void deleteElement() {
        if (selectedElementIsLabel()) {
            Label l = (Label) selected;
            Set<DiagramElement> deletedElements = facade.deleteElementByLabel(l);
            mediator.removeInReposInOtherSubwindows(deletedElements, this);
            stopEditingLabel();
        }
    }

    /**
     * start editing a label in the subwindow
     */
    public void stopEditingLabel() {
        labelMode = false;
        labelContainer = "";
        selected = null;
    }

    /**
     * adds the given char to the active label
     *
     * @param c
     */
    private void addCharToLabel(char c) throws DomainException {
        String l = "";
        if (labelContainer.equals("")) {
            l = "";
        } else {
            l = labelContainer.substring(0, labelContainer.length() - 1);
        }
        l += c;
        l += "I";
        labelContainer = l;
        handleChangeInLabel();
    }

    /**
     * removes the last char from the active label
     */
    private void removeLastCharFromLabel() throws DomainException {
        if (labelContainer.length() > 1) {
            String l = labelContainer.substring(0, getLabelContainer().length() - 2);
            l += "I";
            labelContainer = l;
            labelMode = !checkIfValidLable();
            handleChangeInLabel();
        }
    }

    /**
     * handle a change in the active label
     */
    private void handleChangeInLabel() throws DomainException {
        if (checkIfValidLable()) {
            labelMode = false;
            Label selectedLabel = (Label) selected;
            selectedLabel.setLabel(labelContainer.substring(0, getLabelContainer().length() - 1));
            mediator.updateLabelContainers(selectedLabel, this);
        } else {
            labelMode = true;
        }
    }

    /**
     * check if the active label is valid
     *
     * @return true if the label is valid
     */
    public boolean checkIfValidLable() {
        if (getLabelContainer().equals("")) {
            return false;
        }
        if (selectedElementIsLabel() && labelContainer.length() > 0) {
            Label l = (Label) selected;
            return l.isValidLabel(getLabelContainer().substring(0, getLabelContainer().length() - 1)) && !getLabelContainer().equals("");
        }
        return true;
    }
}
