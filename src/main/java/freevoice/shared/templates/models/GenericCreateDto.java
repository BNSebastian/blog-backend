package freevoice.shared.templates.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericCreateDto<T, ID> {
    private ID id;

    private String createdOn;

    private String modifiedOn;
}
