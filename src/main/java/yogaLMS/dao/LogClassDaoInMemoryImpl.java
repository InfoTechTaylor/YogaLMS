package yogaLMS.dao;

import yogaLMS.dto.log.LogClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogClassDaoInMemoryImpl implements LogClassDao {

    private Map<Long, LogClass> logClassMap = new HashMap<>();
    private Long currentHighestId = 0L;

    @Override
    public LogClass create(LogClass logClass) {
        logClass.setId(getNextId());
        logClassMap.put(logClass.getId(), logClass);
        return logClass;
    }

    private Long getNextId(){
        currentHighestId++;
        return currentHighestId;
    }

    @Override
    public LogClass read(Long id) {
        return logClassMap.get(id);
    }

    @Override
    public void update(LogClass logClass) {
        logClassMap.replace(logClass.getId(), logClass);
    }

    @Override
    public void delete(LogClass logClass) {
        logClassMap.remove(logClass.getId());
    }

    @Override
    public List<LogClass> retrieveAll() {
        return new ArrayList<>(logClassMap.values());
    }
}
