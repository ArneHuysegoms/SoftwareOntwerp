package handler;

import canvascomponents.CanvasComponent;

public class SwitchDigramHandler extends Handler {

    public SwitchDigramHandler(CanvasComponent component, UIEvent uiEvent){
        super(component, uiEvent);
    }

    @Override
    public boolean HandleEvent(CanvasComponent component, UIEvent uiEvent) {
        return false;
    }
}
