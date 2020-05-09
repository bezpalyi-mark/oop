package ua.khpi.oop.bezpalyi12;

public class OperatorChecker {
    private static final String LIFECELL_NUMBER = "^\\+?3?8?0((63)|(93)|(73))(\\d{7})$";

    private static final String KYIVSTAR_NUMBER = "^\\+?3?8?0((39)|(67)|(68)|(96)|(97)|(98))(\\d{7})$";

    public static boolean checkLifeCellNumber(String lifeCellNumber) {
        return lifeCellNumber.matches(LIFECELL_NUMBER);
    }

    public static boolean checkKyivStarNumber(String kyivStarNumber) {
        return kyivStarNumber.matches(KYIVSTAR_NUMBER);
    }
}
