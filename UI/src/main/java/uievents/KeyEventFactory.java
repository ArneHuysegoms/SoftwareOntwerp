package uievents;


public class KeyEventFactory {
    /**
     *
     * @param id of the KeyEvent
     * @param keyCode of the KeyEvent
     * @param keyChar of the KeyEvent
     * @return new KeyEvent with KeyEventType
     *          | TAB if KEY_PRESSED and keyCode == 9
     *          | DEL if KEY_PRESSED and keyCode == 46 or 127
     *          | COLON if KEY_TYPED and keyCode == 186
     *          | CHAR if KEY_TYPED
     *          | BACKSPACE if KEY_PRESSED and keyCode == 8
     */
    private boolean ctrlPressed;
    public KeyEvent createKeyEvent(int id, int keyCode, char keyChar) {


        if (id == java.awt.event.KeyEvent.KEY_PRESSED) {
            if (keyCode == 9) {
                return new KeyEvent(KeyEventType.TAB);
            } else if (keyCode == 46 || keyCode == 127) {
                return new KeyEvent(KeyEventType.DEL);
            } else if (keyCode == 8) {
                return new KeyEvent(KeyEventType.BACKSPACE);
            } else if (keyCode == 17){
                setCtrlPressed(true);
                System.out.println("Set TRUE");
            } else if (keyCode != 68 && keyCode != 78) {
                setCtrlPressed(false);
            } else if (keyCode == 68) {
                if (ctrlPressed == true) {
                    System.out.println("CTRLD EVENT CREATED");
                    setCtrlPressed(false);
                    return new KeyEvent(KeyEventType.CTRLD);
                }
            } else if (keyCode == 78){
                if(ctrlPressed == true){
                    System.out.println("CTRLN EVENT CREATED");
                    setCtrlPressed(false);
                    return new KeyEvent(KeyEventType.CTRLN);
                }
            }
        } else if (id == java.awt.event.KeyEvent.KEY_TYPED && keyChar != '\b') {
            if ((keyChar >= 'A' && keyChar <= 'Z') || (keyChar >= 'a' && keyChar <= 'z') || keyChar == ':' || keyChar == ' ') {
                return new KeyEvent(KeyEventType.CHAR, keyChar);
            } else {
                return new KeyEvent(KeyEventType.CHAR);
            }

        }
        return new KeyEvent(KeyEventType.IRRELEVANT);
    }

    public void setCtrlPressed(boolean b){
        this.ctrlPressed = b;
    }
}
