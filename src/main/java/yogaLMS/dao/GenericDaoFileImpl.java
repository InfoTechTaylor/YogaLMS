package yogaLMS.dao;

import yogaLMS.dto.Dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericDaoFileImpl<T extends Dto> implements GenericDao<T> {

    private Map<Long, T> map = new HashMap<>();
    private Long currentHighestId = 0L;

    public Map<Long, T> getMap() {
        return map;
    }

    @Override
    public T create(T newEntity) throws YogaLMSPersistenceException {
        newEntity.setId(getNextId());
        this.map.put(newEntity.getId(), newEntity);
        writeEntities();
        return newEntity;
    }

    private Long getNextId(){
        currentHighestId++;
        return currentHighestId;
    }

    @Override
    public void update(T updatedEntity) throws YogaLMSPersistenceException {
        map.replace(updatedEntity.getId(), updatedEntity);
        writeEntities();
    }

    @Override
    public void delete(T persistentEntity) throws YogaLMSPersistenceException {
        map.remove(persistentEntity.getId());
        writeEntities();
    }

    @Override
    public T read(Long id) throws YogaLMSPersistenceException {
        loadEntities();
        return map.get(id);
    }

    @Override
    public List<T> retrieveAll() throws YogaLMSPersistenceException {
        loadEntities();
        return new ArrayList<>(map.values());
    }

    public abstract void loadEntities() throws YogaLMSPersistenceException;

    public abstract void writeEntities() throws YogaLMSPersistenceException;
}
