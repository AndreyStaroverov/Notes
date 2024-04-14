package notes.severstal.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private Long id;
    @NotBlank
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 250, message = "{validation.name.size.too_long}")
    private String name;
    @NotBlank
    @Size(min = 6, message = "{validation.name.size.too_short}")
    @Size(max = 254, message = "{validation.name.size.too_long}")
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, message = "{validation.name.size.too_short}")
    @Size(max = 25, message = "{validation.name.size.too_long}")
    private String password;
    private Timestamp createdAt;
}
