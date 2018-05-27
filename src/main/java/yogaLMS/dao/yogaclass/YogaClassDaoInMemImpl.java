package yogaLMS.dao.yogaclass;

import yogaLMS.dao.GenericDaoInMemImpl;
import yogaLMS.dao.yogaclass.YogaClassDao;
import yogaLMS.dto.yogaclass.YogaClass;

import java.util.ArrayList;
import java.util.List;

public class YogaClassDaoInMemImpl extends GenericDaoInMemImpl<YogaClass> implements YogaClassDao {

    @Override
    public List<YogaClass> retrieveAllByStudio(String studio) {

        List<YogaClass> allYogaClasses = this.retrieveAll();
        List<YogaClass> filteredYogaClasses = new ArrayList<>();

        for(YogaClass currentClass : allYogaClasses){
            if (currentClass.getStudioName().equals(studio)){
                filteredYogaClasses.add(currentClass);
            }
        }

        return filteredYogaClasses;
    }
}
