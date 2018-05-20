package yogaLMS.service.workshop;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dao.workshop.LogWorkshopDao;
import yogaLMS.dto.workshop.LogWorkshop;

import javax.inject.Inject;
import java.util.List;

public class LogWorkshopServiceFileImpl implements LogWorkshopService {

    private LogWorkshopDao logWorkshopDao;

    @Inject
    public LogWorkshopServiceFileImpl(LogWorkshopDao logWorkshopDao) {
        this.logWorkshopDao = logWorkshopDao;
    }

    @Override
    public LogWorkshop create(LogWorkshop logWorkshop) throws YogaLMSPersistenceException {
        return logWorkshopDao.create(logWorkshop);
    }

    @Override
    public LogWorkshop read(Long id) throws YogaLMSPersistenceException {
        return logWorkshopDao.read(id);
    }

    @Override
    public void update(LogWorkshop logWorkshop) throws YogaLMSPersistenceException {
        logWorkshopDao.update(logWorkshop);
    }

    @Override
    public void delete(LogWorkshop logWorkshop) throws YogaLMSPersistenceException {
        logWorkshopDao.delete(logWorkshop);
    }

    @Override
    public List<LogWorkshop> retrieveAll() throws YogaLMSPersistenceException {
        return logWorkshopDao.retrieveAll();
    }
}
