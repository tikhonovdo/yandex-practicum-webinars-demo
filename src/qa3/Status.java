package qa3;

enum Status {
    NEW("new"),
    IN_PROGRESS("in progress"),
    DONE("done");

    private String description;

    Status(String description) {
        this.description = description;
    }
}
