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
    public KeyEvent createKeyEvent(int id, int keyCode, char keyChar) {

        if (id == java.awt.event.KeyEvent.KEY_PRESSED) {
            if (keyCode == 9) {
                return new KeyEvent(KeyEventType.TAB);
            } else if (keyCode == 46 || keyCode == 127) {
                return new KeyEvent(KeyEventType.DEL);
            } else if (keyCode == 8) {
                return new KeyEvent(KeyEventType.BACKSPACE);
            }
        } else if (id == java.awt.event.KeyEvent.KEY_TYPED) {
            if ((keyChar >= 'A' && keyChar <= 'Z') || (keyChar >= 'a' && keyChar <= 'z') || keyChar == ':') {
                return new KeyEvent(KeyEventType.CHAR, keyChar);
            } else {
                return new KeyEvent(KeyEventType.CHAR);
            }

        }
        return new KeyEvent(KeyEventType.IRRELEVANT);
    }
}
