package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.UserNotFoundException;
import br.com.devannis.webmarket.model.dto.UserExhibitionDTO;
import br.com.devannis.webmarket.model.dto.UserRegisterDTO;
import br.com.devannis.webmarket.model.entity.User;
import br.com.devannis.webmarket.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserExhibitionDTO saveUser(UserRegisterDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        User savedUser = userRepository.save(user);

        return new UserExhibitionDTO(savedUser);
    }

    public UserExhibitionDTO searchById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return new UserExhibitionDTO(userOptional.get());
        } else {
            throw new UserNotFoundException("User do not exist!");
        }
    }

    public List<UserExhibitionDTO> listAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserExhibitionDTO::new)
                .toList();
    }

    public void excludeUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new UserNotFoundException("User do not exist!");
        }
    }

    public User updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());

        if (userOptional.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User do not exist!");
        }
    }
}