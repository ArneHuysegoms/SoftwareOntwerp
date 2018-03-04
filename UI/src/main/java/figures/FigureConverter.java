package figures;

import canvascomponents.diagram.*;
import figures.Drawer.ActorDrawer;
import figures.Drawer.MessageDrawer;
import figures.diagramFigures.Figure;

import java.awt.*;

public class FigureConverter {
    private static FigureConverter instance = null;

    private FigureConverter(){

    }

    public static FigureConverter getInstance(){
        if(instance == null)
            instance = new FigureConverter();
        return instance;
    }


    public void draw(Graphics graphics, Diagram diagram){

        drawParties(graphics, diagram);

        drawMessages(graphics, diagram);

    }

    private void drawParties(Graphics graphics, Diagram diagram){
        for(Party p : diagram.getParties()){
            if(p instanceof Actor) {
                ActorDrawer.getInstance().draw(graphics, p.getCoordinate(), null);
            }
        }
    }

    private void drawMessages(Graphics graphics, Diagram diagram){
        Message m = diagram.getFirstMessage();
        int messageDepth = 0;

        while(m != null){
            //TODO
            if(m instanceof InvocationMessage)
                messageDepth++;
            if(m instanceof ResultMessage)
                messageDepth--;
            MessageDrawer.getInstance().draw(graphics, m.);
            m = m.getNextMessage();
        }
    }
}
