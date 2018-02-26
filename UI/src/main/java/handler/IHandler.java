package handler;

import canvascomponents.CanvasComponent;

public interface IHandler {

    boolean HandleEvent(CanvasComponent component, UIEvent uiEvent);
}
