package handler.useCaseHandlers;

import canvascomponents.CanvasComponent;
import handler.Handler;

public class AddPartyHandler extends Handler {

    public AddPartyHandler(CanvasComponent canvasComponent, UIEvent uiEvent){
        super(canvasComponent, uiEvent);
    }

    @Override
    public boolean HandleEvent(CanvasComponent component, UIEvent uiEvent) {
        return false;
    }
}
