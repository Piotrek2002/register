package com.register.register.dto;

import com.register.register.entity.Class;
import com.register.register.entity.ClassDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassMapper {
    ClassMapper INSTANCE = Mappers.getMapper(ClassMapper.class);

    @Mapping(source = "teacher.id", target = "teacherId")
    ClassDTO classToClassDTO(Class aClass);

}
