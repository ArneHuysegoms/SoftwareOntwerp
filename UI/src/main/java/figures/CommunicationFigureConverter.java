package figures;

import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Party;
import figures.Drawer.DiagramSpecificDrawers.CommunicationActorDrawer;
import figures.Drawer.DiagramSpecificDrawers.CommunicationInvokeMessageDrawer;
import figures.Drawer.DiagramSpecificDrawers.CommunicationObjectDrawer;
import repo.message.CommunicationMessageRepo;
import repo.message.MessageRepo;
import repo.party.PartyRepo;
import subwindow.Subwindow;
import util.PartyPair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class CommunicationFigureConverter extends Converter {

    public CommunicationFigureConverter(Subwindow subwindow) {
        super(subwindow);
        actorDrawingStrategy = new CommunicationActorDrawer();
        objectDrawingStrategy = new CommunicationObjectDrawer();
        invokeMessageDrawingStrategy = new CommunicationInvokeMessageDrawer();
    }

    /**
     * method that draws messages
     *
     * @param graphics     object used to draw on the program's window
     * @param messageRepo  repository containing all the coordinates of the messages in the subwindow's diagram
     * @param partyMap     list of Party and Point2D entries
     * @param firstMessage the first message in the diagram
     */
    @Override
    protected void drawMessages(Graphics graphics, MessageRepo messageRepo, Map<Party, Point2D> partyMap, Message firstMessage) {
        CommunicationMessageRepo repo = (CommunicationMessageRepo) messageRepo;

        List<PartyPair> pairs = repo.getMap();

        for (PartyPair pair : pairs) {
            int spread = PartyRepo.OBJECTHEIGHT / pair.getNumberOfMessages();
            Point2D start, end;

            for (int i = 0; i < pair.getNumberOfMessages(); i++) {
                start = calculateStart(i * spread, pair, partyMap);
                end = calculateEnd(i * spread, pair, partyMap);
                invokeMessageDrawingStrategy.draw(graphics, start, end, "", getX1(), getY1(), getX2(), getY2());
            }
        }
    }

    /**
     * calculates start point of an arrow, position depends on how many messages are sent from the first party to the second
     *
     * @param spaceing
     * @param pair
     * @param partyMap
     * @return start point of the arrow
     */
    public Point2D calculateStart(int spaceing, PartyPair pair, Map<Party, Point2D> partyMap) {
        double x, y, offset;
        if (pair.getSender() instanceof Actor) {
            offset = PartyRepo.ACTORWIDTH / 2;
        } else {
            offset = PartyRepo.OBJECTWIDTH;
        }
        x = getSubwindow().getAbsolutePosition(partyMap.get(pair.getSender())).getX() + offset;
        y = getSubwindow().getAbsolutePosition(partyMap.get(pair.getSender())).getY();
        return new Point2D.Double(x, y + spaceing);
    }

    /**
     * calculates end point of an arrow, position depends on how many messages are sent from the first party to the second
     *
     * @param spaceing
     * @param pair
     * @param partyMap
     * @return end point of the arrow
     */
    public Point2D calculateEnd(int spaceing, PartyPair pair, Map<Party, Point2D> partyMap) {
        double x, y, offset;
        if (pair.getReceiver() instanceof Actor) {
            offset = PartyRepo.ACTORWIDTH / 2;
        } else {
            offset = 0;
        }
        x = getSubwindow().getAbsolutePosition(partyMap.get(pair.getReceiver())).getX() - offset;
        y = getSubwindow().getAbsolutePosition(partyMap.get(pair.getReceiver())).getY();
        return new Point2D.Double(x, y + spaceing);
    }
}
