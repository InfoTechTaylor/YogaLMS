package yogaLMS.service.workshop;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dao.workshop.WorkshopDao;
import yogaLMS.dto.workshop.Workshop;

import javax.inject.Inject;
import java.util.List;

public class WorkshopServiceFileImpl implements WorkshopService {

    private WorkshopDao workshopDao;

    @Inject
    public WorkshopServiceFileImpl(WorkshopDao workshopDao) {
        this.workshopDao = workshopDao;
    }

    @Override
    public Workshop create(Workshop workshop) throws YogaLMSPersistenceException {
        return workshopDao.create(workshop);
    }

    @Override
    public Workshop read(Long id) throws YogaLMSPersistenceException {
        return workshopDao.read(id);
    }

    @Override
    public void update(Workshop workshop) throws YogaLMSPersistenceException {
        workshopDao.update(workshop);
    }

    @Override
    public void delete(Workshop workshop) throws YogaLMSPersistenceException {
        workshopDao.delete(workshop);
    }

    @Override
    public List<Workshop> retrieveAll() throws YogaLMSPersistenceException {
        return workshopDao.retrieveAll();
    }
}
