package yogaLMS.dto.user;

import java.io.Serializable;

public class FacultyUser extends User implements Serializable {

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
