package freevoice.shared.templates;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T, ID> {
    T create(T entity);
    List<T> getAll();
    T getById(ID id);
    T update(ID id, T updatedEntity);
    void delete(ID id);
    Long count();
    List<T> getPage(int pageIndex, int pageSize);
}