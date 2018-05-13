package yogaLMS.service;

import yogaLMS.dao.YogaClassDao;
import yogaLMS.dto.log.LogClass;
import yogaLMS.dto.log.YogaClass;

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

    @Override
    public List<YogaClass> retrieveAllByLogId(Long id) {
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
