package com.bht.pim.mapper;

import lombok.extern.log4j.Log4j;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

@Log4j
@Component
@Mapper(uses = DateTimeMapper.class, componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class CustomizedMapper {

}
