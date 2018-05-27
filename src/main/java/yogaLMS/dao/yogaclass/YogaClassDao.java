package yogaLMS.dao.yogaclass;

import yogaLMS.dao.GenericDao;
import yogaLMS.dto.yogaclass.YogaClass;

import java.util.List;

public interface YogaClassDao extends GenericDao<YogaClass> {

    List<YogaClass> retrieveAllByStudio(String studio);
}
