package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.UserNotFoundException;
import br.com.devannis.webmarket.model.dto.UserResponseDTO;
import br.com.devannis.webmarket.model.dto.UserRequestDTO;
import br.com.devannis.webmarket.model.entity.User;
import br.com.devannis.webmarket.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponseDTO saveUser(UserRequestDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser);
    }

    public UserResponseDTO searchByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User do not exist"));

        return new UserResponseDTO(user);
    }

    public List<UserResponseDTO> listAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User do not exist"));

        userRepository.delete(user);
    }

    @Transactional
    public UserResponseDTO updateUser(User user) {
        userRepository.findById(user.getUserId()).orElseThrow(() -> new UserNotFoundException("User do not exist!"));

        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}