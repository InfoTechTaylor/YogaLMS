package yogaLMS.service.yogapose;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.yogapose.YogaPose;

import java.util.List;

public interface YogaPoseService {

    YogaPose create(YogaPose newEntity) throws YogaLMSPersistenceException;

    YogaPose read(Long id) throws YogaLMSPersistenceException;

    void update(YogaPose updatedEntity) throws YogaLMSPersistenceException;

    void delete(YogaPose persistentEntity) throws YogaLMSPersistenceException;

    List<YogaPose> retrieveAll() throws YogaLMSPersistenceException;
}
