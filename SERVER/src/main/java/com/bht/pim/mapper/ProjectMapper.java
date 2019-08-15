package com.bht.pim.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = CustomizedMapper.class)
public interface ProjectMapper {

}
