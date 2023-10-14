package br.com.kauasilveira.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired //Gerencia a instanciação
    private IUserRepository userRepository;

    // @RequestBody -> pega os dados no body
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        UserModel findUser = this.userRepository.findByUsername(userModel.getUsername());

        if (findUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe!!");
        }

        userModel.setPassword(BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()));

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userRepository.save(userModel));

    }

}
