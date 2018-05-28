package yogaLMS.service.yogaclass;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.yogaclass.YogaClass;

import java.util.List;

public interface YogaClassService {

    public YogaClass create(YogaClass yogaClass) throws YogaLMSPersistenceException;

    public YogaClass read(Long id) throws YogaLMSPersistenceException;

    public void update(YogaClass yogaClass) throws YogaLMSPersistenceException;

    public void delete(YogaClass yogaClass) throws YogaLMSPersistenceException;

    public List<YogaClass> retrieveAll() throws YogaLMSPersistenceException;

    public List<YogaClass> retrieveAllByStudio(String studio) throws YogaLMSPersistenceException;

    public List<YogaClass> retrieveAllByLogId(Long id) throws YogaLMSPersistenceException;
}
