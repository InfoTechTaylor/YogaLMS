package yogaLMS.dao;

import yogaLMS.dto.log.YogaClass;

import java.util.List;

public interface YogaClassDao {

    public YogaClass create(YogaClass yogaClass);

    public YogaClass read(Long id);

    public void update(YogaClass yogaClass);

    public void delete(YogaClass yogaClass);

    public List<YogaClass> retrieveAll();

    public List<YogaClass> retrieveAllByStudio(String studio);
}
