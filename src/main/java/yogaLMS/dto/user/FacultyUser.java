package yogaLMS.dto.user;

public class FacultyUser extends User {

    private boolean isMentor;
    private String yogaCertification;

    public boolean isMentor() {
        return isMentor;
    }

    public void setMentor(boolean mentor) {
        isMentor = mentor;
    }

    public String getYogaCertification() {
        return yogaCertification;
    }

    public void setYogaCertification(String yogaCertification) {
        this.yogaCertification = yogaCertification;
    }
}
