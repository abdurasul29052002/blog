package com.example.blog.mapper.template;

import org.mapstruct.*;

import java.util.List;

public interface GenericMapper<E,M> {

    M entityToModel(E entity);

    E modelToEntity(M model);

    List<M> entitiesToModels(List<E> entities);

    List<E> modelsToEntities(List<M> models);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromModel(@MappingTarget E entity, M model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromEntity(E entity, @MappingTarget M model);
}
