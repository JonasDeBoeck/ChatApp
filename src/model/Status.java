package model;

public enum Status {
    ONLINE ("Online"),
    OFFLINE ("Offline"),
    AWAY ("Away"),
    OTHER ("");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
