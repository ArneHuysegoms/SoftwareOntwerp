package figures.helperClasses;

import diagram.party.Party;

import java.awt.*;
import java.util.List;

public class Lifeline {
    private Party party;
    private List<ActivationBar> bars;

    public Lifeline(){
        calculateOuterBars();
        calculateNextBars();
    }

    private void calculateOuterBars(){

    }

    private void calculateNextBars() {
        for(ActivationBar a: bars){
            a.calculateNextBars();
        }
    }

    /**
     * Calculate the dashed line's length
     */
    private void calculateLengthOfLine() {

    }

    public void draw(Graphics graphics){

    }
}
