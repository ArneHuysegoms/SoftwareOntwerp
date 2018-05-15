package window.dialogbox;

import command.ChangeToActorCommand;
import command.ChangeToObjectCommand;
import diagram.party.Party;
import exception.UIException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.RadioButton;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;

public class PartyDialogBox extends DialogBox {

    public static final int WIDTH = 150;
    public static final int HEIGHT = 200;

    public static final String TOOBJECT_DESPCRIPTION = "Object";
    public static final String TOACTOR_DESCRIPTION = "Actor";

    public static final String INSTANCE_DESCRIPTION = "Instance";
    public static final String CLASS_DESCRIPTION = "Class";

    private RadioButton toActor;
    private RadioButton toObject;

    private TextBox instanceTextBox;
    private TextBox classTextBox;

    private Party party;

    //TODO give party
    public PartyDialogBox(Point2D pos, Party party, DiagramSubwindow subwindow) throws UIException {
        super(pos);
        this.setParty(party);
        toActor = new RadioButton(new ChangeToActorCommand(subwindow, party), new Point2D.Double(10, 20), TOACTOR_DESCRIPTION);
        toObject = new RadioButton(new ChangeToObjectCommand(subwindow, party), new Point2D.Double(85, 20), TOOBJECT_DESPCRIPTION);
        instanceTextBox = new InstanceTextBox(new Point2D.Double(10, 50), INSTANCE_DESCRIPTION);
        classTextBox = new ClassTextBox(new Point2D.Double(10, 75), CLASS_DESCRIPTION);
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public void handleMouseEvent(MouseEvent mouseEvent) {

    }

    @Override
    public void handleKeyEvent(KeyEvent keyEvent) {

    }
}
