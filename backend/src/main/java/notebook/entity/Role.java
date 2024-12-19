package notebook.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Entity
@Table(name = "USER_ROLES")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "USER_ROLE_SEQUENCE", sequenceName = "USER_ROLE_SEQ")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLE_SEQUENCE")
    private Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @OneToMany(mappedBy = "role")
    Set<UserEntity> user;
}