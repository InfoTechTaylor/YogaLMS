package yogaLMS.dto.user;

import yogaLMS.dto.log.Log;
import yogaLMS.dto.program.Program;

import java.io.Serializable;

public class StudentUser extends User implements Serializable {

    private Program program;
    private Log log;

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
