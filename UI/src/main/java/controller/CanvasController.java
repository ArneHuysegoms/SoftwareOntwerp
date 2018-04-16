package controller;

import exceptions.DomainException;
import mediator.InteractionMediator;
import subwindow.CloseButton;
import subwindow.Subwindow;
import uievents.KeyEvent;
import uievents.MouseEvent;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main layer between pure UI and the domain
 *
 * Interprets UIEvents and addresses the appropriate functions in the diagram.
 */
public class CanvasController {

    private List<SubWindowLevel> subwindows;

    private Subwindow activeSubwindow;

    public CanvasController(){
        this.setSubwindows(new ArrayList<>());
        this.activeSubwindow = null;
    }

    public List<SubWindowLevel> getSubwindows() {
        return subwindows;
    }

    private void setSubwindows(List<SubWindowLevel> subwindows) throws IllegalArgumentException{
        if(subwindows == null){
            throw new IllegalArgumentException("Subwindows may not be null");
        }
        this.subwindows = subwindows;
    }

    public Subwindow getActiveSubwindow() {
        return activeSubwindow;
    }

    private void setActiveSubwindow(Subwindow activeSubwindow) throws IllegalArgumentException {
        if(activeSubwindow == null){
            throw new IllegalArgumentException("Active subwindow may not be null");
        }
        this.activeSubwindow = activeSubwindow;
    }

    public void removeSubwindow(Subwindow subwindow){
        this.subwindows.remove(subwindow);
        this.setSubwindowLevels();
    }

    public void addSubwindow(Subwindow subwindow, int level){
        if(subwindow == null){
            throw  new IllegalArgumentException("can't add null subwindow");
        }
        SubWindowLevel subWindowLevel = new SubWindowLevel(subwindow, level);
        this.getSubwindows().add(subWindowLevel);
    }

    /**
     * set the correct level for all subwindows
     * TODO
     */
    public void setSubwindowLevels(){

    }

    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException {
        switch(keyEvent.getKeyEventType()){
            case CTRLD:
                copyActiveSubWindow();
                break;
            case CTRLN:
                createNewSubwindow();
                break;
            default:
                activeSubwindow.handleKeyEvent(keyEvent);
                setSubwindowLevels();
                break;
        }
    }

    public void handleMouseEvent(MouseEvent mouseEvent){
        Subwindow subwindow = getAppropriateSubwindow(mouseEvent.getPoint());
        Point2D relativePoint = getRelativePoint(subwindow, mouseEvent.getPoint());
        if(! subwindow.equals(getActiveSubwindow())){
            changeActiveSubwindow(activeSubwindow);
        }
        subwindow.handleMouseEvent(mouseEvent);
    }

    private void changeActiveSubwindow(Subwindow newActiveSubWindow){
        this.setActiveSubwindow(newActiveSubWindow);
        this.setSubwindowLevels();
    }

    private void createNewSubwindow(){
        Subwindow subwindow = new Subwindow(new Point2D.Double(100,100), new CloseButton(this), new InteractionMediator());
        int level = getCorrectLevel();
        addSubwindow(subwindow, level);
        this.setActiveSubwindow(subwindow);
    }

    private void copyActiveSubWindow(){
        if(this.getActiveSubwindow() != null) {
            Subwindow subwindow = new Subwindow(new Point2D.Double(100, 100), new CloseButton(this), activeSubwindow.getCopyOfFacade(), activeSubwindow.getMediator());
            int level = getCorrectLevel();
            addSubwindow(subwindow, level);
            this.setActiveSubwindow(subwindow);
        }
    }

    private Point2D getRelativePoint(Subwindow subwindow, Point2D location){
        return new Point2D.Double(location.getX() - subwindow.getPosition().getX(), location.getY()  - subwindow.getPosition().getY());
    }

    private Subwindow getAppropriateSubwindow(Point2D clickedLocation){
        List<SubWindowLevel> clickedSubwindows = this.getSubwindows()
                                                    .stream()
                                                    .filter(s -> s.getSubwindow().isClicked(clickedLocation))
                                                    .collect(Collectors.toList());
        int level = -1;
        Subwindow highest = null;
        for(SubWindowLevel s : clickedSubwindows){
            if(s.getLevel() > level){
                highest = s.getSubwindow();
                level = s.getLevel();
            }
        }
        return highest;
    }

    private class SubWindowLevel implements Comparable<SubWindowLevel> {

        private Subwindow subwindow;
        private int level;

        public SubWindowLevel(Subwindow subwindow, int level){
            this.setSubwindow(subwindow);
            this.setLevel(level);
        }

        public Subwindow getSubwindow() {
            return subwindow;
        }

        private void setSubwindow(Subwindow subwindow) {
            if(subwindow == null){
                throw new IllegalArgumentException("Subwindow may not be null");
            }
            this.subwindow = subwindow;
        }

        public int getLevel() {
            return level;
        }

        private void setLevel(int level) {
            if(level < 0){
                throw new IllegalArgumentException("level can't be negative");
            }
            this.level = level;
        }

        @Override
        public int compareTo(SubWindowLevel o) {
            // 0 moet helemaal achteraan staan, dus 0 is het grootste
            if (this.getLevel() > o.getLevel()) {
                return -1;
            } else if (this.getLevel() == o.getLevel()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
