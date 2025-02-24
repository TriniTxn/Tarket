package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.UserResponseDTO;
import br.com.devannis.webmarket.model.dto.UserRequestDTO;
import br.com.devannis.webmarket.model.entity.User;
import br.com.devannis.webmarket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO user) {
        return userService.saveUser(user);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> listAll() {
        return userService.listAllUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> searchById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.searchByUserId(userId));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclude(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
