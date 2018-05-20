package yogaLMS.service;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.workshop.Workshop;

import java.util.List;

public interface WorkshopService {

    public Workshop create(Workshop workshop) throws YogaLMSPersistenceException;

    public Workshop read(Long id) throws YogaLMSPersistenceException;

    public void update(Workshop workshop) throws YogaLMSPersistenceException;

    public void delete(Workshop workshop) throws YogaLMSPersistenceException;

    public List<Workshop> retrieveAll() throws YogaLMSPersistenceException;
}
