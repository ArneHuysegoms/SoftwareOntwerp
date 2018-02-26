package handler;

import canvascomponents.CanvasComponent;

public class MovePartyHandler extends Handler {

    public MovePartyHandler(CanvasComponent canvasComponent, UIEvent uiEvent){
        super(canvasComponent, uiEvent);
    }

    @Override
    public boolean HandleEvent(CanvasComponent component, UIEvent uiEvent) {
        return false;
    }
}
