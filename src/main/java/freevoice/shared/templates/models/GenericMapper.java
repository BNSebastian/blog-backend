package freevoice.shared.templates.models;

public class GenericMapper<T, R, C, ID> {
    private final GenericEntity<T, ID> entity;
    private final GenericCreateDto<T, ID> createDto;
    private final GenericReadDto<T, ID> readDto;

    public GenericMapper(GenericEntity<T, ID> entity, GenericCreateDto<T, ID> createDto,
            GenericReadDto<T, ID> readDto) {
        this.entity = entity;
        this.createDto = createDto;
        this.readDto = readDto;
    }

    public R mapEntityToReadDto(T t) {
        return null;
    }
}
