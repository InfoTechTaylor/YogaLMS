package yogaLMS.service.log;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dao.log.LogDao;
import yogaLMS.dto.log.Log;

import javax.inject.Inject;
import java.util.List;

public class LogServiceImpl implements LogService {

    private LogDao logDao;

    @Inject
    public LogServiceImpl(LogDao logDao){
        this.logDao = logDao;
    }

    @Override
    public Log create(Log log) throws YogaLMSPersistenceException {
        return logDao.create(log);
    }

    @Override
    public Log read(Long id) throws YogaLMSPersistenceException {
        return logDao.read(id);
    }

    @Override
    public void update(Log log) throws YogaLMSPersistenceException {
        logDao.update(log);
    }

    @Override
    public void delete(Log log) throws YogaLMSPersistenceException {
        logDao.delete(log);
    }

    @Override
    public List<Log> retrieveAll() throws YogaLMSPersistenceException {
        return logDao.retrieveAll();
    }
}
