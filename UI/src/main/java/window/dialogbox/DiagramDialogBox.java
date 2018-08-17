package window.dialogbox;

import action.Action;
import action.EmptyAction;
import action.UpdateListAction;
import command.changeType.DiagramCommand.ChangeToCommunicationCommand;
import command.changeType.DiagramCommand.ChangeToSequenceCommand;
import exception.UIException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.radiobutton.DiagramRadioButton;
import window.elements.radiobutton.RadioButton;
import window.elements.radiobutton.ToCommunicationRadioButton;
import window.elements.radiobutton.ToSequenceRadioButton;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * construct a new dialogbox for diagrams
 */
public class DiagramDialogBox extends DialogBox {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 100;

    public static final String TOCOMMUNICATIONDIAGRAM_DESPCRIPTION = "Communication";
    public static final String TOSEQUENCEDIAGRAM_DESCRIPTION = "Sequence";

    private DiagramSubwindow subwindow;

    public static ArrayList<DialogboxElement> DIAGRAMBOXLIST;

    static {
        try{
            DIAGRAMBOXLIST = new ArrayList<DialogboxElement>(Arrays.asList(new ToCommunicationRadioButton(new ChangeToCommunicationCommand(null), new Point2D.Double(20, 30), TOCOMMUNICATIONDIAGRAM_DESPCRIPTION),
                    new ToSequenceRadioButton(new ChangeToSequenceCommand(null), new Point2D.Double(20, 60), TOSEQUENCEDIAGRAM_DESCRIPTION)));
        }catch (UIException e){
            e.printStackTrace();
        }
    }
    @Override
    public List<DialogboxElement> getStaticList(){
        return DIAGRAMBOXLIST;
    }

    /**
     * creates a new diagramdialogbox
     *
     * @param position  the new position
     * @param subwindow the subwindow this dialogbox is for
     * @throws UIException if the position is null
     */
    public DiagramDialogBox(Point2D position, DiagramSubwindow subwindow) throws UIException {
        super(position);
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);

        this.elementList = new ArrayList<>();
        this.setDiagramSubwindow(subwindow);
        updateList();
        this.selectedindex = 0;
        selected = this.elementList.get(getSelectedindex());
    }

    /**
     * syncs static list with private list
     * sets correct selectedelement
     */
    @Override
    public void updateList(){
        this.elementList = new ArrayList<>();
        for (DialogboxElement d : DIAGRAMBOXLIST) {
            DialogboxElement clone = d.clone();
            clone.update(subwindow);
            elementList.add(clone);
        }

        if(elementList.size() == 0){
            selected = null;
        }
        else if(selectedindex > elementList.size()-1){
            selectedindex = 0;
            selected = this.elementList.get(selectedindex);
        }else{

            selected = this.elementList.get(selectedindex);
        }
    }

    public int getSelectedindex() {
        return selectedindex;
    }

    /**
     * @return the diagramsubwindow for this dialogbox
     */
    public DiagramSubwindow getDiagramSubwindow() {
        return subwindow;
    }

    /**
     * sets the subwindow to the given subwindow
     *
     * @param subwindow the new subwindow
     */
    public void setDiagramSubwindow(DiagramSubwindow subwindow) {
        this.subwindow = subwindow;
    }

    /**
     * @return the default width for this dialogboxtype
     */
    public static int getWIDTH() {
        return WIDTH;
    }

    /**
     * @return the default height for this dialogboxtype
     */
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * handles the backspace key
     * @return correct action
     */
    @Override
    protected Action handleBackSpace() {
        if(designerMode) {
            DialogboxElement d = getStaticList().get(selectedindex);
            d.deleteCharFromDescription();
            if (!d.isValidDescription()) {
                setInvalidDescriptionMode(true);
            }
            return new UpdateListAction();
        }
        return new EmptyAction();
    }

    /**
     * handles a character key
     * @param keyEvent the keyEvent with the char
     * @return correct action
     */
    @Override
    public Action handleChar(KeyEvent keyEvent) {
        if(designerMode && selected != null){
            DialogboxElement d = getStaticList().get(selectedindex);
            d.addCharToDescription(keyEvent.getKeyChar());
            if(d.isValidDescription()){
                setInvalidDescriptionMode(false);
            }
            return new UpdateListAction();
        }
        return new EmptyAction();
    }

    /**
     * handles an action
     *
     * @param action the action to handle
     */
    @Override
    public void handleAction(Action action) {
        if(action instanceof UpdateListAction){
            updateList();
        }
    }


}
