package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.UserExhibitionDTO;
import br.com.devannis.webmarket.model.dto.UserRegistrationDTO;
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
    public UserExhibitionDTO registerUser(@RequestBody @Valid UserRegistrationDTO user) {
        return userService.registerUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserExhibitionDTO> listAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserExhibitionDTO> searchById(@PathVariable Long usuarioId){
        return ResponseEntity.ok(userService.getUserById(usuarioId));
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        userService.deleteUserById(usuarioId);
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public User atualizar(@RequestBody User usuario){
        return userService.updateUser(usuario);
    }
}
