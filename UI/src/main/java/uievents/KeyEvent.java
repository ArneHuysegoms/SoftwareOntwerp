package uievents;

public class KeyEvent {
    private KeyEventType keyEventType;
    private char keyChar;

    public KeyEvent(KeyEventType keyEventType) {
        this.setKeyEventType(keyEventType);
    }

    public KeyEvent(KeyEventType keyEventType, char keyChar){
        this.setKeyEventType(keyEventType);
        this.setKeyChar(keyChar);
    }

    public KeyEventType getKeyEventType() {
        return keyEventType;
    }
    public char getKeyChar() {
        return keyChar;
    }
    private void setKeyEventType(KeyEventType keyEventType){
        this.keyEventType = keyEventType;
    }
    private void setKeyChar(char keyChar){
        this.keyChar = keyChar;
    }

    public boolean equals(KeyEvent k) {
        if (this.getKeyEventType().equals(k.getKeyEventType()) && this.getKeyChar() == k.getKeyChar()) {
            return true;
        }
            return false;
    }
}
