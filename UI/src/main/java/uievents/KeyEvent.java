package uievents;

/**
 * @author Arne
 */
public class KeyEvent {
    private KeyEventType keyEventType;
    private char keyChar;

    public KeyEvent(KeyEventType keyEventType) {
        this.setKeyEventType(keyEventType);
    }

    /**
     *
     * @param keyEventType keyEvent Type
     * @param keyChar key character
     */
    public KeyEvent(KeyEventType keyEventType, char keyChar){
        this.setKeyEventType(keyEventType);
        this.setKeyChar(keyChar);
    }

    /**
     *
     * @return keyEventType of KeyEvent
     */
    public KeyEventType getKeyEventType() {
        return keyEventType;
    }

    /**
     *
     * @return character of KeyEvent
     */
    public char getKeyChar() {
        return keyChar;
    }

    /**
     *
     * @param keyEventType
     */
    private void setKeyEventType(KeyEventType keyEventType){
        this.keyEventType = keyEventType;
    }

    /**
     *
     * @param keyChar
     */
    private void setKeyChar(char keyChar){
        this.keyChar = keyChar;
    }

    /**
     *
     * @param keyEvent keyEvent
     * @return boolean
     *          | if (this.getKeyEventType().equals(keyEvent.getKeyEventType()) and this.getKeyChar() == keyEvent.getKeyChar())
     *          |   return true
     *          | else
     *          |   return false
     */
    public boolean equals(KeyEvent keyEvent) {
        if (this.getKeyEventType().equals(keyEvent.getKeyEventType()) && this.getKeyChar() == keyEvent.getKeyChar()) {
            return true;
        }
            return false;
    }
}
