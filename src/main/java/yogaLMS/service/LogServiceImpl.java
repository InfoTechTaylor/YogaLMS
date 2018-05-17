package yogaLMS.service;

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
    public Log create(Log log) {
        return logDao.create(log);
    }

    @Override
    public Log read(Long id) {
        return logDao.read(id);
    }

    @Override
    public void update(Log log) {
        logDao.update(log);
    }

    @Override
    public void delete(Log log) {
        logDao.delete(log);
    }

    @Override
    public List<Log> retrieveAll() {
        return logDao.retrieveAll();
    }
}
