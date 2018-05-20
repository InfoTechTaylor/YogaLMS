package yogaLMS.service.workshop;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.workshop.LogWorkshop;

import java.util.List;

public interface LogWorkshopService {

    public LogWorkshop create(LogWorkshop logWorkshop) throws YogaLMSPersistenceException;

    public LogWorkshop read(Long id) throws YogaLMSPersistenceException;

    public void update(LogWorkshop logWorkshop) throws YogaLMSPersistenceException;

    public void delete(LogWorkshop logWorkshop) throws YogaLMSPersistenceException;

    public List<LogWorkshop> retrieveAll() throws YogaLMSPersistenceException;
}
