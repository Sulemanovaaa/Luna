package utils;

public class ScaleUtil {

    private static final int bottomBorder = 0;
    private static final int topBorder = 100;

    public static int checkAndSetValue(int value) {
        if (value < bottomBorder) {
            return bottomBorder;
        }
        else if (value > topBorder) {
            return topBorder;
        }
        return value;
    }
}
