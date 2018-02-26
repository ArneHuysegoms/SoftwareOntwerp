package handler;

import canvascomponents.CanvasComponent;

public abstract class Handler implements IHandler{

    private UIEvent uiEvent;
    private CanvasComponent canvasComponent;

    public Handler(CanvasComponent canvasComponent, UIEvent uiEvent){
        this.setCanvasComponent(canvasComponent);
        this.setUiEvent(uiEvent);
    }

    public abstract boolean HandleEvent(CanvasComponent component, UIEvent uiEvent);

    public UIEvent getUiEvent() {
        return uiEvent;
    }

    private void setUiEvent(UIEvent uiEvent) {
        this.uiEvent = uiEvent;
    }

    public CanvasComponent getCanvasComponent() {
        return canvasComponent;
    }

    private void setCanvasComponent(CanvasComponent canvasComponent) {
        this.canvasComponent = canvasComponent;
    }
}
