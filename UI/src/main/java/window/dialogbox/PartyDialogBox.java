package window.dialogbox;

import diagram.party.Party;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.elements.RadioButton;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;

public class PartyDialogBox extends DialogBox {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 200;

    public static final String TOOBJECT_DESPCRIPTION = "Object";
    public static final String TOPARTY_DESCRIPTION = "Party";

    private RadioButton toActor;
    private RadioButton toParty;

    private TextBox InstanceTextBox;
    private TextBox ClassTextBox;

    private Party party;

    //TODO
    public PartyDialogBox(Point2D pos){
        super(pos);
    }

    @Override
    public void handleMouseEvent(MouseEvent mouseEvent) {

    }

    @Override
    public void handleKeyEvent(KeyEvent keyEvent) {

    }
}
