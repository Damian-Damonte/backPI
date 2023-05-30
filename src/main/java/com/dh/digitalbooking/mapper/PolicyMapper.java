package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.policy.PolicyFullDto;
import com.dh.digitalbooking.entity.Policy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PolicyMapper {
    PolicyFullDto policyToPolictyFullDto(Policy policy);
}
