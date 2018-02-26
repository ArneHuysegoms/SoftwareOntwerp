package handler.useCaseHandlers;

import canvascomponents.CanvasComponent;
import handler.Handler;

public class DeleteElementHandler extends Handler {

    public DeleteElementHandler(CanvasComponent canvasComponent, UIEvent uiEvent){
        super(canvasComponent, uiEvent);
    }

    @Override
    public boolean HandleEvent(CanvasComponent component, UIEvent uiEvent) {
        return false;
    }

}
