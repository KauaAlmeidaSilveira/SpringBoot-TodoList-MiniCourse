package br.com.kauasilveira.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel){
        /*
        if(this.iTaskRepository.existsById(taskModel.getIdUser())){
            this.iTaskRepository.save(taskModel);
            return ResponseEntity.ok().body(taskModel);
        }else {
            return ResponseEntity.status(400).body("Usuario não existe");
        }
        *
        */

        return this.iTaskRepository.save(taskModel);

    }
}
