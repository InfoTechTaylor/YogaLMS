package yogaLMS.service;

import yogaLMS.dto.log.LogClass;

import java.util.List;

public interface LogClassService {

    public LogClass create(LogClass logClass);

    public LogClass read(Long id);

    public void update(LogClass logClass);

    public void delete(LogClass logClass);

    public List<LogClass> retrieveAll();
}
