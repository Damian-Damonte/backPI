package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.user.UserFullDto;
import com.dh.digitalbooking.dto.user.UserResponse;
import com.dh.digitalbooking.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserResponse userToUserResponse(User user);
    UserFullDto userToUserFullDto(User user);
}
