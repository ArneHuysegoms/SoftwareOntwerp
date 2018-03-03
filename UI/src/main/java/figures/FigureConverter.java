package figures;

import canvascomponents.diagram.Diagram;
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

    }
}
