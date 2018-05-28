package yogaLMS.dao.inmemoryimpl;

import yogaLMS.dao.GenericDao;
import yogaLMS.dto.Dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericDaoInMemImpl<T extends Dto> implements GenericDao<T> {

    private Map<Long, T> map = new HashMap<>();
    private Long currentHighestId = 0L;

    @Override
    public T create(T newEntity) {
        newEntity.setId(getNextId());
        this.map.put(newEntity.getId(), newEntity);
        return newEntity;
    }

    private Long getNextId(){
        currentHighestId++;
        return currentHighestId;
    }

    @Override
    public void update(T updatedEntity) {
        map.replace(updatedEntity.getId(), updatedEntity);
    }

    @Override
    public void delete(T persistentEntity) {
        map.remove(persistentEntity.getId());
    }

    @Override
    public T read(Long id) {
        return map.get(id);
    }

    @Override
    public List<T> retrieveAll() {
        return new ArrayList<>(map.values());
    }
}
