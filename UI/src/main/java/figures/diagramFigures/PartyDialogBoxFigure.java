package figures.diagramFigures;

import figures.basicShapes.Circle;
import figures.basicShapes.FilledCircle;
import window.dialogbox.PartyDialogBox;
import window.elements.RadioButton;
import window.elements.textbox.TextBox;

import java.awt.*;
import java.awt.geom.Point2D;

// TODO dialog box for a party shows
//two text boxes and two radio buttons, for editing the instance name and the class
//name and for choosing between the actor and object form, respectively.
public class PartyDialogBoxFigure extends SubwindowFigure {

    private PartyDialogBox dialogBox;

    /**
     * @param dialogBox
     */
    public PartyDialogBoxFigure(PartyDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        super.draw(graphics, minX, minY, maxX, maxY);
        drawRadioButtons(graphics, minX, minY, maxX, maxY);
        drawTextBoxes(graphics, minX, minY, maxX, maxY);
    }

    private void drawTextBoxes(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        Point2D instPos=dialogBox.getInstanceTextBox().getCoordinate(), clasPos=dialogBox.getClassTextBox().getCoordinate();

        new figures.basicShapes.Rectangle(instPos, TextBox.HEIGHT, TextBox.WIDTH).draw(graphics, minX, minY, maxX, maxY);
        graphics.drawString(PartyDialogBox.INSTANCE_DESCRIPTION, (int)instPos.getX(), (int)instPos.getY());

        new  figures.basicShapes.Rectangle(clasPos, TextBox.HEIGHT, TextBox.WIDTH).draw(graphics, minX, minY, maxX, maxY);
        graphics.drawString(PartyDialogBox.CLASS_DESCRIPTION, (int)clasPos.getX(), (int)clasPos.getY());
    }

    public void drawRadioButtons(Graphics graphics, int minX, int minY, int maxX, int maxY){
        Point2D acc=dialogBox.getToActor().getCoordinate(), obj=dialogBox.getToObject().getCoordinate();
        //TODO class RadioButtonFigue? want coordinate is MISSCHIEN nie midden van radio button
        //TODO miss Class maken voor radiobuttion, public RadioButton(Point2D, descripton)
        new Circle(acc, RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
        graphics.drawString(PartyDialogBox.TOACTOR_DESCRIPTION, (int)acc.getX(), (int)acc.getY());

        new Circle(obj,RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);;
        graphics.drawString(PartyDialogBox.TOOBJECT_DESPCRIPTION, (int)obj.getX(), (int)obj.getY());

        new FilledCircle(dialogBox.getSelected().getCoordinate(), RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
    }
}
