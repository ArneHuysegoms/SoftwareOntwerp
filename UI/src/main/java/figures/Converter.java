package figures;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import figures.Drawer.BoxDrawer;
import figures.Drawer.Drawer;
import figures.Drawer.LabelDrawer;
import figures.Drawer.SelectionBoxDrawer;
import repo.diagram.DiagramRepo;
import repo.label.LabelRepo;
import repo.message.MessageRepo;
import repo.message.SequenceMessageRepo;
import repo.party.PartyRepo;
import subwindow.Subwindow;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Set;


public abstract class Converter {
    private Subwindow subwindow;
    protected int x1, y1, x2, y2;
    protected Drawer boxDrawingStrategy,
            labelDrawingStrategy,
            selectionBoxDrawingStrategy,
            actorDrawingStrategy,
            objectDrawingStrategy,
            invokeMessageDrawingStrategy;

    /**
     * @param subwindow the subwindow to be drawn
     */
    public Converter(Subwindow subwindow) {
        this.subwindow = subwindow;
        x1 = (int) getSubwindow().getPosition().getX();
        y1 = (int) getSubwindow().getPosition().getY();
        x2 = x1 + getSubwindow().getWidth();
        y2 = y1 + getSubwindow().getHeight();
        boxDrawingStrategy = new BoxDrawer();
        selectionBoxDrawingStrategy = new SelectionBoxDrawer();
        labelDrawingStrategy = new LabelDrawer();
    }

    /**
     * draw method for sequence diagrams
     *
     * @param graphics        object used to draw on the program's window
     * @param repo            repository containing all the coordinates of a diagram
     * @param diagram         the diagram that will be drawn
     * @param selectedElement the currently selected element in the subwindow
     */
    public void draw(Graphics graphics, DiagramRepo repo, Diagram diagram, DiagramElement selectedElement) {
        drawDiagramSpecificStuff(graphics, repo, diagram, selectedElement);
        drawParties(graphics, repo.getPartyRepo(), actorDrawingStrategy, objectDrawingStrategy);
        drawPartyLabels(graphics, repo.getPartyRepo().getAllParties(), repo.getLabelRepo());
        drawMessageLabels(graphics, diagram.getFirstMessage(), repo.getLabelRepo());
        drawSelectedLabel(graphics, diagram.getFirstMessage(), repo.getLabelRepo().getMap());
        drawMessages(graphics, repo.getMessageRepo(), repo.getPartyRepo().getMap(), diagram.getFirstMessage());
        drawSelectionBox(graphics, selectedElement, repo);
    }

    /**
     * default hook method, for drawing diagram specific stuff, that does nothing
     *
     * @param graphics        object used to draw on the program's window
     * @param repo            repository containing all the coordinates of a diagram
     * @param diagram         the diagram that will be drawn
     * @param selectedElement the currently selected element in the subwindow
     */
    protected void drawDiagramSpecificStuff(Graphics graphics, DiagramRepo repo, Diagram diagram, DiagramElement selectedElement) {
    }

    /**
     * method that draws parties
     *
     * @param graphics     object used to draw on the program's window
     * @param partyRepo    repository containing all the coordinates of the parties in the subwindow's diagram
     * @param actorDrawer  a drawer object to be used to draw actor parties
     * @param objectDrawer a drawer object to be used to draw object parties
     */
    protected void drawParties(Graphics graphics, PartyRepo partyRepo, Drawer actorDrawer, Drawer objectDrawer) {
        Map<Party, Point2D> partyMap = partyRepo.getMap();

        for (Map.Entry<Party, Point2D> entry : partyMap.entrySet()) {
            Point2D start = subwindow.getAbsolutePosition(entry.getValue());
            Point2D end;

            if (entry.getKey() instanceof Actor) {
                end = new Point2D.Double(start.getX() + PartyRepo.ACTORWIDTH, start.getY() + PartyRepo.ACTORHEIGHT);
                actorDrawer.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
            } else {
                end = new Point2D.Double(start.getX() + PartyRepo.OBJECTWIDTH, start.getY() + PartyRepo.OBJECTHEIGHT);
                objectDrawer.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
            }
        }
    }

    /**
     * method that draws the subwindow's label container over the selected label it's position
     *
     * @param graphics     object used to draw on the program's window
     * @param firstMessage
     * @param labelMap     list of Label and Point2D entries
     */
    protected void drawSelectedLabel(Graphics graphics, Message firstMessage, Map<Label, Point2D> labelMap) {
        if (getSubwindow().getSelected() instanceof Label) {
            Label selectedLabel = (Label) getSubwindow().getSelected();
            Message msg = firstMessage;
            String messageNumber = "";
            while (firstMessage != null) {
                if (msg.getLabel() == selectedLabel){
                    break;
                }
                msg = msg.getNextMessage();
            }

            if(msg instanceof InvocationMessage){
                messageNumber = ((InvocationMessage)msg).getMessageNumber()+" ";
            }
            Point2D start = getSubwindow().getAbsolutePosition(labelMap.get(selectedLabel));
            labelDrawingStrategy.draw(graphics, start, null, messageNumber+getSubwindow().getLabelContainer(), getX1(), getY1(), getX2(), getY2());
        }
    }

    /**
     * method that draws labels of parties
     *
     * @param graphics   object used to draw on the program's window
     * @param allParties a set of all parties in the subwindow's diagram
     * @param labelRepo  repository containing all the coordinates of the labels in the subwindow's diagram
     */
    protected void drawPartyLabels(Graphics graphics, Set<Party> allParties, LabelRepo labelRepo) {
        Point2D start;
        Map<Label, Point2D> labelMap = labelRepo.getMap();
        getSubwindow()
        for (Party party : allParties) {
            start = getSubwindow().getAbsolutePosition(labelMap.get(party.getLabel()));
            labelDrawingStrategy.draw(graphics, start, null, party.getLabel().getLabel(), getX1(), getY1(), getX2(), getY2());
        }
    }

    /**
     * method that draws labels of messages
     *
     * @param graphics     object used to draw on the program's window
     * @param firstMessage the first message in the diagram
     * @param labelRepo    repository containing all the coordinates of the labels in the subwindow's diagram
     */
    protected void drawMessageLabels(Graphics graphics, Message firstMessage, LabelRepo labelRepo) {
        while (firstMessage != null) {
            drawMessageLabel(graphics, firstMessage, labelRepo);
            firstMessage = firstMessage.getNextMessage();
        }
    }

    /**
     * method that draws a label a message
     *
     * @param graphics  object used to draw on the program's window
     * @param message   message to be drawn
     * @param labelRepo repository containing all the coordinates of the labels in the subwindow's diagram
     */
    protected void drawMessageLabel(Graphics graphics, Message message, LabelRepo labelRepo) {
        if (message instanceof InvocationMessage) {
            String messageNumber = ((InvocationMessage) message).getMessageNumber();

            Map<Label, Point2D> labelMap = labelRepo.getMap();

            Point2D start = getSubwindow().getAbsolutePosition(labelMap.get(message.getLabel()));
            labelDrawingStrategy.draw(graphics, start, null, messageNumber + " " + message.getLabel().getLabel(), getX1(), getY1(), getX2(), getY2());
        }
    }

    /**
     * method that draws messages
     *
     * @param graphics     object used to draw on the program's window
     * @param messageRepo  repository containing all the coordinates of the messages in the subwindow's diagram
     * @param partyMap     list of Party and Point2D entries
     * @param firstMessage the first message in the diagram
     */
    protected abstract void drawMessages(Graphics graphics, MessageRepo messageRepo, Map<Party, Point2D> partyMap, Message firstMessage);

    /**
     * method that uses the selection box drawer to draw a box around the currently selected selectable parts of the diagram
     *
     * @param graphics        object used to draw on the program's window
     * @param selectedElement the subwindow's selected element
     * @param repo            repository containing all the coordinates of a diagram
     */
    protected void drawSelectionBox(Graphics graphics, DiagramElement selectedElement, DiagramRepo repo) {
        if (selectedElement instanceof Actor) {
            Actor a = (Actor) selectedElement;
            Map<Party, Point2D> partyMap = repo.getPartyRepo().getMap();
            Point2D actorPos = subwindow.getAbsolutePosition(partyMap.get(a));
            Point2D start = new Point2D.Double(actorPos.getX() - (PartyRepo.ACTORWIDTH / 2), actorPos.getY()),
                    end = new Point2D.Double(actorPos.getX() + (PartyRepo.ACTORWIDTH / 2), actorPos.getY() + PartyRepo.ACTORWIDTH);
            selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
        } else if (selectedElement instanceof Label) {
            Label l = (Label) selectedElement;
            Point2D start = subwindow.getAbsolutePosition(repo.getLabelRepo().getLocationOfLabel(l));
            selectionBoxDrawingStrategy.draw(graphics, start, new Point2D.Double(start.getX() + LabelRepo.WIDTH, start.getY() + LabelRepo.HEIGHT), "", getX1(), getY1(), getX2(), getY2());
        } else if (selectedElement instanceof Object) {
            Object o = (Object) selectedElement;
            Map<Party, Point2D> partyMap = repo.getPartyRepo().getMap();
            Point2D objectPos = subwindow.getAbsolutePosition(partyMap.get(o));
            int selectionBoxSize = 5;
            Point2D start = new Point2D.Double(objectPos.getX() - selectionBoxSize, objectPos.getY() - selectionBoxSize);
            Point2D end = new Point2D.Double(objectPos.getX() + PartyRepo.OBJECTWIDTH + selectionBoxSize, objectPos.getY() + PartyRepo.OBJECTHEIGHT + selectionBoxSize);
            selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
        } else if (selectedElement instanceof Message) {
            Message m = (Message) selectedElement;
            Point2D start;
            Point2D end;
            Map<Party, Point2D> partyMap = repo.getPartyRepo().getMap();
            if (repo.getMessageRepo() instanceof SequenceMessageRepo) {
                Map<Message, Integer> msgMap = ((SequenceMessageRepo) repo.getMessageRepo()).getMap();
                Point2D senderPos = subwindow.getAbsolutePosition(partyMap.get(m.getSender()));
                Point2D receiverPos = subwindow.getAbsolutePosition(partyMap.get(m.getReceiver()));
                start = new Point2D.Double(senderPos.getX(), (msgMap.get(m) + subwindow.getPosition().getY()) - (MessageRepo.HEIGHT / 2));
                end = new Point2D.Double(receiverPos.getX(), (msgMap.get(m) + subwindow.getPosition().getY()) + (MessageRepo.HEIGHT / 2));
                selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
            }
        }
    }

    /**
     * @return the subwindow to draw
     */
    protected Subwindow getSubwindow() {
        return subwindow;
    }

    /**
     * @return the x-coordinate of the top-left corner of the subwindow
     */
    protected int getX1() {
        return x1;
    }

    /**
     * @return the x-coordinate of the bottom-right corner of the subwindow
     */
    protected int getX2() {
        return x2;
    }

    /**
     * @return the y-coordinate of the top-left corner of the subwindow
     */
    protected int getY1() {
        return y1;
    }

    /**
     * @return the y-coordinate of the bottom-right corner of the subwindow
     */
    protected int getY2() {
        return y2;
    }
}
