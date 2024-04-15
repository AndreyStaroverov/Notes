package notes.severstal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "text", nullable = false)
    private String text;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(name = "pinned", nullable = false)
    private Boolean pinned;
    @Column(name = "createDate", nullable = false)
    private Timestamp createDate;
    @Column(name = "image_link")
    private String imageLink;
}
