package yogaLMS.service.yogaclass;

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
    public LogClass create(LogClass logClass) {
        return logClassDao.create(logClass);
    }

    @Override
    public LogClass read(Long id) {
        return logClassDao.read(id);
    }

    @Override
    public void update(LogClass logClass) {
        logClassDao.update(logClass);
    }

    @Override
    public void delete(LogClass logClass) {
        logClassDao.delete(logClass);
    }

    @Override
    public List<LogClass> retrieveAll() {
        return logClassDao.retrieveAll();
    }
}
