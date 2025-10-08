package dev.sn.service.interfaces;


import dev.sn.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(UserDto userDto);
    List<UserDto> findAll();
    Optional<UserDto> findById(long menuId);
    UserDto update(long userId, UserDto userDto);
    String deleteById(long userId);
    String deleteAll();
}
