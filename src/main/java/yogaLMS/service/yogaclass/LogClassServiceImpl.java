package yogaLMS.service.yogaclass;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dao.yogaclass.LogClassDao;
import yogaLMS.dto.yogaclass.LogClass;

import javax.inject.Inject;
import java.util.List;

public class LogClassServiceImpl implements LogClassService{

    LogClassDao logClassDao;

    @Inject
    public LogClassServiceImpl(LogClassDao logClassDao){
        this.logClassDao = logClassDao;
    }

    @Override
    public LogClass create(LogClass logClass) throws YogaLMSPersistenceException {
        return logClassDao.create(logClass);
    }

    @Override
    public LogClass read(Long id) throws YogaLMSPersistenceException {
        return logClassDao.read(id);
    }

    @Override
    public void update(LogClass logClass) throws YogaLMSPersistenceException {
        logClassDao.update(logClass);
    }

    @Override
    public void delete(LogClass logClass) throws YogaLMSPersistenceException {
        logClassDao.delete(logClass);
    }

    @Override
    public List<LogClass> retrieveAll() throws YogaLMSPersistenceException {
        return logClassDao.retrieveAll();
    }
}
