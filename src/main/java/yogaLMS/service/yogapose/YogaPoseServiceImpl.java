package yogaLMS.service.yogapose;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dao.yogapose.YogaPoseDao;
import yogaLMS.dto.yogapose.PoseCategory;
import yogaLMS.dto.yogapose.YogaPose;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YogaPoseServiceImpl implements YogaPoseService {

    private YogaPoseDao yogaPoseDao;

    @Inject
    public YogaPoseServiceImpl(YogaPoseDao yogaPoseDao) {
        this.yogaPoseDao = yogaPoseDao;
    }

    @Override
    public YogaPose create(YogaPose newEntity) throws YogaLMSPersistenceException {
        return yogaPoseDao.create(newEntity);
    }

    @Override
    public YogaPose read(Long id) throws YogaLMSPersistenceException {
        return yogaPoseDao.read(id);
    }

    @Override
    public void update(YogaPose updatedEntity) throws YogaLMSPersistenceException {
        yogaPoseDao.update(updatedEntity);
    }

    @Override
    public void delete(YogaPose persistentEntity) throws YogaLMSPersistenceException {
        yogaPoseDao.delete(persistentEntity);
    }

    @Override
    public List<YogaPose> retrieveAll() throws YogaLMSPersistenceException {
        return yogaPoseDao.retrieveAll();
    }

    @Override
    public List<YogaPose> retrieveAllByCategory(PoseCategory category) throws YogaLMSPersistenceException {
        List<YogaPose> allPoses = this.retrieveAll();

        List<YogaPose> filteredList =
        allPoses.stream()
                .filter(s -> s.getCategories()
                        .stream()
                        .anyMatch(c -> category == c))
                .collect(Collectors.toList());

        return filteredList;
    }
}
