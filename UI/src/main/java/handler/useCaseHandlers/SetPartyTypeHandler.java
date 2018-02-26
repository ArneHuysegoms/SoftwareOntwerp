package handler.useCaseHandlers;

import canvascomponents.CanvasComponent;
import handler.Handler;

public class SetPartyTypeHandler extends Handler {

    public SetPartyTypeHandler(CanvasComponent canvasComponent, UIEvent uiEvent){
        super(canvasComponent, uiEvent);
    }

    @Override
    public boolean HandleEvent(CanvasComponent component, UIEvent uiEvent) {
        return false;
    }


}
