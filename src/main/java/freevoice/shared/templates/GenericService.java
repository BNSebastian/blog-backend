package freevoice.shared.templates;

import freevoice.shared.templates.exceptions.GenericNotFoundEx;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenericService<T, ID> implements IGenericService<T, ID> {

    private final GenericRepository<T, ID> repository;

    public GenericService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(ID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new GenericNotFoundEx("entry with id " + id + " not found"));
    }

    @Override
    public T update(ID id, T updatedEntity) {
        if (repository.existsById(id)) {
            return repository.save(updatedEntity);
        }
        return null; // Handle not found scenario
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
