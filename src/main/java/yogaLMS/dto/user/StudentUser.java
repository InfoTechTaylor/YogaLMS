package yogaLMS.dto.user;

import yogaLMS.dto.log.Log;
import yogaLMS.dto.program.Program;

public class StudentUser extends User{

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
