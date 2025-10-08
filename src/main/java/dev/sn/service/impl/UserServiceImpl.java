package dev.sn.service.impl;

import dev.sn.dtos.MenuDto;
import dev.sn.dtos.UserDto;
import dev.sn.entities.Menu;
import dev.sn.entities.User;
import dev.sn.mappers.UserMapper;
import dev.sn.repositories.UserRepository;
import dev.sn.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private UserDto save(UserDto userDto){
        User user = userMapper.toUser(userDto);
        User userSaved = userRepository.save(user);
        return userMapper.toUserDto(userSaved);
    }


    @Override
    public UserDto create(UserDto userDto) {
        return save(userDto);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDto).
                collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> UserDto.builder()
                .id(user.getId())
                .prenom(user.getPrenom())
                .nom(user.getNom())
                .email(user.getEmail())
                .build());
    }

    @Override
    public UserDto update(long userId, UserDto userDto) {
        Optional<UserDto> userDtoOptional = findById(userId);
        if (userDtoOptional.isPresent()){
            UserDto newUserdto = userDtoOptional.get();
            if (userDto.getNom()!=null){
                newUserdto.setNom(userDto.getNom());
            }
            if (userDto.getPrenom()!=null){
                newUserdto.setPrenom(userDto.getPrenom());
            }
            if (userDto.getEmail()!=null){
                newUserdto.setEmail(userDto.getEmail());
            }
            return save(newUserdto);
        }
        return null;
    }

    @Override
    public String deleteById(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User non trouvé avec id: " + userId);
        }
        userRepository.deleteById(userId);
        return "Le User a été supprimé avec succès !";

    }

    @Override
    public String deleteAll() {
        userRepository.deleteAll();
        return "La liste des users a été supprimée avec succès !";
    }
}
