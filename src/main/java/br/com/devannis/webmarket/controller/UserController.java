package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.UserExhibitionDTO;
import br.com.devannis.webmarket.model.dto.UserRegisterDTO;
import br.com.devannis.webmarket.model.entity.User;
import br.com.devannis.webmarket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserExhibitionDTO save(@RequestBody @Valid UserRegisterDTO user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserExhibitionDTO> listAll() {
        return userService.listAllUsers();
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserExhibitionDTO> searchById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.searchById(userId));
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclude(@PathVariable Long userId) {
        userService.excludeUser(userId);
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
