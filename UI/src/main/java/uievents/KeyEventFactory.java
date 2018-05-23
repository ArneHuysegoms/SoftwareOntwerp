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
     *          | BACKSPACE if KEY_PRESSED and keyCode == 8
     *          | CTRLN if KEY_PRESSED and keyCode == 68 and ctrlPressed == true
     *          | CTRLD if KEY_PRESSED and keyCode == 78 and ctrlPressed == true
     *          | CHAR if KEY_TYPED
     *          | else IRRELEVANT
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
            } else if (keyCode == 32) {
                return new KeyEvent(KeyEventType.SPACE);
            } else if (keyCode == 37) {
                return new KeyEvent(KeyEventType.ARROWKEYLEFT);
            } else if (keyCode == 38) {
                return new KeyEvent(KeyEventType.ARROWKEYUP);
            } else if (keyCode == 39) {
                return new KeyEvent(KeyEventType.ARROWKEYRIGHT);
            } else if (keyCode == 40) {
                return new KeyEvent(KeyEventType.ARROWKEYDOWN);
            }  else if (keyCode == 17){
                setCtrlPressed(true);
                System.out.println("Set TRUE");
            } else if (keyCode != 68 && keyCode != 78 && keyCode != 10) {
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
            } else if (keyCode == 10){
                if(ctrlPressed == true){
                    System.out.println("CTRLENTER EVENT CREATED");
                    setCtrlPressed(false);
                    return new KeyEvent(KeyEventType.CTRLENTER);
                }
            }
        } else if (id == java.awt.event.KeyEvent.KEY_TYPED && keyChar != '\b' && keyChar != '\t') {
            if ((keyChar >= 'A' && keyChar <= 'Z') || (keyChar >= 'a' && keyChar <= 'z') || keyChar == ':' || keyChar == ' ' || keyChar == ')' || keyChar == '(' || keyChar == ',') {
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
