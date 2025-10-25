package dev.sn.web;

import dev.sn.dtos.UserDto;
import dev.sn.mappers.UserMapper;
import dev.sn.repositories.UserRepository;
import dev.sn.service.interfaces.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {
    final private UserService userService;

    public UserController(UserService userService,
                          UserRepository userRepository,
                          UserMapper userMapper) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto){
        return userService.create(userDto);
    }

    @GetMapping
    public List<UserDto> findAll(){
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable long id, @RequestBody UserDto userDto){
        return userService.update(id, userDto);
    }

    @GetMapping("/{id}")
    public Optional<UserDto> findById(@PathVariable long id){
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id){
        return userService.deleteById(id);
    }
}
