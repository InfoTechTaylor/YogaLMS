package yogaLMS.service;

import yogaLMS.dto.yogaclass.YogaClass;

import java.util.List;

public interface YogaClassService {

    public YogaClass create(YogaClass yogaClass);

    public YogaClass read(Long id);

    public void update(YogaClass yogaClass);

    public void delete(YogaClass yogaClass);

    public List<YogaClass> retrieveAll();

    public List<YogaClass> retrieveAllByStudio(String studio);

    public List<YogaClass> retrieveAllByLogId(Long id);
}
