package figures.converters.diagramSub;

import diagram.label.Label;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Party;
import figures.drawable.basicShapes.Arrow;
import util.PartyPair;
import view.label.LabelView;
import view.message.CommunicationMessageView;
import view.message.MessageView;
import view.party.PartyView;
import window.diagram.DiagramSubwindow;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class CommunicationConverter extends DiagramConverter {

    public CommunicationConverter(DiagramSubwindow diagramSubwindow) {
        super(diagramSubwindow);
    }

    @Override
    protected void drawMessageLabel(Graphics graphics, Message message, LabelView labelView) {
        if (message instanceof InvocationMessage) {
            Map<Label, Point2D> labelMap = labelView.getMap();

            Point2D start = getDiagramSubwindow().getAbsolutePosition(labelMap.get(message.getLabel()));
            drawLabel(graphics, start, message.toString(), getX1(), getY1(), getX2(), getY2());
        }
    }

    /**
     * method that draws messages
     *
     * @param graphics     object used to draw on the program's window
     * @param messageView  view object containing all the coordinates of the messages in the window.diagram's diagram
     * @param partyMap     list of Party and Point2D entries
     * @param firstMessage the first message in the diagram
     */
    @Override
    protected void drawMessages(Graphics graphics, MessageView messageView, Map<Party, Point2D> partyMap, Message firstMessage) {
        CommunicationMessageView view = (CommunicationMessageView) messageView;

        List<PartyPair> pairs = view.getMap();

        for (PartyPair pair : pairs) {
            int spread = PartyView.OBJECTHEIGHT / pair.getNumberOfMessages();
            Point2D start, end;

            for (int i = 0; i < pair.getNumberOfMessages(); i++) {
                start = calculateStart(i * spread, pair, partyMap);
                end = calculateEnd(i * spread, pair, partyMap);
                new Arrow(start, end).draw(graphics, getX1(), getY1(), getX2(), getY2());
            }
        }
    }

    /**
     * calculates start point of an arrow, position depends on how many messages are sent from the first party to the second
     *
     * @param spaceing space to leave between the arrow and the object's top y-coordinate
     * @param pair     a pair object used to keep track of the messages and number of messages between pairs of parties in communication diagrams
     * @param partyMap list of Party and Point2D entries
     * @return start point of the arrow
     */
    private Point2D calculateStart(int spaceing, PartyPair pair, Map<Party, Point2D> partyMap) {
        double x, y, offset;
        if (pair.getSender() instanceof Actor) {
            offset = PartyView.ACTORWIDTH / 2;
        } else {
            offset = PartyView.OBJECTWIDTH;
        }
        x = getDiagramSubwindow().getAbsolutePosition(partyMap.get(pair.getSender())).getX() + offset;
        y = getDiagramSubwindow().getAbsolutePosition(partyMap.get(pair.getSender())).getY();
        return new Point2D.Double(x, y + spaceing);
    }

    /**
     * calculates end point of an arrow, position depends on how many messages are sent from the first party to the second
     *
     * @param spaceing space to leave between the arrow and the object's top y-coordinate
     * @param pair     a pair object used to keep track of the messages and number of messages between pairs of parties in communication diagrams
     * @param partyMap list of Party and Point2D entries
     * @return end point of the arrow
     */
    private Point2D calculateEnd(int spaceing, PartyPair pair, Map<Party, Point2D> partyMap) {
        double x, y, offset;
        if (pair.getReceiver() instanceof Actor) {
            offset = PartyView.ACTORWIDTH / 2;
        } else {
            offset = 0;
        }
        x = getDiagramSubwindow().getAbsolutePosition(partyMap.get(pair.getReceiver())).getX() - offset;
        y = getDiagramSubwindow().getAbsolutePosition(partyMap.get(pair.getReceiver())).getY();
        return new Point2D.Double(x, y + spaceing);
    }
}
