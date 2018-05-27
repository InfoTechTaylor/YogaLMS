package yogaLMS.service.yogaclass;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.yogaclass.LogClass;

import java.util.List;

public interface LogClassService {

    public LogClass create(LogClass logClass) throws YogaLMSPersistenceException;

    public LogClass read(Long id) throws YogaLMSPersistenceException;

    public void update(LogClass logClass) throws YogaLMSPersistenceException;

    public void delete(LogClass logClass) throws YogaLMSPersistenceException;

    public List<LogClass> retrieveAll() throws YogaLMSPersistenceException;
}
