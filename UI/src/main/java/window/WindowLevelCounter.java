package window;

public class WindowLevelCounter {

    private static int counter = 0;

    public static int getNextLevel(){
        return counter++;
    }
}
