package br.com.kauasilveira.todolist.task;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        taskModel.setIdUser((UUID) request.getAttribute("idUser"));

        var currentDate = LocalDateTime.now();
        if ((currentDate.isAfter(taskModel.getDataDeInicio()) || currentDate.isAfter(taskModel.getDataDeTermino()))) {
            return ResponseEntity.status(400).body("A data de início/termino deve ser posterior à atual!");
        }

        if (taskModel.getDataDeInicio().isAfter(taskModel.getDataDeTermino())) {
            return ResponseEntity.status(400).body("A data de início deve ser menor que a de termino!");
        }

        var task = this.iTaskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}
