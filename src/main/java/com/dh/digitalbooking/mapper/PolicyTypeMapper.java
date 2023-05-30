package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDto;
import com.dh.digitalbooking.entity.PolicyType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PolicyTypeMapper {
    PolicyTypeFullDto policytypeToPolicyTypeFullDto(PolicyType policyType);
}
