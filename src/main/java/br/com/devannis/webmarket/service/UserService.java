package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.UserNotFoundException;
import br.com.devannis.webmarket.model.dto.UserExhibitionDTO;
import br.com.devannis.webmarket.model.dto.UserRegistrationDTO;
import br.com.devannis.webmarket.model.entity.User;
import br.com.devannis.webmarket.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public UserExhibitionDTO registerUser(UserRegistrationDTO userRegistrationDTO) {

        String passwordEncryptor = new BCryptPasswordEncoder().encode(userRegistrationDTO.password());

        User user = new User();
        BeanUtils.copyProperties(userRegistrationDTO, user);
        user.setPassword(passwordEncryptor);

        return new UserExhibitionDTO(userRepository.save(user));
    }

    public UserExhibitionDTO getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return new UserExhibitionDTO(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public List<UserExhibitionDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserExhibitionDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public User updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());

        if (userOptional.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
