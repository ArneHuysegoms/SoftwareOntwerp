package action;

import window.diagram.DiagramSubwindow;

public class UpdateInvocationMessageLabelAction extends Action {

    private int index;

    /**
     * creates an updatelabelaction for the given diagramelement and the given label
     * @param index
     */
    public UpdateInvocationMessageLabelAction(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {

    }
}
