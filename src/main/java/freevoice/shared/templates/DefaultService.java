package freevoice.shared.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultService<T> implements IDefaultService<T> {

    @Autowired
    private DefaultRepository<T> defaultRepository;

    @Override
    public T create(T entry) {
        try {
            return defaultRepository.save(entry);
        } catch (Error e) {
            throw new RuntimeException();
        }
    }

    @Override
    public T getById(Long id) {
        try {
            return defaultRepository.getReferenceById(id);
        } catch (Error e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<T> getAll() {
        try {
            return defaultRepository.findAll();
        } catch (Error e) {
            throw new RuntimeException();
        }
    }

    @Override
    public T update(T entry) {
        try {
            return defaultRepository.save(entry);
        } catch (Error e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(T entry) {
        try {
            defaultRepository.delete(entry);
        } catch (Error e) {
            throw new RuntimeException();
        }
    }
}
