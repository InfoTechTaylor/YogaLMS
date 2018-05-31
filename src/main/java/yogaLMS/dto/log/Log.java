package yogaLMS.dto.log;

import yogaLMS.dto.Dto;
import yogaLMS.dto.program.Program;
import yogaLMS.dto.user.StudentUser;
import yogaLMS.dto.user.User;

import java.io.Serializable;
import java.time.LocalDate;

public class Log extends Dto implements Serializable {

    private Long id;
    private Program program;
    private LocalDate startDate;
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
