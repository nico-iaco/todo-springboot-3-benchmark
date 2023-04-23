package it.iacovelli.todospringboot3benchmark.mapper;

import it.iacovelli.todospringboot3benchmark.model.Todo;
import it.iacovelli.todospringboot3benchmark.model.dto.TodoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    @Mapping(target = "id", ignore = true)
    Todo fromDtoToEntity(TodoDto dto);

    TodoDto fromEntityToDto(Todo entity);

    @Mapping(target = "id", ignore = true)
    Todo updateEntityFromDto(TodoDto dto, @MappingTarget Todo entity);

}
