package yogaLMS.dao.yogaclass;

import yogaLMS.dto.yogaclass.YogaClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YogaClassDaoInMemoryImpl implements YogaClassDao {

    private Map<Long, YogaClass> yogaClassMap = new HashMap<>();
    private Long currentHighestId = 0L;

    @Override
    public YogaClass create(YogaClass yogaClass) {
        yogaClass.setId(getNextId());
        yogaClassMap.put(yogaClass.getId(), yogaClass);
        return yogaClass;
    }

    private Long getNextId(){
        currentHighestId++;
        return currentHighestId;
    }

    @Override
    public YogaClass read(Long id) {
        return yogaClassMap.get(id);
    }

    @Override
    public void delete(YogaClass yogaClass) {
        yogaClassMap.remove(yogaClass.getId());
    }

    @Override
    public void update(YogaClass yogaClass) {
        yogaClassMap.replace(yogaClass.getId(), yogaClass);
    }

    @Override
    public List<YogaClass> retrieveAll() {
        return new ArrayList<>(yogaClassMap.values());
    }

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
