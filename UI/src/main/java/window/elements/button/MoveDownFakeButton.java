package window.elements.button;

import exception.UIException;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class MoveDownFakeButton extends FakeButton {

    /**
     * create a new fakeButton at the given position
     *
     * @param position the position for the fakebutton
     * @throws UIException if position is null
     */
    public MoveDownFakeButton(Point2D position) throws UIException {
        super(position);
    }

    @Override
    public DialogboxElement clone() {
        try {
            return new MoveDownFakeButton(getCoordinate());
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
