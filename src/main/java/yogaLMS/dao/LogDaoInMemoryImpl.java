package yogaLMS.dao;

import yogaLMS.dto.log.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogDaoInMemoryImpl implements LogDao {

    private Map<Long, Log> logMap = new HashMap<>();
    private Long currentHighestId = 0L;

    @Override
    public Log create(Log log) {
        log.setId(getNextId());
        logMap.put(log.getId(), log);
        return log;
    }

    private Long getNextId(){
        currentHighestId++;
        return currentHighestId;
    }

    @Override
    public Log read(Long id) {
        return logMap.get(id);
    }

    @Override
    public void update(Log log) {
        logMap.replace(log.getId(), log);
    }

    @Override
    public void delete(Log log) {
        logMap.remove(log.getId());
    }

    @Override
    public List<Log> retrieveAll() {
        return new ArrayList<>(logMap.values());
    }

}
