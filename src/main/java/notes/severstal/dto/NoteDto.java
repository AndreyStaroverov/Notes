package notes.severstal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    @Positive
    private Long id;
    @NotBlank
    private String text;
    @Positive
    private Long owner;
    private Boolean pinned;
    private Timestamp createDate;
}
