package by.onliner.helpers;

public class IntHelper {
    public static int formatItemFeatureText(final String itemFeature, final String itemFeatureText) {
        return switch (itemFeature) {
            case "Launch date" -> Integer.parseInt(StringHelper.substringYearValueInString(itemFeatureText));
            case "Storage capacity" -> Integer.parseInt(StringHelper.replaceUnitsOfMemory(itemFeatureText));
            default -> throw new IllegalArgumentException(String.format("Unexpected item feature: '%s'", itemFeature));
        };
    }
}
