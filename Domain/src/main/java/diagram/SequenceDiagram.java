package diagram;

import diagram.message.Message;
import diagram.party.Party;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * subclass of diagram, depicting a sequence diagam
 */
public class SequenceDiagram extends Diagram {

    /**
     * see superclass
     */
    public SequenceDiagram(){
        this(null, null);
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     */
    public SequenceDiagram(List<Party> parties, Message firstMessage){
        this(parties, firstMessage, null);
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     */
    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement){
        this(parties, firstMessage, selectedElement, "");
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     * @param labelContainer
     */
    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(parties, firstMessage, selectedElement, labelContainer, false, true, false);
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     * @param labelContainer
     * @param labelMode
     * @param validLabel
     * @param messageMode
     */
    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement,
                                 String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        super(parties, firstMessage, selectedElement, labelContainer, labelMode, validLabel, messageMode);
    }
}
