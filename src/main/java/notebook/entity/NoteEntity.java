package notebook.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "NOTES")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "NOTES_SEQUENCE", sequenceName = "NOTES_SEQ")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @ToString.Exclude
    private String note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "image_url")
    private String imageUrl;
    private boolean isFavorite;

}
