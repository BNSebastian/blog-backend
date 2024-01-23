package freevoice.shared.templates;

import java.util.List;

public interface IDefaultService<T> {
    T create(T entry);
    T getById(Long id);
    List<T> getAll();
    T update(T entry);
    void delete(T entry);
}
