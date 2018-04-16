package subwindow;

import diagram.DiagramElement;
import diagram.label.Label;
import exceptions.DomainException;
import facade.DomainFacade;
import mediator.InteractionMediator;
import repo.diagram.DiagramRepo;
import uievents.KeyEvent;
import uievents.MouseEvent;

import java.awt.geom.Point2D;
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

    //TODO add mediator to constructors
    public Subwindow(Point2D pos, Button button, InteractionMediator mediator){
        setWidth(600);
        setHeight(600);
        setPosition(pos);
        setLabelMode(false);
        setFacade(new DomainFacade());
        this.setButton(button);
        this.setMediator(mediator);

        button.setSubwindow(this);
        mediator.addSubwindow(this);
    }

    public Subwindow(Point2D pos, Button button, DomainFacade facade, InteractionMediator mediator){
        setWidth(600);
        setHeight(600);
        setPosition(pos);
        setLabelMode(false);
        setFacade(facade);
        this.setButton(button);
        this.setMediator(mediator);

        button.setSubwindow(this);
        mediator.addSubwindow(this);
    }

    public void updateLabels(char c){
        // probeer het label up te daten
        // zo ja:
        //      stuur naar mediator -> past alle andere subwindows aan
        // zo niet:
        //      doe niks

    }

    public InteractionMediator getMediator() {
        return mediator;
    }

    public void setMediator(InteractionMediator mediator) throws IllegalArgumentException{
        if(mediator == null){
            throw new IllegalArgumentException("mediator may not be null");
        }
        this.mediator = mediator;
    }

    public DomainFacade getCopyOfFacade(){
        return new DomainFacade(this.getFacade().getDiagram(), DiagramRepo.copy(getFacade().getSequenceRepo()), DiagramRepo.copy(getFacade().getCommunicationRepo()));
    }

    public Point2D getAbsolutePosition(Point2D relativePoint){
        return new Point2D.Double(relativePoint.getX() + this.getPosition().getX(), relativePoint.getY() + this.getPosition().getY());
    }

    public boolean isClicked(Point2D position){
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
        if(button == null){
            throw new IllegalArgumentException("Button may not be null");
        }
        this.button = button;
    }

    public void updateLabelContainer(char c){
        setLabelContainer(labelContainer + c);
    }


    public boolean isInLabelMode(){
        return this.labelMode;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
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
        if(! labelMode){
            this.stopEditingLabel();
            switch (keyEvent.getKeyEventType()){
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
        }
        else{
            switch (keyEvent.getKeyEventType()){
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
    public void handleMouseEvent(MouseEvent mouseEvent){
        if(! labelMode){
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

    /**
     * handle a left click on the UI
     *
     * @param mouseEvent the MouseEvent containing the information of the event
     */
    private void handleLeftClick(MouseEvent mouseEvent){
        Clickable selected = this.getFacade().getSelectedElement();
        Clickable newSelected = this.getFacade().findSelectedElement(mouseEvent.getPoint());
        if(selected != null) {
            if (selected.equals(newSelected) && this.getFacade().selectedElementIsLabel()) {
                this.getFacade().editLabel();
            }
        }
    }

    /**
     * Handles MouseEvents of type MouseEvent.Pressed
     *
     * @param mouseEvent the event to handle
     */
    private void handleMousePressed(MouseEvent mouseEvent){
        Clickable wouldBe = this.getFacade().wouldBeSelectedElement(mouseEvent.getPoint());
        if(! this.getFacade().isLabel(wouldBe)){
            this.getFacade().setSelectedElement(wouldBe);
        }
    }

    private void deleteElement(){
        if(selected instanceof Label){
            Label l = (Label) selected;
            Set<DiagramElement> deletedElements = facade.deleteElementByLabel(l);
            mediator.removeInReposInOtherSubwindows(deletedElements, this);
        }
    }

    private void stopEditingLabel(){
        selected = null;
        labelContainer = "";
    }

    private void addCharToLabel(char c) throws DomainException{
        String l = labelContainer.substring(0, getLabelContainer().length()-1);
        l += c;
        l += "I";
        labelContainer = l;
        handleChangeInLabel();
    }

    private void removeLastCharFromLabel() throws DomainException{
        String l = labelContainer.substring(0, getLabelContainer().length()-2);
        l += "I";
        labelContainer = l;
        labelMode = ! checkIfValidLable();
        handleChangeInLabel();
    }

    private void handleChangeInLabel() throws DomainException{
        if(checkIfValidLable()){
            labelMode = false;
            Label selectedLabel = (Label) selected;
            selectedLabel.setLabel(labelContainer.substring(0, getLabelContainer().length()-1));
        }
        else{
            labelMode = true;
        }
    }

    private boolean checkIfValidLable(){
        if(selected instanceof Label){
            Label l = (Label) selected;
            return l.isValidLabel(getLabelContainer().substring(0, getLabelContainer().length()-1));
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
    }
}
