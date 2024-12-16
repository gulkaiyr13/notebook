package notebook.mapper;

import javax.annotation.processing.Generated;
import notebook.entity.NoteEntity;
import notebook.model.NoteDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T01:32:56+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteEntity toEntity(NoteDTO noteDto) {
        if ( noteDto == null ) {
            return null;
        }

        NoteEntity noteEntity = new NoteEntity();

        noteEntity.setImageUrl( noteDto.getImageUrl() );
        noteEntity.setId( noteDto.getId() );
        noteEntity.setDate( noteDto.getDate() );
        noteEntity.setNote( noteDto.getNote() );

        return noteEntity;
    }

    @Override
    public NoteDTO toDto(NoteEntity noteEntity) {
        if ( noteEntity == null ) {
            return null;
        }

        NoteDTO noteDTO = new NoteDTO();

        noteDTO.setImageUrl( noteEntity.getImageUrl() );
        noteDTO.setId( noteEntity.getId() );
        noteDTO.setNote( noteEntity.getNote() );
        noteDTO.setDate( noteEntity.getDate() );

        return noteDTO;
    }
}
