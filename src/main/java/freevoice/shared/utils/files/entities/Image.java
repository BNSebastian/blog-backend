package freevoice.shared.utils.files.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long userId;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] file;

    public Image(byte[] file, String name) {
        this.file = file;
        this.name = name;
    }
}
