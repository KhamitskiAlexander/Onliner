package by.onliner.helpers;

public class StringHelper {
    public static String substringYearValueInString(final String date) {
        return date.substring(0, 4);
    }

    public static String replaceUnitsOfMemory(final String memoryCapacity) {
        return memoryCapacity.replace(" ГБ", "");
    }
}
