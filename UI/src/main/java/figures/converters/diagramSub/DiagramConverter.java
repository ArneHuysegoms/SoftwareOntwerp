package figures.converters.diagramSub;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;

import figures.converters.SubwindowConverter;
import figures.drawable.basicShapes.Box;
import figures.drawable.subwindowFigures.DiagramSubwindowFigure;
import figures.drawable.diagramFigures.SelectionBoxFigure;
import figures.drawable.diagramFigures.StickMan;
import window.diagram.DiagramSubwindow;
import view.diagram.DiagramView;
import view.label.LabelView;
import view.message.MessageView;
import view.message.SequenceMessageView;
import view.party.PartyView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Set;


public abstract class DiagramConverter extends SubwindowConverter {
    private DiagramSubwindow diagramSubwindow;
    protected int x1, y1, x2, y2;

    /*protected Drawer boxDrawingStrategy,
            labelDrawingStrategy,
            selectionBoxDrawingStrategy,
            actorDrawingStrategy,
            objectDrawingStrategy,
            invokeMessageDrawingStrategy;*/

    /**
     * @param diagramSubwindow the diagramSubwindow to be drawn
     */
    public DiagramConverter(DiagramSubwindow diagramSubwindow) {
        this.diagramSubwindow = diagramSubwindow;
        x1 = (int) getDiagramSubwindow().getPosition().getX();
        y1 = (int) getDiagramSubwindow().getPosition().getY();
        x2 = x1 + getDiagramSubwindow().getWidth();
        y2 = y1 + getDiagramSubwindow().getHeight();
        //boxDrawingStrategy = new BoxDrawer();
        //selectionBoxDrawingStrategy = new SelectionBoxDrawer();
        //labelDrawingStrategy = new LabelDrawer();
    }

    /**
     * draw method for sequence diagrams
     *
     * @param graphics        object used to draw on the program's window
     //* @param repo            repository containing all the coordinates of a diagram
     //* @param diagram         the diagram that will be drawn
     //* @param selectedElement the currently selected element in the diagramSubwindow
     */
    @Override
    public void draw(Graphics graphics) {
        DiagramView repo = diagramSubwindow.getFacade().getActiveView();
        Diagram diagram = diagramSubwindow.getFacade().getDiagram();
        DiagramElement selectedElement = diagramSubwindow.getSelected();

        drawSubwindow(graphics);
        drawDiagramSpecificStuff(graphics, repo, diagram, selectedElement);
        //drawParties(graphics, repo.getPartyView(), actorDrawingStrategy, objectDrawingStrategy);
        drawParties(graphics, repo.getPartyView());
        drawPartyLabels(graphics, repo.getPartyView().getAllParties(), repo.getLabelView());
        drawMessageLabels(graphics, diagram.getFirstMessage(), repo.getLabelView());
        drawMessages(graphics, repo.getMessageView(), repo.getPartyView().getMap(), diagram.getFirstMessage());
        drawSelectedLabel(graphics, diagram.getFirstMessage(), repo.getLabelView().getMap());
        drawSelectionBox(graphics, selectedElement, repo);
    }

    /**
     * default hook method, for drawing diagram specific stuff, that does nothing
     *
     * @param graphics        object used to draw on the program's window
     * @param repo            repository containing all the coordinates of a diagram
     * @param diagram         the diagram that will be drawn
     * @param selectedElement the currently selected element in the diagramSubwindow
     */
    protected void drawDiagramSpecificStuff(Graphics graphics, DiagramView repo, Diagram diagram, DiagramElement selectedElement) {
    }

    /**
     * method that draws parties
     *
     * @param graphics     object used to draw on the program's window
     * @param partyView    repository containing all the coordinates of the parties in the diagramSubwindow's diagram
     //@param actorDrawer  a drawer object to be used to draw actor parties
     //@param objectDrawer a drawer object to be used to draw object parties
     */
    //protected void drawParties(Graphics graphics, PartyView partyView, Drawer actorDrawer, Drawer objectDrawer) {
    protected void drawParties(Graphics graphics, PartyView partyView) {
        Map<Party, Point2D> partyMap = partyView.getMap();

        for (Map.Entry<Party, Point2D> entry : partyMap.entrySet()) {
            Point2D start = diagramSubwindow.getAbsolutePosition(entry.getValue());
            Point2D end;

            if (entry.getKey() instanceof Actor) {
                end = new Point2D.Double(start.getX() + PartyView.ACTORWIDTH, start.getY() + PartyView.ACTORHEIGHT);
                new StickMan(start).draw(graphics, getX1(), getY1(), getX2(), getY2());
                //actorDrawer.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
            } else {
                end = new Point2D.Double(start.getX() + PartyView.OBJECTWIDTH, start.getY() + PartyView.OBJECTHEIGHT);
                new Box(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
                // /objectDrawer.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
            }
        }
    }

    /**
     * method that draws the diagramSubwindow's label container over the selected label it's position
     *
     * @param graphics     object used to draw on the program's window
     * @param firstMessage
     * @param labelMap     list of Label and Point2D entries
     */
    protected void drawSelectedLabel(Graphics graphics, Message firstMessage, Map<Label, Point2D> labelMap) {
        if (getDiagramSubwindow().isEditing()) {
            Label selectedLabel = (Label) getDiagramSubwindow().getSelected();
            Message msg = firstMessage;
            String messageNumber = "";
            while (msg != null) {
                if (msg.getLabel() == selectedLabel) {
                    break;
                }
                msg = msg.getNextMessage();
            }

            if (msg instanceof InvocationMessage) {
                messageNumber = ((InvocationMessage) msg).getMessageNumber() + " ";
            }
            Point2D start = getDiagramSubwindow().getAbsolutePosition(labelMap.get(selectedLabel));
            if (!getDiagramSubwindow().checkIfValidLable()) {
                graphics.setColor(Color.RED);
                drawLabel(graphics, start,  messageNumber + getDiagramSubwindow().getLabelContainer(), getX1(), getY1(), getX2(), getY2());
                //labelDrawingStrategy.draw(graphics, start, null, messageNumber + getDiagramSubwindow().getLabelContainer(), getX1(), getY1(), getX2(), getY2());
                graphics.setColor(Color.BLACK);
            } else {
                drawLabel(graphics, start,  messageNumber + getDiagramSubwindow().getLabelContainer(), getX1(), getY1(), getX2(), getY2());
                //labelDrawingStrategy.draw(graphics, start, null, messageNumber + getDiagramSubwindow().getLabelContainer(), getX1(), getY1(), getX2(), getY2());
            }

        }
    }

    /**
     * method that draws labels of parties
     *
     * @param graphics   object used to draw on the program's window
     * @param allParties a set of all parties in the diagramSubwindow's diagram
     * @param labelView  repository containing all the coordinates of the labels in the diagramSubwindow's diagram
     */
    protected void drawPartyLabels(Graphics graphics, Set<Party> allParties, LabelView labelView) {
        Point2D start;
        Map<Label, Point2D> labelMap = labelView.getMap();
        for (Party party : allParties) {
            start = getDiagramSubwindow().getAbsolutePosition(labelMap.get(party.getLabel()));
            drawLabel(graphics, start, party.getLabel().getLabel(), getX1(), getY1(), getX2(), getY2());
            //labelDrawingStrategy.draw(graphics, start, null, party.getLabel().getLabel(), getX1(), getY1(), getX2(), getY2());
        }
    }

    /**
     * method that draws labels of messages
     *
     * @param graphics     object used to draw on the program's window
     * @param firstMessage the first message in the diagram
     * @param labelView    repository containing all the coordinates of the labels in the diagramSubwindow's diagram
     */
    protected void drawMessageLabels(Graphics graphics, Message firstMessage, LabelView labelView) {
        while (firstMessage != null) {
            drawMessageLabel(graphics, firstMessage, labelView);
            firstMessage = firstMessage.getNextMessage();
        }
    }

    /**
     * method that draws a label a message
     *
     * @param graphics  object used to draw on the program's window
     * @param message   message to be drawn
     * @param labelView repository containing all the coordinates of the labels in the diagramSubwindow's diagram
     */
    protected void drawMessageLabel(Graphics graphics, Message message, LabelView labelView) {
        if (message instanceof InvocationMessage) {
            String messageNumber = ((InvocationMessage) message).getMessageNumber();

            Map<Label, Point2D> labelMap = labelView.getMap();

            Point2D start = getDiagramSubwindow().getAbsolutePosition(labelMap.get(message.getLabel()));
            drawLabel(graphics, start, messageNumber + " " + message.getLabel().getLabel(), getX1(), getY1(), getX2(), getY2());
            //labelDrawingStrategy.draw(graphics, start, null, messageNumber + " " + message.getLabel().getLabel(), getX1(), getY1(), getX2(), getY2());
        }
    }

    private void drawLabel(Graphics graphics, Point2D start, String label, int minX, int minY, int maxX, int maxY) {
        double x1 = start.getX(), y1 = start.getY(), stringPixelEstimate = 50;
        if ((label.length()) * 5 > stringPixelEstimate) {
            stringPixelEstimate = (label.length()) * 5;
        }
        if (x1 > minX && y1 > minY && x1 + stringPixelEstimate < maxX && y1 < maxY) {
            Color c = graphics.getColor();
            graphics.setColor(Color.WHITE);
            graphics.fillRect((int) x1, (int) y1, 50, 15);
            graphics.setColor(c);
            graphics.drawString(label, (int) start.getX() + 3, (int) start.getY() + 10);
        }
    }

    /**
     * method that draws messages
     *
     * @param graphics     object used to draw on the program's window
     * @param messageView  repository containing all the coordinates of the messages in the diagramSubwindow's diagram
     * @param partyMap     list of Party and Point2D entries
     * @param firstMessage the first message in the diagram
     */
    protected abstract void drawMessages(Graphics graphics, MessageView messageView, Map<Party, Point2D> partyMap, Message firstMessage);

    /**
     * method that uses the selection box drawer to draw a box around the currently selected selectable parts of the diagram
     *
     * @param graphics        object used to draw on the program's window
     * @param selectedElement the diagramSubwindow's selected element
     * @param repo            repository containing all the coordinates of a diagram
     */
    protected void drawSelectionBox(Graphics graphics, DiagramElement selectedElement, DiagramView repo) {
        if (selectedElement instanceof Actor) {
            Actor a = (Actor) selectedElement;
            Map<Party, Point2D> partyMap = repo.getPartyView().getMap();
            Point2D actorPos = diagramSubwindow.getAbsolutePosition(partyMap.get(a));
            Point2D start = new Point2D.Double(actorPos.getX() - (PartyView.ACTORWIDTH / 2), actorPos.getY()),
                    end = new Point2D.Double(actorPos.getX() + (PartyView.ACTORWIDTH / 2), actorPos.getY() + PartyView.ACTORWIDTH);
            new SelectionBoxFigure(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
            //selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
        } else if (selectedElement instanceof Label) {
            Label l = (Label) selectedElement;
            Point2D start = diagramSubwindow.getAbsolutePosition(repo.getLabelView().getLocationOfLabel(l));
            new SelectionBoxFigure(start, new Point2D.Double(start.getX() + LabelView.WIDTH, start.getY() + LabelView.HEIGHT)).draw(graphics, getX1(), getY1(), getX2(), getY2());
            //selectionBoxDrawingStrategy.draw(graphics, start, new Point2D.Double(start.getX() + LabelView.WIDTH, start.getY() + LabelView.HEIGHT), "", getX1(), getY1(), getX2(), getY2());
        } else if (selectedElement instanceof Object) {
            Object o = (Object) selectedElement;
            Map<Party, Point2D> partyMap = repo.getPartyView().getMap();
            Point2D objectPos = diagramSubwindow.getAbsolutePosition(partyMap.get(o));
            int selectionBoxSize = 5;
            Point2D start = new Point2D.Double(objectPos.getX() - selectionBoxSize, objectPos.getY() - selectionBoxSize);
            Point2D end = new Point2D.Double(objectPos.getX() + PartyView.OBJECTWIDTH + selectionBoxSize, objectPos.getY() + PartyView.OBJECTHEIGHT + selectionBoxSize);
            new SelectionBoxFigure(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
            //selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
        } else if (selectedElement instanceof Message) {
            Message m = (Message) selectedElement;
            Point2D start;
            Point2D end;
            Map<Party, Point2D> partyMap = repo.getPartyView().getMap();
            if (repo.getMessageView() instanceof SequenceMessageView) {
                Map<Message, Integer> msgMap = ((SequenceMessageView) repo.getMessageView()).getMap();
                Point2D senderPos = diagramSubwindow.getAbsolutePosition(partyMap.get(m.getSender()));
                Point2D receiverPos = diagramSubwindow.getAbsolutePosition(partyMap.get(m.getReceiver()));
                start = new Point2D.Double(senderPos.getX(), (msgMap.get(m) + diagramSubwindow.getPosition().getY()) - (MessageView.HEIGHT / 2));
                end = new Point2D.Double(receiverPos.getX(), (msgMap.get(m) + diagramSubwindow.getPosition().getY()) + (MessageView.HEIGHT / 2));
                new SelectionBoxFigure(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
                //selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
            }
        }
    }

    @Override
    public void drawSubwindow(Graphics graphics){
        new DiagramSubwindowFigure(diagramSubwindow).draw(graphics, 0, 0, 2000, 2000);
    }

    /**
     * @return the diagramSubwindow to draw
     */
    protected DiagramSubwindow getDiagramSubwindow() {
        return diagramSubwindow;
    }

    /**
     * @return the x-coordinate of the top-left corner of the diagramSubwindow
     */
    protected int getX1() {
        return x1;
    }

    /**
     * @return the x-coordinate of the bottom-right corner of the diagramSubwindow
     */
    protected int getX2() {
        return x2;
    }

    /**
     * @return the y-coordinate of the top-left corner of the diagramSubwindow
     */
    protected int getY1() {
        return y1;
    }

    /**
     * @return the y-coordinate of the bottom-right corner of the diagramSubwindow
     */
    protected int getY2() {
        return y2;
    }
}
