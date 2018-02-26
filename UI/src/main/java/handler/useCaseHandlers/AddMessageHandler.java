package handler.useCaseHandlers;

import canvascomponents.CanvasComponent;
import handler.Handler;

public class AddMessageHandler extends Handler {

    public AddMessageHandler(CanvasComponent canvasComponent, UIEvent uiEvent){
        super(canvasComponent, uiEvent);
    }


    @Override
    public boolean HandleEvent(CanvasComponent component, UIEvent uiEvent) {
        return false;
    }
}
