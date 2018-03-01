package uievents;


public class KeyEventFactory {

    public KeyEvent createKeyEvent(int id, int keyCode, char keyChar){
       /* if(id == java.awt.event.KeyEvent.KEY_PRESSED)){
            return new KeyEvent(KeyEventType.TAB);
            //* Momenteel enkel tab nodig? keyChar meegeven aan KeyEvent?
        } else if(id == java.awt.event.KeyEvent.KEY_TYPED){
            return new KeyEvent(KeyEventType.COLON);
        } else if(id == java.awt.event.KeyEvent.KEY_TYPED){
            return new KeyEvent(KeyEventType.CHAR);
        }*/
        return null;
    }
}
