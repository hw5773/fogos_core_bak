package FlexID;

public enum CategoryType {
    ANY("any"),
    FILE_TRANSFER("file transfer"),
    STREAMING("streaming"),
    WEB("web");

    private String name;

    CategoryType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
