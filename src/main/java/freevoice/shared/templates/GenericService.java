package freevoice.shared.templates;

import freevoice.shared.templates.exceptions.GenericNotFoundEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GenericService<T, ID> implements IGenericService<T, ID> {

    private final GenericRepository<T, ID> repository;

    public GenericService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        T createdEntity = repository.save(entity);
        log.info("Entity created successfully: {}", createdEntity);
        return createdEntity;
    }

    @Override
    public List<T> getAll() {
        List<T> entities = repository.findAll();
        log.info("Retrieved all entities: {}", entities);
        return entities;
    }

    @Override
    public T getById(ID id) {
        T entity = repository
                .findById(id)
                .orElseThrow(() -> new GenericNotFoundEx("Entry with id " + id + " not found"));
        log.info("Retrieved entity by ID {}: {}", id, entity);
        return entity;
    }

    @Override
    public T update(ID id, T updatedEntity) {
        // Find the existing entity by ID
        T existingEntity = repository.findById(id)
                                     .orElseThrow(() -> new GenericNotFoundEx("Entry with id " + id + " not found for update"));

        // Copy the properties from the updatedEntity to the existingEntity
        // Assuming you have a utility method or library to perform the copy
        copyProperties(updatedEntity, existingEntity);

        // Save the updated entity
        T savedEntity = repository.save(existingEntity);

        log.info("Entity updated successfully: {}", savedEntity);
        return savedEntity;
    }

    // Utility method to copy properties from one object to another
    private void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
        log.info("Entity deleted successfully with ID: {}", id);
    }

    @Override
    public Long count() {
        try {
            Long count = repository.count();
            log.info("Retrieved number entities available: {}", count);
            return count;
        } catch (Exception ex) {
            log.error("Error counting entities: {}", ex.getMessage());
            throw new GenericNotFoundEx("Error counting entities", ex);
        }
    }

    public List<T> getPage(int pageIndex, int pageSize) {
        List<T> entries = repository.findAll();
        int entrySize = entries.size();
        int left = pageIndex * pageSize;
        int right = left + pageSize;

        if (entrySize == 0) {
            log.warn("No entries present");
            return null;
        } else if (entrySize < left) {
            log.warn("Initial index is out of bounds");
            return null;
        }

        if (entrySize < right) {
            right = entrySize;
        }

        List<T> result = entries.subList(left, right);
        log.info("Retrieved page ({} - {}) of entities: {}", left, right, result);
        return result;
    }
}

