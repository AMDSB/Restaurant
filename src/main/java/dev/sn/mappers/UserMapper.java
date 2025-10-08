package dev.sn.mappers;


import dev.sn.dtos.UserDto;
import dev.sn.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);

}
