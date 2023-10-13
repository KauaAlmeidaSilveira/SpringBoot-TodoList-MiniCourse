package br.com.kauasilveira.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    // @RequestBody -> pega os dados no body
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel){
        System.out.println(userModel.name);
    }

}
