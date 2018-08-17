package command;

import command.InvocationCommand.DeleteArgumentCommand;
import diagram.label.InvocationMessageLabel;
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

public class DeleteArgumentCommandTest {

    private DeleteArgumentCommand deleteArgumentCommand;
    private DiagramSubwindow subwindow;
    private InvocationMessageLabel label;
    private ArgumentTextBox textBox;
    private ListBox listBox;


    @Before
    public void setUp(){
        try {
            listBox = new ListBox(new Point2D.Double(110, 1800),"listBox");
            label = new InvocationMessageLabel("jos",new ArrayList<String>());
            subwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
            deleteArgumentCommand = new DeleteArgumentCommand(listBox, label,subwindow);
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
        listBox.addArgument("hello");
        label.addArgument("hello");
        assertEquals(1,label.getArguments().size());
        deleteArgumentCommand.performAction();
        assertEquals(0,label.getArguments().size());
    }
}
