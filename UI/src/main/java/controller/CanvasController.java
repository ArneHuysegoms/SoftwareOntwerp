package controller;

import command.CloseSubwindowCommand;
import exceptions.DomainException;
import mediator.InteractionMediator;
import subwindow.Button;
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
    private boolean dragging = false;

    /**
     * constructs a new empty canvascontroller
     */
    public CanvasController() {
        this.setSubwindows(new ArrayList<>());
        this.activeSubwindow = null;
    }

    /**
     * @return the list containing all subwindows
     */
    public List<SubWindowLevel> getSubwindows() {
        return subwindows;
    }

    /**
     * sets the subwindowlevels  to the given subwindowlevels
     * @param subwindows the new subwindows
     * @throws IllegalArgumentException if the given subwindows is null
     */
    private void setSubwindows(List<SubWindowLevel> subwindows) throws IllegalArgumentException {
        if (subwindows == null) {
            throw new IllegalArgumentException("Subwindows may not be null");
        }
        this.subwindows = subwindows;
    }

    /**
     *
     * @return active subwindow
     */
    public Subwindow getActiveSubwindow() {
        return activeSubwindow;
    }

    /**
     *
     * @param activeSubwindow the new active subwindow
     */
    private void setActiveSubwindow(Subwindow activeSubwindow){
        this.activeSubwindow = activeSubwindow;
    }

    /**
     * removes the subwindow from the list and sets the subwindow with the highest level as active subwindow
     * @param subwindow the subwindow to remove
     */
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

    /**
     * sets the subwindow with the highest level as active subwindow
     */
    public void setNewActiveSubWindow() {
        SubWindowLevel s = findHighestSubwindowLevel();
        if (s != null) {
            changeActiveSubwindow(s.getSubwindow());
        } else {
            changeActiveSubwindow(null);
        }
    }

    /**
     * returns the subwindow with the highest level
     * @return subwindowlevel
     */
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

    /**
     * adds a subwindow with a given level to the list of subwindows
     * @param subwindow the subindow to add
     * @param level, level of the subwindow
     */
    public void addSubwindow(Subwindow subwindow, int level) {
        if (subwindow == null) {
            throw new IllegalArgumentException("can't add null subwindow");
        }
        SubWindowLevel subWindowLevel = new SubWindowLevel(subwindow, level);
        this.getSubwindows().add(subWindowLevel);
    }

    /**
     * handles a key event
     * @param keyEvent the keyEvent to handle
     */
    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException {
        switch (keyEvent.getKeyEventType()) {
            case CTRLD:
                if (getActiveSubwindow() != null) {
                    copyActiveSubWindow();
                }
                break;
            case CTRLN:
                createNewSubwindow();
                break;
            default:
                if (this.getActiveSubwindow() != null) {
                    activeSubwindow.handleKeyEvent(keyEvent);
                }
                break;
        }
    }

    /**
     * handles MouseEvent that affects the subwindow
     *          | if dragged == false, check if subwindow currently is being dragged (update the dragging boolean)
     *          | if dragged == true, check the mouseEventType
     *                          | if RELEASE, pass the mouseEvent to activeSubwindow and set dragging == false
     *                          | if LEFTCLICK, set dragging == false
     *          | otherwise the appropriate subwindow that was clicked
     *                          | if the subwindow exists
     *                              | if the subwindow is not the active one, set it as the active one
     *                              get the coordinates of the click on the subwindow, relative to the subwindow
     *                              pass the mouseEvent to the appropriate subwindow
     * @param mouseEvent the mouseEvent to handle
     */
    public void handleMouseEvent(MouseEvent mouseEvent) {
        if (!dragging) {
            dragging = checkFordragging(mouseEvent);
        }
        if (dragging) {
            switch (mouseEvent.getMouseEventType()) {
                case RELEASE:
                    activeSubwindow.handleMovement(mouseEvent.getPoint());
                    dragging = false;
                    break;
                case LEFTCLICK:
                    dragging = false;
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
                Point2D relativePoint = getActiveSubwindow().getRelativePoint(mouseEvent.getPoint());
                mouseEvent.setPoint(relativePoint);
                subwindow.handleMouseEvent(mouseEvent);
            }
        }
    }

    /**
     * checks if  the mouseEvent results in a drag
     *
     * @param mouseEvent the mouseEvent to check for
     * @return if the active subwindow exists
     *              | if the active subwindow is being dragged
     *                  return false
     *              | if the active subwindow's frame has been clicked
     *                  return true
     *              | if the active subwindow is not in label mode
     *                  if a subwindow has been clicked
     *                  return true
     *         else return false
     */
    private boolean checkFordragging(MouseEvent mouseEvent) {
        if (activeSubwindow != null) {
            if (getActiveSubwindow().isDragging()) {
                return false;
            }
            if (activeSubwindow.frameIsClicked(mouseEvent.getPoint())) {
                return true;
            }
            if (!getActiveSubwindow().isInLabelMode()) {
                for (SubWindowLevel subwindow : subwindows) {
                    if (subwindow.getSubwindow().frameIsClicked(mouseEvent.getPoint())) {
                        changeActiveSubwindow(subwindow.getSubwindow());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * sets the active subwindow to newActiveSubWindow and changes the level to the highest
     *
     * @param newActiveSubWindow the new actie subwindow
     *
     */
    private void changeActiveSubwindow(Subwindow newActiveSubWindow) {
        this.setActiveSubwindow(newActiveSubWindow);
        this.changeLevelForActiveSubWindow();
    }

    /**
     * changes the level for the active subwindow
     */
    private void changeLevelForActiveSubWindow() {
        if (activeSubwindow != null) {
            for (SubWindowLevel s : subwindows) {
                if (s.getSubwindow().equals(getActiveSubwindow())) {
                    s.setLevel(getCorrectLevel());
                }
            }
        }
    }

    /**
     * creates a new subwindow with the correct level, adds it to the list of subwindows and sets it as active
     */
    private void createNewSubwindow() {
        Subwindow subwindow = new Subwindow(new Point2D.Double(100, 100), new InteractionMediator());
        Button button = new Button(new CloseSubwindowCommand(this, subwindow));
        subwindow.getFrame().setButton(button);
        int level = getCorrectLevel();
        addSubwindow(subwindow, level);
        this.changeActiveSubwindow(subwindow);
    }

    /**
     * copies the active subwindow, sets the correct level, adds it to the list of subwindows and sets it active
     */
    private void copyActiveSubWindow() {
        if (this.getActiveSubwindow() != null) {
            Subwindow subwindow = new Subwindow(new Point2D.Double(100, 100), activeSubwindow.getCopyOfFacade(), activeSubwindow.getMediator());
            Button button = new Button(new CloseSubwindowCommand(this, subwindow));
            subwindow.getFrame().setButton(button);
            int level = getCorrectLevel();
            addSubwindow(subwindow, level);
            this.changeActiveSubwindow(subwindow);
        }
    }

    /**
     *
     * @return the highest level in the list of subwindows
     */
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

    /**
     * returns the subwindow with the highest level
     * @param clickedLocation the location of the click
     * @return subwindow if a subwindow is clicked, null otherwise
     */
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

    /**
     * class SubWindowLevel
     *
     * Contains a subwindow and its level in the canvas
     */
    public class SubWindowLevel implements Comparable<SubWindowLevel> {

        private Subwindow subwindow;
        private int level;

        /**
         * constructor for SubWindowLevel
         * @param subwindow the subwindow
         * @param level the level of this subwindow in the canvas
         */
        public SubWindowLevel(Subwindow subwindow, int level) {
            this.setSubwindow(subwindow);
            this.setLevel(level);
        }

        /**
         *
         * @return subwindow
         */
        public Subwindow getSubwindow() {
            return subwindow;
        }

        /**
         * sets the subwindow to param subwindow
         * @param subwindow the new  subwindow
         */
        private void setSubwindow(Subwindow subwindow) {
            if (subwindow == null) {
                throw new IllegalArgumentException("Subwindow may not be null");
            }
            this.subwindow = subwindow;
        }

        /**
         *
         * @return level
         */
        public int getLevel() {
            return level;
        }

        /**
         * sets the level to param level
         * @param level the new level
         */
        private void setLevel(int level) {
            if (level < 0) {
                throw new IllegalArgumentException("level can't be negative");
            }
            this.level = level;
        }

        /**
         * compares SubWindowLevel by level
         * @param o other subwindow to compare to
         * @return positive if higher, 0 if equal, negative if smaller
         */
        @Override
        public int compareTo(SubWindowLevel o) {
            return -Integer.compare(this.getLevel(), o.getLevel());
        }
    }
}
