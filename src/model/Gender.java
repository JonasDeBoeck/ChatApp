package model;

public enum Gender {
    FEMALE ("Female"),
    MALE ("Male");

    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
