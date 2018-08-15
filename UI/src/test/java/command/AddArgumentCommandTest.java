package command;

import command.InvocationCommand.AddArgumentCommand;
import command.changeType.PartyCommand.ChangeToObjectCommand;
import diagram.label.InvocationMessageLabel;
import diagram.label.Label;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Party;
import exception.UIException;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import window.diagram.DiagramSubwindow;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddArgumentCommandTest {

    private AddArgumentCommand addArgumentCommand;
    private DiagramSubwindow subwindow;
    private InvocationMessageLabel label;
    private ArgumentTextBox textBox;
    private ListBox listBox;


    @Before
    public void setUp(){
        try {
            listBox = new ListBox(new Point2D.Double(110, 1800),"listBox");
            textBox = new ArgumentTextBox(new Point2D.Double(110, 110),"listBox");
            label = new InvocationMessageLabel("jos",new ArrayList<String>());
            subwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
            addArgumentCommand = new AddArgumentCommand(listBox, textBox, label,subwindow);
        }catch(DomainException e){
            System.out.println("domainexception AddArgumentCommandTest setup");
            e.printStackTrace();
        } catch (UIException e) {
            System.out.println("domainexception AddArgumentCommandTest setup2");
            e.printStackTrace();
        }
    }

    @Test
    public void test_performAction(){
        assertEquals(0,label.getArguments().size());
        textBox.setContents("hello");
        addArgumentCommand.performAction();
        assertEquals(1,label.getArguments().size());
    }
}
