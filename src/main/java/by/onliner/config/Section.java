package by.onliner.config;

public enum Section {
    NOTEBOOK("notebook","/notebook");

    private final String name;
    private final String endpoint;

    Section(String name, String endpoint) {
        this.name = name;
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public static String getEndpoint(final String sectionName) {
        for (Section section : Section.values()) {
            if (section.getName().equals(sectionName)) {
                return section.getEndpoint();
            }
        }
        throw new IllegalArgumentException(String.format("Unexpected section: '%s'", sectionName));
    }
}
