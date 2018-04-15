package figures;

import diagram.Diagram;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Party;
import figures.Drawer.BoxDrawer;
import figures.Drawer.Drawer;
import figures.Drawer.LabelDrawer;
import figures.Drawer.SelectionBoxDrawer;
import repo.diagram.DiagramRepo;
import repo.label.LabelRepo;
import repo.message.MessageRepo;
import repo.party.PartyRepo;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

public abstract class Converter {
    protected Drawer boxDrawingStrategy,
            labelDrawingStrategy,
            selectionBoxDrawingStrategy;

    public Converter(){
        boxDrawingStrategy = new BoxDrawer();
        selectionBoxDrawingStrategy = new SelectionBoxDrawer();
        labelDrawingStrategy = new LabelDrawer();
    }

    public abstract void draw(Graphics graphics, DiagramRepo activeRepo, Diagram diagram);

    protected void drawParties(Graphics graphics, PartyRepo partyRepo, Drawer actorDrawer, Drawer objectDrawer) {
        Map<Party, Point2D> partyMap = partyRepo.getMap();

        for (Map.Entry<Party, Point2D> entry : partyMap.entrySet()) {
            Point2D start = entry.getValue();
            Point2D end;

            if (entry.getKey() instanceof Actor) {
                end = new Point2D.Double(start.getX() + PartyRepo.ACTORWIDTH, start.getY() + PartyRepo.ACTORHEIGHT);
                actorDrawer.draw(graphics, start, end, "");
            } else {
                end = new Point2D.Double(start.getX() + PartyRepo.OBJECTWIDTH, start.getY() + PartyRepo.OBJECTHEIGHT);
                objectDrawer.draw(graphics, start, end, "");
            }
        }
    }

    protected void drawLabels(Graphics graphics, LabelRepo messageRepo) {
        Map<diagram.label.Label, Point2D> labelMap = messageRepo.getMap();

        for (Map.Entry<Label, Point2D> entry : labelMap.entrySet()) {
            Point2D start = entry.getValue();
            Point2D end;

            labelDrawingStrategy.draw(graphics, start, null, entry.getKey().getLabel());
        }
    }

     protected abstract void drawMessages(Graphics graphics, MessageRepo messageRepo, Map<Party, Point2D> map, Message firstMessage);

    //TODO drawSelectionBox
    /**
     * method that uses the selection box drawer to draw a box around the currently selected selectable parts of the diagram
     *
     * @param graphics object used to draw on the program's window
     * @param diagram  the diagram object to be drawn on the canvas
     */
    /*
    private void drawSelectionBox(Graphics graphics, Diagram diagram) {
        Clickable c = diagram.getSelectedElement();

        if (diagram.selectedElementIsActor()) {
            Actor a = (Actor) c;
            Point2D start = new Point2D.Double(a.getCoordinate().getX() - (Actor.WIDTH / 2), a.getCoordinate().getY()),
                    end = new Point2D.Double(a.getCoordinate().getX() + (Actor.WIDTH / 2), a.getCoordinate().getY() + Actor.WIDTH);
            selectionBoxDrawingStrategy.draw(graphics, start, end, "");
        } else if (diagram.selectedElementIsLabel()) {
            Label l = (Label) c;
            Point2D start = l.getCoordinate();
            selectionBoxDrawingStrategy.draw(graphics, start, new Point2D.Double(start.getX() + Label.width, start.getY() + Label.height), "");
        } else if (diagram.selectedElementIsObject()) {
            Object object = (Object) c;
            int selectionBoxSize = 5;
            Point2D start = new Point2D.Double(object.getCoordinate().getX() - selectionBoxSize, object.getCoordinate().getY() - selectionBoxSize);
            Point2D end = new Point2D.Double(object.getCoordinate().getX() + Object.WIDTH + selectionBoxSize, object.getCoordinate().getY() + Object.HEIGHT + selectionBoxSize);
            selectionBoxDrawingStrategy.draw(graphics, start, end, "");
        } else if (diagram.selectedElementIsMessage()) {
            Message m = (Message) c;
            Point2D start = new Point2D.Double(m.getSender().getXLocationOfLifeline(), m.getyLocation() - (Message.HEIGHT / 2));
            Point2D end = new Point2D.Double(m.getReceiver().getXLocationOfLifeline(), m.getyLocation() + (Message.HEIGHT / 2));
            selectionBoxDrawingStrategy.draw(graphics, start, end, "");
        }
    }
    */
}
