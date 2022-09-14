package com.example.blog.service.template;

import com.example.blog.mapper.template.GenericMapper;
import com.example.blog.payload.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.PageFormat;
import java.util.List;

@RequiredArgsConstructor
public abstract class GenericService<E, M, R extends JpaRepository<E, Long>, MAPPER extends GenericMapper<E, M>> {

    final protected MAPPER mapper;

    final protected R repository;

    public M save(M model){
        E savedEntity = repository.save(mapper.modelToEntity(model));
        return mapper.entityToModel(savedEntity);
    }

    public M findById(Long id) {
        E entity = repository.findById(id).orElseThrow();
        return mapper.entityToModel(entity);
    }

    public List<M> findAll(Integer page, Integer size){
        List<E> list = repository.findAll(PageRequest.of(page, size)).getContent();
        return mapper.entitiesToModels(list);
    }

    public M update(M model, Long id){
        E entity = repository.findById(id).orElseThrow();
        mapper.updateEntityFromModel(entity,model);
        E save = repository.save(entity);
        return mapper.entityToModel(save);
    }

    public ApiResponse deleteById(Long id){
        try{
            repository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

}

