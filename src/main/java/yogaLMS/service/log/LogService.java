package yogaLMS.service.log;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.log.Log;

import java.util.List;

public interface LogService {

    public Log create(Log log) throws YogaLMSPersistenceException;

    public Log read(Long id) throws YogaLMSPersistenceException;

    public void update(Log log) throws YogaLMSPersistenceException;

    public void delete(Log log) throws YogaLMSPersistenceException;

    public List<Log> retrieveAll() throws YogaLMSPersistenceException;
}
