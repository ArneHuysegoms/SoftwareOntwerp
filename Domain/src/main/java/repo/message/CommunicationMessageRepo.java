package repo.message;

import diagram.message.Message;
import diagram.party.Party;
import repo.label.LabelRepo;
import repo.party.PartyRepo;

import java.util.List;

public class CommunicationMessageRepo extends MessageRepo {

    @Override
    public void removeMessage(Message message) {

    }

    @Override
    public void resetMessagePositions(Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo) {

    }

    @Override
    public void resetLabelPositionsForMovedParty(LabelRepo labelRepo, PartyRepo partyRepo, Party movedParty) {

    }

    @Override
    public Message findPreviousMessage(int yLocation, Message firstMessage) throws IllegalStateException{
        throw new IllegalStateException("This operation should never happen, you have the wrong kind of repo");
    }

    @Override
    public void addMessages(List<Message> messages, Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo) {

    }
}
