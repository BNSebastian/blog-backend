package freevoice.shared.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GenericController<T, ID> {

    private final GenericService<T, ID> genericService;

    @Autowired
    public GenericController(GenericService<T, ID> genericService) {
        this.genericService = genericService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = genericService.getAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        T entity = genericService.getById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/create")
    public ResponseEntity<T> create(@RequestBody T entity) {
        T createdEntity = genericService.create(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        T updatedEntity = genericService.update(id, entity);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        genericService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
