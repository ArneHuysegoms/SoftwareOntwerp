package handler.useCaseHandlers;

import canvascomponents.CanvasComponent;
import handler.Handler;

public class EditLabelHandler extends Handler {

    public EditLabelHandler(CanvasComponent canvasComponent, UIEvent uiEvent){
        super(canvasComponent, uiEvent);
    }

    @Override
    public boolean HandleEvent(CanvasComponent component, UIEvent uiEvent) {
        return false;
    }
}
