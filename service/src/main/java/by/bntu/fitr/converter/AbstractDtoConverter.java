package by.bntu.fitr.converter;

import by.bntu.fitr.model.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public abstract class AbstractDtoConverter<E extends BaseEntity, DTO> {

    protected ModelMapper modelMapper;

    private final Class<E> entityClass;
    private final Class<DTO> dtoClass;

    public AbstractDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.dtoClass = (Class<DTO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public DTO convertToDto(E entity){
        return modelMapper.map(entity, this.dtoClass);
    }

    public E convertFromDto(DTO dto){
        return modelMapper.map(dto, this.entityClass);
    }

    public Set<DTO> convertToDtoSet(Set<E> set){
        return set.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    public Set<DTO> convertToDtoSet(List<E> list){
        return list.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    public Page<DTO> convertToDtoPage(Page<E> page){
        return page.map(this::convertToDto);
    }

//    public abstract Page<DTO> convertPageToDto(Page<E> page);
//
//    public Pageable convertPageableFromDto(PageableDto pageableDto) {
//        return modelMapper.map(pageableDto, Pageable.class);
//    }
//
//    protected void setPageableAndTotalCountOfItems(Page<?> pageDto, Page<? extends BaseEntity> page) {
//        pageDto.setPageable(page.getPageable());
//        pageDto.setTotalCountOfItems(page.getTotalCountOfItems());
//    }
}
