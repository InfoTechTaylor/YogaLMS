package yogaLMS.dto.workshop;

import yogaLMS.dto.Dto;
import yogaLMS.dto.log.Log;

public class LogWorkshop extends Dto {
    private Long id;
    private Log log;
    private Workshop workshop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
}
