package freevoice.shared.templates.models;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericEntity<T, ID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    private LocalDateTime createdOn;

    private LocalDateTime modifiedOn;
}
