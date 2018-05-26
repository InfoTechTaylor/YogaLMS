package yogaLMS.dto.yogaclass;

import yogaLMS.dto.Dto;
import yogaLMS.dto.log.Log;

public class LogClass extends Dto {

    private Long id;
    private Log log;
    private YogaClass yogaClass;

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

    public YogaClass getYogaClass() {
        return yogaClass;
    }

    public void setYogaClass(YogaClass yogaClass) {
        this.yogaClass = yogaClass;
    }
}
