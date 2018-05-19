package figures.converters;

import diagram.party.Actor;
import figures.drawable.subwindowFigures.*;
import window.dialogbox.*;
import window.elements.DialogboxElement;
import window.elements.textbox.TextBox;

import java.awt.*;

public class DialogBoxConverter extends SubwindowConverter {

    private SubwindowFigure figure;
    private DialogBox dialogBox;

    public DialogBoxConverter(DialogBox dialogBox) {
        this.dialogBox = dialogBox;
        setFigure(dialogBox);
    }

    @Override
    public void draw(Graphics graphics){
        drawSubwindow(graphics);
        drawSelectionBox(graphics);
    }

    private void drawSelectionBox(Graphics graphics) {
        DialogboxElement selectedElement = dialogBox.get
        if (selectedElement instanceof TextBox) {
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

    @Override
    public void drawSubwindow(Graphics graphics) {
        figure.draw(graphics, 0, 0, 2000, 2000);
    }

    private void setFigure(DialogBox dialogBox) {
        if (dialogBox instanceof DiagramDialogBox) {
            figure = new DiagramDialogBoxFigure((DiagramDialogBox)dialogBox);
        } else if (dialogBox instanceof InvocationMessageDialogBox) {
            figure = new InvocationMessageDialogBoxFigure((InvocationMessageDialogBox)dialogBox);
        } else if (dialogBox instanceof PartyDialogBox) {
            figure = new PartyDialogBoxFigure((PartyDialogBox) dialogBox);
        } else if (dialogBox instanceof ResultMessageDialogBox) {
            figure = new ResultMessageDialogBoxFigure((ResultMessageDialogBox) dialogBox);
        }
    }
}
