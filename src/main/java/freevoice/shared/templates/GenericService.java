package freevoice.shared.templates;

import freevoice.shared.templates.exceptions.GenericNotFoundEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GenericService<T, ID> implements IGenericService<T, ID> {

    private final GenericRepository<T, ID> repository;

    public GenericService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    /**
     * Saves a new entity in the database and returns it.
     * 
     * @param entity the entity to be saved
     * @return the saved entity
     */
    @SuppressWarnings("null")
    @Override
    public T create(T entity) {
        T createdEntity = repository.save(entity);
        log.info("create:: entry created");
        return createdEntity;
    }

    /**
     * Returns a list of all entities in the database.
     *
     * @return a list of all entities in the database
     */
    @Override
    public List<T> getAll() {
        List<T> entities = repository.findAll();
        log.info("getAll:: retrieved all entries");
        return entities;
    }

    /**
     * Returns an entity by its ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the specified ID, or null if no entity with the
     *         specified ID exists
     */
    @SuppressWarnings("null")
    public T getById(ID id) {
        Optional<T> entryOptional = repository.findById(id);

        return entryOptional.map(entry -> {
            log.info("getById:: retrieved entry with id: {}", id);
            return entry;
        }).orElseGet(() -> {
            log.error("getById:: could not retrieve entry with id: {}", id);
            return null;
        });
    }

    /**
     * Updates an existing entity in the database and returns the updated entity.
     *
     * @param id            the ID of the entity to be updated
     * @param updatedEntity the updated entity with the new values
     * @return the updated entity
     */
    @SuppressWarnings("null")
    @Override
    public T update(ID id, T updatedEntity) {
        Optional<T> entryOptional = repository.findById(id);

        return entryOptional.map(existingEntry -> {
            // copy the updated properties to the existing entity
            copyProperties(updatedEntity, existingEntry);
            // save the updated entity
            T savedEntity = repository.save(existingEntry);
            // log the update
            log.info("update:: entry with id: {} updated successfully", id);
            return savedEntity;
        }).orElseGet(() -> {
            // log that the update failed
            log.error("update:: could not update entry with id: {}", id);
            return null;
        });
        // T existingEntity = repository
        // .findById(id)
        // .orElseThrow(() -> new GenericNotFoundEx("Entry with id " + id + " not found
        // for update"));
        // copyProperties(updatedEntity, existingEntity);
        // T savedEntity = repository.save(existingEntity);
        // log.info("Entity updated successfully: {}", savedEntity);
        // return savedEntity;
    }

    /**
     * Copies the properties of the given source object to the given target object.
     *
     * @param source the source object
     * @param target the target object
     */
    @SuppressWarnings("null")
    private void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * Deletes an entity from the database by its ID.
     *
     * @param id the ID of the entity to be deleted
     */
    @SuppressWarnings("null")
    @Override
    public void delete(ID id) {
        repository.deleteById(id);
        log.info("delete:: successfully deleted entry with id: {}", id);
    }

    /**
     * Returns the total number of entities in the database.
     *
     * @return the total number of entities in the database
     * @throws GenericNotFoundEx if an error occurs while counting the entries
     */
    @Override
    public Long count() {
        try {
            Long count = repository.count();
            log.info("count:: {} entries retrieved", count);
            return count;
        } catch (Exception ex) {
            log.error("count:: error counting entries: {}", ex.getMessage());
            throw new GenericNotFoundEx("count:: error counting entries", ex);
        }
    }

    /**
     * Returns a page of entries from the database, starting from the specified page
     * index and with the specified page size.
     * 
     * @param pageIndex the index of the first entry in the page (zero-based)
     * @param pageSize  the number of entries in the page
     * @return a page of entries, or null if no entries are present
     * @throws GenericNotFoundEx if an error occurs while retrieving the entries
     */
    public List<T> getPage(int pageIndex, int pageSize) {
        List<T> entries = repository.findAll();
        int entrySize = entries.size();
        int left = pageIndex * pageSize;
        int right = left + pageSize;

        if (entrySize == 0) {
            log.warn("getPage:: no entries present");
            return null;
        } else if (entrySize < left) {
            log.error("getPage:: initial index is out of bounds");
            return null;
        }

        if (entrySize < right) {
            right = entrySize;
        }

        List<T> result = entries.subList(left, right);
        log.info("getPage:: retrieved page with index: {} and size: {}", pageIndex, pageSize);
        return result;
    }
}
