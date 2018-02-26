package controllers;

import canvascomponents.CanvasComponent;

public interface IHandler {

    boolean HandleEvent(CanvasComponent component, UIEevent uiEvent);
}
