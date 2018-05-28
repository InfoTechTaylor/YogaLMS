package yogaLMS.service.yogaclass;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dao.yogaclass.YogaClassDao;
import yogaLMS.dto.yogaclass.LogClass;
import yogaLMS.dto.yogaclass.YogaClass;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class YogaClassServiceImpl implements YogaClassService {

    YogaClassDao yogaClassDao;
    LogClassService logClassService;

    @Inject
    public YogaClassServiceImpl(YogaClassDao yogaClassDao, LogClassService logClassSerivce){
        this.yogaClassDao = yogaClassDao;
        this.logClassService = logClassSerivce;
    }

    @Override
    public YogaClass create(YogaClass yogaClass) throws YogaLMSPersistenceException {
        return yogaClassDao.create(yogaClass);
    }

    @Override
    public YogaClass read(Long id) throws YogaLMSPersistenceException {
        return yogaClassDao.read(id);
    }

    @Override
    public void update(YogaClass yogaClass) throws YogaLMSPersistenceException {
        yogaClassDao.update(yogaClass);
    }

    @Override
    public void delete(YogaClass yogaClass) throws YogaLMSPersistenceException {
        yogaClassDao.delete(yogaClass);
    }

    @Override
    public List<YogaClass> retrieveAll() throws YogaLMSPersistenceException {
        return yogaClassDao.retrieveAll();
    }

    @Override
    public List<YogaClass> retrieveAllByStudio(String studio) throws YogaLMSPersistenceException {
        return yogaClassDao.retrieveAllByStudio(studio);
    }

    @Override
    public List<YogaClass> retrieveAllByLogId(Long id) throws YogaLMSPersistenceException {
        List<LogClass> allLogClasses = logClassService.retrieveAll();
        List<YogaClass> allYogaClassesInLog = new ArrayList<>();

        for(LogClass currentLogClass : allLogClasses){
            if(currentLogClass.getLog().getId() == id){
                // if LogClass with id exists, look up YogaClass and add to allYogaClassesInLog list
                YogaClass yogaClass = this.read(id);
                allYogaClassesInLog.add(yogaClass);
            }
        }
        return allYogaClassesInLog;
    }
}
