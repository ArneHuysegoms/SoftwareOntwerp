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
import windowElements.SubwindowFrame;
import windowElements.SubwindowFrameCorner;
import windowElements.TitleBar;
import windowElements.TitleBarClick;

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

    private List<SubwindowFrameCorner> corners;
    private SubwindowFrame frame;
    private TitleBar titleBar;

    //TODO button positioning
    public Subwindow(Point2D pos, Button button, InteractionMediator mediator) {
        setWidth(600);
        setHeight(600);
        setPosition(pos);
        setLabelMode(false);
        setFacade(new DomainFacade());
        this.setButton(button);
        this.setMediator(mediator);

        createFrame();

        button.setSubwindow(this);
        mediator.addSubwindow(this);
        }

    public Subwindow(Point2D pos, Button button, DomainFacade facade, InteractionMediator mediator) {
        setWidth(600);
        setHeight(600);
        setPosition(pos);
        setLabelMode(false);
        setFacade(facade);
        this.setButton(button);
        this.setMediator(mediator);

        createFrame();

        button.setSubwindow(this);
        mediator.addSubwindow(this);
    }

    //TODO also works fot updating frame
    private void createFrame(){
        corners = new ArrayList<>();
        corners.add(new SubwindowFrameCorner(new Point2D.Double(position.getX(), position.getY())));
        corners.add(new SubwindowFrameCorner(new Point2D.Double(position.getX() + width, position.getY())));
        corners.add(new SubwindowFrameCorner(new Point2D.Double(position.getX(), position.getY() + height)));
        corners.add(new SubwindowFrameCorner(new Point2D.Double(position.getX() + width, position.getY() + height)));

        frame = new SubwindowFrame(position, width, height);

        titleBar = new TitleBar(position, width - 30);

        button.setPosition(new Point2D.Double(position.getX() + width - 30, position.getY()));
    }

    public void updateLabels(char c) {
        // probeer het label up te daten
        // zo ja:
        //      stuur naar mediator -> past alle andere subwindows aan
        // zo niet:
        //      doe niks

    }

    public InteractionMediator getMediator() {
        return mediator;
    }

    public void setMediator(InteractionMediator mediator) throws IllegalArgumentException {
        if (mediator == null) {
            throw new IllegalArgumentException("mediator may not be null");
        }
        this.mediator = mediator;
    }

    public DomainFacade getCopyOfFacade() {
        return new DomainFacade(this.getFacade().getDiagram(), DiagramRepo.copy(getFacade().getSequenceRepo()), DiagramRepo.copy(getFacade().getCommunicationRepo()));
    }

    public Point2D getAbsolutePosition(Point2D relativePoint) {
        return new Point2D.Double(relativePoint.getX() + this.getPosition().getX(), relativePoint.getY() + this.getPosition().getY());
    }

    public boolean isClicked(Point2D position) {
        double startX = this.getPosition().getX();
        double endX = this.getPosition().getX() + this.getWidth();
        double startY = this.getPosition().getY();
        double endY = this.getPosition().getY() + this.getHeight();
        return (startX <= position.getX() && endX >= position.getX()) && (startY <= position.getY() && endY >= position.getY());
    }

    public Button getButton() {
        return button;
    }

    private void setButton(Button button) {
        if (button == null) {
            throw new IllegalArgumentException("Button may not be null");
        }
        this.button = button;
    }

    public void updateLabelContainer(char c) {
        setLabelContainer(labelContainer + c);
    }


    public boolean isInLabelMode() {
        return this.labelMode;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if(width < 0){
            throw new IllegalArgumentException("Width can't be less than zero");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if(height < 0){
            throw new IllegalArgumentException("Height can't be less than zero");
        }
        this.height = height;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position2D) {
        this.position = position2D;
    }

    public boolean isLabelMode() {
        return labelMode;
    }

    public void setLabelMode(boolean labelMode) {
        this.labelMode = labelMode;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public DomainFacade getFacade() {
        return facade;
    }

    public void setFacade(DomainFacade facade) {
        this.facade = facade;
    }

    public String getLabelContainer() {
        return labelContainer;
    }

    public void setLabelContainer(String labelContainer) {
        this.labelContainer = labelContainer;
    }


    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException {
        if (!labelMode) {
            this.stopEditingLabel();
            switch (keyEvent.getKeyEventType()) {
                case TAB:
                    this.getFacade().changeActiveDiagram();
                    break;
                case DEL:
                    this.deleteElement();
                    break;
                case CHAR:
                    this.addCharToLabel(keyEvent.getKeyChar());
                    break;
                case BACKSPACE:
                    this.removeLastCharFromLabel();
                    break;
                default:
                    break;
            }
        } else {
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
     */
    public void handleMouseEvent(MouseEvent mouseEvent) throws DomainException {
        if (!labelMode) {
            if (this.selected != null && !(this.selected instanceof Label)) {
                this.stopEditingLabel();
            }
            switch (mouseEvent.getMouseEventType()) {
                case DRAG:
                    if (this.selected instanceof Party) {
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
                    if (this.selected instanceof Party) {
                        Party p = (Party) selected;
                        this.getFacade().changePartyType(p);
                    }
                    if (this.selected == null) {
                        Party newParty = this.getFacade().addNewParty(mouseEvent.getPoint());
                        selected = newParty.getLabel();
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
        if (this.selected instanceof DiagramRepo.MessageStart) {
            DiagramRepo.MessageStart ms = (DiagramRepo.MessageStart) selected;
            List<Message> newMessages = this.getFacade().addNewMessage(mouseEvent.getPoint(), ms);
            selected = newMessages.get(0).getLabel();
            mediator.addNewMessagesToOtherSubwindowRepos(newMessages, this);
        }

        else if(frameElement != null){
            if(frameElement instanceof CloseButton){
                CloseButton c = (CloseButton) frameElement;
                c.performAction();
            }
            else if(frameElement instanceof SubwindowFrameCorner){
                SubwindowFrameCorner corner = (SubwindowFrameCorner) frameElement;
                resizeByCorner(corner, mouseEvent.getPoint());
            }
            else if(frameElement instanceof SubwindowFrame.SubwindowFrameRectangle){
                SubwindowFrame.SubwindowFrameRectangle frameRectangle = (SubwindowFrame.SubwindowFrameRectangle) frameElement;
                resizeByFrameRectangle(frameRectangle, mouseEvent.getPoint());
            }
            else if(frameElement instanceof TitleBarClick){
                TitleBarClick titleBarClick = (TitleBarClick) frameElement;
                moveSubwindow(titleBarClick, mouseEvent.getPoint());

            }
        }
    }

    private void handleLeftClick(MouseEvent mouseEvent){

    }

    public void resizeByCorner(SubwindowFrameCorner corner, Point2D point){

        Point2D originalPosition = this.getPosition();
        int originalWidth = this.width;
        int originalHeight = this.height;
        if(Math.abs(corner.getCenter().getX() - this.getPosition().getX()) <= 10){
            if (Math.abs(corner.getCenter().getY() - this.getPosition().getY()) <= 10) {
                //TOP LEFT
                this.setPosition(point);
                this.setWidth( new Double(originalPosition.getX() - point.getX()).intValue() + originalWidth);
                this.setHeight(new Double(originalPosition.getY() - point.getY()).intValue() + originalHeight);
            }
            else{
                //BOTTOM LEFT
                this.setPosition(new Point2D.Double(point.getY(), this.getPosition().getY()));
                this.setWidth( new Double(originalPosition.getX() - point.getX()).intValue() + originalWidth);
                this.setHeight(new Double(point.getY() - originalPosition.getY()).intValue() + originalHeight);
            }
        }
        else {
            if (Math.abs(corner.getCenter().getY() - this.getPosition().getY()) <= 10) {
                //TOP RIGHT
                this.setPosition(new Point2D.Double(this.getPosition().getX(), point.getY()));
                this.setWidth( new Double(point.getX() - originalPosition.getX()).intValue() + originalWidth);
                this.setHeight(new Double(originalPosition.getY() - point.getY()).intValue() + originalHeight);
            }
            else{
                //BOTTOM RIGHT
                this.setWidth( new Double(point.getX() - originalPosition.getX()).intValue() + originalWidth);
                this.setHeight(new Double(point.getY() - originalPosition.getY()).intValue() + originalHeight);
            }

        }
    }

    public void resizeByFrameRectangle(SubwindowFrame.SubwindowFrameRectangle rectangle, Point2D point){
        Point2D originalPosition = this.getPosition();
        int originalWidth = this.width;
        int originalHeight = this.height;
        if(Math.abs(rectangle.getPosition().getX() - this.getPosition().getX()) <= 10){
            // LEFT
            setPosition(new Point2D.Double(point.getX(),originalPosition.getY()));
            this.setWidth( new Double(originalPosition.getX() - point.getX()).intValue() + originalWidth);
        }
        else if(Math.abs(rectangle.getPosition().getX() - (this.getPosition().getX() + originalWidth)) <= 10){
            // RIGHT
            setPosition(new Point2D.Double(point.getX(),originalPosition.getY()));
            this.setWidth( new Double(point.getX() - originalPosition.getX()).intValue() + originalWidth);
        }
        else if(Math.abs(rectangle.getPosition().getY() - this.getPosition().getY()) <= 10){
            // TOP
            setPosition(new Point2D.Double(originalPosition.getX(), point.getY()));
            this.setHeight(new Double(originalPosition.getY() - point.getY()).intValue() + originalHeight);
        }
        else if(Math.abs(rectangle.getPosition().getY() - (this.getPosition().getY() + originalHeight)) <= 10){
            // BOTTOM
            setPosition(new Point2D.Double(originalPosition.getX(), point.getY()));
            this.setHeight(new Double(point.getY() - originalPosition.getY()).intValue() + originalHeight);
        }
    }

    public void moveSubwindow(TitleBarClick titleBarClick, Point2D point){
        double x = titleBarClick.getInitialClickPosition().getX() - this.getPosition().getX();
        double y = titleBarClick.getInitialClickPosition().getY() - this.getPosition().getY();
        setPosition(new Point2D.Double(point.getX() + x, point.getY() + y));
    }

    /**
     * Handles MouseEvents of type MouseEvent.Pressed
     *
     * @param mouseEvent the event to handle
     */
    private void handleMousePressed(MouseEvent mouseEvent){
        frameElement = findClickedElementOfFrame(mouseEvent.getPoint());
        if(frameElement == null) {
            DiagramElement oldSelected = this.selected;
            DiagramElement newSelected = this.getFacade().findSelectedElement(mouseEvent.getPoint());
            if (newSelected != null) {
                if (oldSelected.equals(newSelected) && oldSelected instanceof Label) {
                    selected = newSelected;
                    this.startEditingLabel();
                } else {
                    selected = newSelected;
                }
            }
        }
    }

    private Clickable findClickedElementOfFrame(Point2D clickLocation){
        for(SubwindowFrameCorner corner : corners){
            if(corner.isClicked(clickLocation)){
                return corner;
            }
        }
        for(SubwindowFrame.SubwindowFrameRectangle rectangle : frame.getRectangles()){
            if(rectangle.isClicked(clickLocation)){
                return rectangle;
            }
        }
        if(titleBar.isClicked(clickLocation)){
            return new TitleBarClick(titleBar, clickLocation);
        }
        if(button.isClicked(clickLocation)){
            return button;
        }
        return null;
    }

    private void startEditingLabel(){
        Label labelInEdit = (Label) selected;
        labelContainer = labelInEdit.getLabel() + "I";
    }

    private void deleteElement() {
        if (selected instanceof Label) {
            Label l = (Label) selected;
            Set<DiagramElement> deletedElements = facade.deleteElementByLabel(l);
            mediator.removeInReposInOtherSubwindows(deletedElements, this);
        }
    }

    private void stopEditingLabel() {
        selected = null;
        labelContainer = "";
    }

    private void addCharToLabel(char c) throws DomainException {
        String l = labelContainer.substring(0, getLabelContainer().length() - 1);
        l += c;
        l += "I";
        labelContainer = l;
        handleChangeInLabel();
    }

    private void removeLastCharFromLabel() throws DomainException {
        String l = labelContainer.substring(0, getLabelContainer().length() - 2);
        l += "I";
        labelContainer = l;
        labelMode = !checkIfValidLable();
        handleChangeInLabel();
    }

    private void handleChangeInLabel() throws DomainException {
        if (checkIfValidLable()) {
            labelMode = false;
            Label selectedLabel = (Label) selected;
            selectedLabel.setLabel(labelContainer.substring(0, getLabelContainer().length() - 1));
        } else {
            labelMode = true;
        }
    }

    private boolean checkIfValidLable() {
        if (selected instanceof Label) {
            Label l = (Label) selected;
            return l.isValidLabel(getLabelContainer().substring(0, getLabelContainer().length() - 1));
        }
        return true;
    }

    /*
     * Reads a key event and alters the active diagram based on it
     *
     * @param keyEvent the KeyEvent that happened in the UI, comes from the InteractrCanvas
     *//*
    public void handleKeyEvent(KeyEvent keyEvent){
        if(checkIfValidLable()){
            this.getFacade().stopEditingLabel();
            switch (keyEvent.getKeyEventType()){
                case TAB:
                    this.getFacade().changeActiveDiagram();
                    break;
                case DEL:
                    this.getFacade().deleteElement();
                    break;
                case CHAR:
                    if(this.getFacade().selectedElementIsLabel()){
                        this.getFacade().addCharToLabel(keyEvent.getKeyChar());
                    }
                    break;
                case BACKSPACE:
                    this.getFacade().removeLastCharFromLabel();
                    break;
                default:
                    break;
            }
        }
        else{
          switch (keyEvent.getKeyEventType()){
              case CHAR:
                  if(this.getFacade().selectedElementIsLabel()){
                      this.getFacade().addCharToLabel(keyEvent.getKeyChar());
                  }
                  break;
              case BACKSPACE:
                  this.getFacade().removeLastCharFromLabel();
                  break;
              default:
                  break;
          }
        }
    }

    *//*
     * Reads a mouse event and alters the active diagram based on it
     *
     * @param mouseEvent the MouseEvent that happened in the UI, comes from the InteractrCanvas
     *//*
    public void handleMouseEvent(MouseEvent mouseEvent){
        if(checkIfValidLable()){
            if(this.getFacade().getSelectedElement() != null && !this.getFacade().selectedElementIsLabel()){
                this.getFacade().stopEditingLabel();
            }
            switch (mouseEvent.getMouseEventType()){
                case DRAG:
                    if(this.getFacade().selectedElementIsParty()){
                        this.getFacade().changePartyPosition(mouseEvent.getPoint());
                    }
                    break;
                case PRESSED:
                    handleMousePressed(mouseEvent);
                    break;
                case RELEASE:
                    if(this.getFacade().selectedElementIsMessageStart()){
                        this.getFacade().addNewMessage(mouseEvent.getPoint());
                    }
                    break;
                case LEFTCLICK:
                    handleLeftClick(mouseEvent);
                    break;
                case LEFTDOUBLECLICK:
                    if(this.getFacade().selectedElementIsParty()){
                        this.getFacade().changePartyType(mouseEvent.getPoint());
                    }
                    if(this.getFacade().getSelectedElement() == null){
                        this.getFacade().addNewParty(mouseEvent.getPoint());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    *//*
     *
     * @return true if the label in edit is valid, false otherwise
     *//*
    private boolean checkIfValidLable(){
        return this.getFacade().checkIfValidLable();
    }*/
}
