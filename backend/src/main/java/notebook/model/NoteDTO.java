package notebook.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoteDTO {
    private Long id;
    private String note;
    private LocalDateTime date;
    private String imageUrl;
    private Long userId;
}
