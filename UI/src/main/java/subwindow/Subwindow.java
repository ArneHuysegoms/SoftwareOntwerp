package subwindow;

import diagram.label.Label;
import exceptions.DomainException;
import facade.DomainFacade;
import mediator.InteractionMediator;

import java.awt.geom.Point2D;

public class Subwindow {
    private int width;
    private int height;
    private Point2D position;
    private int level;
    private boolean labelMode;
    private Label label;
    private DomainFacade facade;
    private InteractionMediator mediator;

    public Subwindow(int width, int height, Point2D pos, int level, boolean labelMode, Label label, DomainFacade facade){
        setWidth(width);
        setHeight(height);
        setPosition(pos);
        setLevel(level);
        setLabelMode(labelMode);
        setLabel(label);
        setFacade(facade);
    }

    public void updateLabels(char c){
        // probeer het label up te daten
        // zo ja:
        //      stuur naar mediator -> past alle andere subwindows aan
        // zo niet:
        //      doe niks
        try {
            this.getFacade().getActiveRepo().getLabelRepo().
            this.label.setLabel(label.getLabel() + c);
            this.mediator.updateLabel(this.label);
        }
        catch (DomainException e){
            System.out.println(e.getMessage());
        }
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
}
