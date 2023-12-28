package by.onliner.pages.enums;

public enum ItemFeature {
    LAUNCH_DATE(
            "Launch date",
            "//span[text()='Дата выхода на рынок']/ancestor::tr//span[@class='value__text']",
            "//span[text()='Дата выхода на рынок']/ancestor::tr//span[@class='value__text']/ancestor::td"),
    STORAGE_CAPACITY(
            "Storage capacity",
            "//span[text()='Ёмкость накопителя']/ancestor::tr//span[@class='value__text']",
            "//span[text()='Ёмкость накопителя']/ancestor::tr//span[@class='value__text']/ancestor::td");

    private final String itemFeature;
    private final String itemFeatureValueXpath;
    private final String itemFeatureCellXpath;

    ItemFeature(String itemFeature, String itemFeatureValueXpath, String itemFeatureCellXpath) {
        this.itemFeature = itemFeature;
        this.itemFeatureValueXpath = itemFeatureValueXpath;
        this.itemFeatureCellXpath = itemFeatureCellXpath;
    }

    public String getItemFeature() {
        return itemFeature;
    }

    public String getItemFeatureValueXpath() {
        return itemFeatureValueXpath;
    }

    public String getItemFeatureCellXpath() {
        return itemFeatureCellXpath;
    }

    public static String getItemFeatureValueXpath(final String requiredItemFeature) {
        for (ItemFeature itemFeature : ItemFeature.values()) {
            if (itemFeature.getItemFeature().equals(requiredItemFeature)) {
                return itemFeature.getItemFeatureValueXpath();
            }
        }
        throw new IllegalArgumentException(String.format("Unexpected item feature: '%s'", requiredItemFeature));
    }

    public static String getItemFeatureCellXpath(final String requiredItemFeature) {
        for (ItemFeature itemFeature : ItemFeature.values()) {
            if (itemFeature.getItemFeature().equals(requiredItemFeature)) {
                return itemFeature.getItemFeatureCellXpath();
            }
        }
        throw new IllegalArgumentException(String.format("Unexpected item feature: '%s'", requiredItemFeature));
    }
}
