package yogaLMS.dao;

import yogaLMS.dto.Dto;

import java.util.List;

/**
 * This interface is setup for the generic CRUD methods
 * for DAO classes. T will be the type of object that
 * will be retrieved from storage or written to storage.
 */
public interface GenericDao<T extends Dto> {
    /**
     * Persist the newEntity object into storage
     * @param newEntity of Type T
     * @return newEntity of Type T
     */
    T create(T newEntity) throws YogaLMSPersistenceException;


    /**
     * Retrieve an object/entity from storage using the indicated id as primary key
     * @param id of object to be read
     * @return object requested from file
     */
    T read(Long id) throws YogaLMSPersistenceException;


    /**
     * Save changes made to a persistent object/entity
     * @param updatedEntity of type T
     */
    void update(T updatedEntity) throws YogaLMSPersistenceException;


    /**
     * Delete persisted entity
     * @param persistentEntity of type T
     */
    void delete(T persistentEntity) throws YogaLMSPersistenceException;


    /**
     * Return all entities of type T in a List
     */
    List<T> retrieveAll() throws YogaLMSPersistenceException;


}
