package uievents;


public class KeyEventFactory {
    /**
     *
     * @param id of the KeyEvent
     * @param keyCode of the KeyEvent
     * @param keyChar of the KeyEvent
     * @return new KeyEvent with KeyEventType
     *          | TAB if KEY_PRESSED and keyCode == 9
     *          | DEL if KEY_PRESSED and keyCode == 46
     *          | COLON if KEY_TYPED and keyCode == 186
     *          | CHAR if KEY_TYPED
     */
    public KeyEvent createKeyEvent(int id, int keyCode, char keyChar){
        if(id == java.awt.event.KeyEvent.KEY_PRESSED && keyCode == 9){
            return new KeyEvent(KeyEventType.TAB);
            //* 9 is keycode for tab
        } else if(id == java.awt.event.KeyEvent.KEY_PRESSED && keyCode == 46){
            return new KeyEvent(KeyEventType.DEL);
            //* 46 is keycode for delete
        } else if(id == java.awt.event.KeyEvent.KEY_TYPED && keyCode == 186){
            return new KeyEvent(KeyEventType.COLON);
            //* 186 is keycode for colon
        } else if(id == java.awt.event.KeyEvent.KEY_TYPED){
            return new KeyEvent(KeyEventType.CHAR, keyChar);
        }
        return null;
    }
}
