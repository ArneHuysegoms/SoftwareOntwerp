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
 * <p>
 * Interprets UIEvents and addresses the appropriate functions in the diagram.
 */
public class CanvasController {

    private List<SubWindowLevel> subwindows;

    private Subwindow activeSubwindow;

    private boolean dragging = false;

    public CanvasController() {
        this.setSubwindows(new ArrayList<>());
        this.activeSubwindow = null;
    }

    public List<SubWindowLevel> getSubwindows() {
        return subwindows;
    }

    private void setSubwindows(List<SubWindowLevel> subwindows) throws IllegalArgumentException {
        if (subwindows == null) {
            throw new IllegalArgumentException("Subwindows may not be null");
        }
        this.subwindows = subwindows;
    }

    public Subwindow getActiveSubwindow() {
        return activeSubwindow;
    }

    private void setActiveSubwindow(Subwindow activeSubwindow) throws IllegalArgumentException {
        this.activeSubwindow = activeSubwindow;
    }

    public void removeSubwindow(Subwindow subwindow) {
       SubWindowLevel toRemove = null;
        for (SubWindowLevel s : subwindows) {
            if (s.getSubwindow().equals(subwindow)) {
                toRemove = s;
            }
        }
        this.getSubwindows().remove(toRemove);
        setNewActiveSubWindow();
     }

    public void setNewActiveSubWindow() {
        SubWindowLevel s = findHighestSubwindowLevel();
        if(s != null) {
            changeActiveSubwindow(s.getSubwindow());
        }
        else {
            changeActiveSubwindow(null);
        }
    }

    public SubWindowLevel findHighestSubwindowLevel() {
        int level = -1;
        SubWindowLevel chosen = null;
        for (SubWindowLevel s : subwindows) {
            if (s.getLevel() > level) {
                chosen = s;
                level = s.getLevel();
            }
        }
        return chosen;
    }

    public void addSubwindow(Subwindow subwindow, int level) {
        if (subwindow == null) {
            throw new IllegalArgumentException("can't add null subwindow");
        }
        SubWindowLevel subWindowLevel = new SubWindowLevel(subwindow, level);
        this.getSubwindows().add(subWindowLevel);
    }

    /**
     * set the correct level for all subwindows
     * TODO
     */
    //public void setSubwindowLevels(){ }

    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException {
        switch (keyEvent.getKeyEventType()) {
            case CTRLD:
                if(getActiveSubwindow() != null && ! getActiveSubwindow().isInLabelMode()) {
                    copyActiveSubWindow();
                }
                break;
            case CTRLN:
                if(getActiveSubwindow() == null || (getActiveSubwindow() != null && ! getActiveSubwindow().isInLabelMode())) {
                    createNewSubwindow();
                }
                break;
            default:
                if (this.getActiveSubwindow() != null) {
                    activeSubwindow.handleKeyEvent(keyEvent);
                    //setSubwindowLevels();
                }
                break;
        }
    }

    public void handleMouseEvent(MouseEvent mouseEvent) {
        if(getActiveSubwindow() != null && ! getActiveSubwindow().isInLabelMode()) {
            if (!dragging) {
                dragging = checkFordragging(mouseEvent);
            }
            if (dragging) {
                switch (mouseEvent.getMouseEventType()) {
                    case RELEASE:
                        dragging = false;
                        activeSubwindow.handleMovement(mouseEvent.getPoint());
                        break;
                    default:
                        break;
                }
            } else {
                Subwindow subwindow = getAppropriateSubwindow(mouseEvent.getPoint());
                if (subwindow != null) {
                    if (!subwindow.equals(getActiveSubwindow())) {
                        changeActiveSubwindow(subwindow);
                    }
                    try {
                        Point2D relativePoint = getActiveSubwindow().getRelativePoint(mouseEvent.getPoint());
                        mouseEvent.setPoint(relativePoint);
                        subwindow.handleMouseEvent(mouseEvent);
                    } catch (DomainException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean checkFordragging(MouseEvent mouseEvent) {
        for (SubWindowLevel subwindow : subwindows) {
            if (subwindow.getSubwindow().frameIsClicked(mouseEvent.getPoint())) {
                changeActiveSubwindow(subwindow.getSubwindow());
                return true;
            }
        }
        return false;
    }

    private void changeActiveSubwindow(Subwindow newActiveSubWindow) {
        this.setActiveSubwindow(newActiveSubWindow);
        this.changeLevelForActiveSubWindow();
    }

    private void changeLevelForActiveSubWindow() {
        if(activeSubwindow != null) {
            for (SubWindowLevel s : subwindows) {
                if (s.getSubwindow().equals(getActiveSubwindow())) {
                    s.setLevel(getCorrectLevel());
                }
            }
        }
    }

    private void createNewSubwindow() {
        Subwindow subwindow = new Subwindow(new Point2D.Double(100, 100), new CloseButton(this), new InteractionMediator());
        int level = getCorrectLevel();
        addSubwindow(subwindow, level);
        this.changeActiveSubwindow(subwindow);
    }

    private void copyActiveSubWindow() {
        if (this.getActiveSubwindow() != null) {
            Subwindow subwindow = new Subwindow(new Point2D.Double(100, 100), new CloseButton(this), activeSubwindow.getCopyOfFacade(), activeSubwindow.getMediator());
            int level = getCorrectLevel();
            addSubwindow(subwindow, level);
            this.changeActiveSubwindow(subwindow);
        }
    }

    private int getCorrectLevel() {
        int level = -1;
        for (SubWindowLevel s : subwindows) {
            if (s.getLevel() > level) {
                level = s.getLevel();
            }
        }
        level++;
        return level;
    }

    private Subwindow getAppropriateSubwindow(Point2D clickedLocation) {
        List<SubWindowLevel> clickedSubwindows = this.getSubwindows()
                .stream()
                .filter(s -> s.getSubwindow().isClicked(clickedLocation))
                .collect(Collectors.toList());
        int level = -1;
        Subwindow highest = null;
        for (SubWindowLevel s : clickedSubwindows) {
            if (s.getLevel() > level) {
                highest = s.getSubwindow();
                level = s.getLevel();
            }
        }
        return highest;
    }

    public class SubWindowLevel implements Comparable<SubWindowLevel> {

        private Subwindow subwindow;
        private int level;

        public SubWindowLevel(Subwindow subwindow, int level) {
            this.setSubwindow(subwindow);
            this.setLevel(level);
        }

        public Subwindow getSubwindow() {
            return subwindow;
        }

        private void setSubwindow(Subwindow subwindow) {
            if (subwindow == null) {
                throw new IllegalArgumentException("Subwindow may not be null");
            }
            this.subwindow = subwindow;
        }

        public int getLevel() {
            return level;
        }

        private void setLevel(int level) {
            if (level < 0) {
                throw new IllegalArgumentException("level can't be negative");
            }
            this.level = level;
        }

        @Override
        public int compareTo(SubWindowLevel o) {
            return - Integer.compare(this.getLevel(), o.getLevel());
        }
    }
}
