package diagram.party;

import diagram.DiagramElement;
import diagram.label.Label;

import java.io.Serializable;

/**
 * abstract superclass for parties of he diagram
 */
public abstract class Party extends DiagramElement implements Serializable {

    private Label label;

    /**
     * default constructor
     */
    public Party(){

    }

    /**
     * @param label
     *        The label belonging with this actor
     */
    public Party(Label label){
        this.setLabel(label);
    }

    /**
     * @return returns het label of this party
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label the label to edit to
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     */
    public void setLabel(Label label) {
        this.label = label;
    }

/*    *//*
     * method to find the correct location for the label of a Party
     *
     * @return a Point2D indicating the location
     *//*
    public abstract Point2D getCorrectLabelPosition();

    *//*
     * method to get the x location of the lifeline belonging to the party
     *
     * @return returns a double which denotes the x location of the lifeline belonging to the party
     *//*
    public abstract double getXLocationOfLifeline();*/


}
