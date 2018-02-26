package handler;

import canvascomponents.CanvasComponent;

public class AddPartyHandler extends Handler {

    public AddPartyHandler(CanvasComponent canvasComponent, UIEvent uiEvent){
        super(canvasComponent, uiEvent);
    }

    @Override
    public boolean HandleEvent(CanvasComponent component, UIEvent uiEvent) {
        return false;
    }
}
