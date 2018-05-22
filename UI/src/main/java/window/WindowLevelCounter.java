package window;

/**
 * static class for getting the next level for a window
 */
public class WindowLevelCounter {

    private static int counter = 0;

    /**
     * @return the next level to be used for the subwindow level
     */
    public static int getNextLevel() {
        return counter++;
    }
}
