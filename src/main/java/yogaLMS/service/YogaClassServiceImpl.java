package yogaLMS.service;

import yogaLMS.dao.YogaClassDao;
import yogaLMS.dto.log.YogaClass;

import javax.inject.Inject;
import java.util.List;

public class YogaClassServiceImpl implements YogaClassService {

    YogaClassDao yogaClassDao;

    @Inject
    public YogaClassServiceImpl(YogaClassDao yogaClassDao){
        this.yogaClassDao = yogaClassDao;
    }

    @Override
    public YogaClass create(YogaClass yogaClass) {
        return yogaClassDao.create(yogaClass);
    }

    @Override
    public YogaClass read(Long id) {
        return yogaClassDao.read(id);
    }

    @Override
    public void update(YogaClass yogaClass) {
        yogaClassDao.update(yogaClass);
    }

    @Override
    public void delete(YogaClass yogaClass) {
        yogaClassDao.delete(yogaClass);
    }

    @Override
    public List<YogaClass> retrieveAll() {
        return yogaClassDao.retrieveAll();
    }

    @Override
    public List<YogaClass> retrieveAllByStudio(String studio) {
        return yogaClassDao.retrieveAllByStudio(studio);
    }
}
