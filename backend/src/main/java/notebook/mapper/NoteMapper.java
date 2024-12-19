package notebook.mapper;

import notebook.entity.NoteEntity;
import notebook.model.NoteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(source = "imageUrl", target = "imageUrl")
    NoteEntity toEntity(NoteDTO noteDto);

    @Mapping(source = "imageUrl", target = "imageUrl")
    NoteDTO toDto(NoteEntity noteEntity);
}
