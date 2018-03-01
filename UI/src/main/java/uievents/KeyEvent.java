package uievents;

public class KeyEvent{
    private KeyEventType keyEventType;

    public KeyEvent(KeyEventType keyEventType){
        this.setKeyEventType(keyEventType);
    }

    public KeyEventType getKeyEventType() {
        return keyEventType;
    }

    private void setKeyEventType(KeyEventType keyEventType){
        this.keyEventType = keyEventType;
    }
}
