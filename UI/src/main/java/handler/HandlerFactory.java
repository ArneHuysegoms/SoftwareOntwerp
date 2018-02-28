package handler;

import canvascomponents.CanvasComponent;
import canvascomponents.diagram.Diagram;

public class HandlerFactory {

    private HandlerFactory(){

    }

    public static IHandler CreateHandler(CanvasComponent canvasComponent, UIEvent uiEvent){
        return null;
    }

    private static boolean isDiagram(CanvasComponent component){
        return component instanceof Diagram;
    }


}
