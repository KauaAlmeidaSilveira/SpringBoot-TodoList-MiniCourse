package br.com.kauasilveira.todolist.task;

import br.com.kauasilveira.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public ResponseEntity createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        taskModel.setIdUser((UUID) request.getAttribute("idUser"));

        var currentDate = LocalDateTime.now();
        if ((currentDate.isAfter(taskModel.getDataDeInicio()) || currentDate.isAfter(taskModel.getDataDeTermino()))) {
            return ResponseEntity.status(400).body("A data de início/termino deve ser posterior à atual!");
        }

        if (taskModel.getDataDeInicio().isAfter(taskModel.getDataDeTermino())) {
            return ResponseEntity.status(400).body("A data de início deve ser menor que a de termino!");
        }

        var task = this.iTaskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> ListTasks (HttpServletRequest request){
        return this.iTaskRepository.findAllByIdUser((UUID) request.getAttribute("idUser"));
    }

    @PutMapping("/{idTask}")
    public ResponseEntity updateTask(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID idTask){

        var task = this.iTaskRepository.findById(idTask).orElse(null);

        if(task == null){
            return ResponseEntity.status(400).body("Tarefa não encontrada!");
        }

        if(!task.getIdUser().equals(request.getAttribute("idUser"))){
            return ResponseEntity.status(400).body("Usuario não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(taskModel, task);

        return ResponseEntity.status(201).body(this.iTaskRepository.save(task));
    }
}
