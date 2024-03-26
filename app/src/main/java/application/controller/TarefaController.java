package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Tarefa;
import application.repositories.TarefaRepository;

@RestController
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @PostMapping("/tarefas")
    public Tarefa post(@RequestBody Tarefa tarefa){
        return tarefaRepo.save(tarefa);
    }

    @GetMapping("/tarefas")
    public List<Tarefa> getAll(){
        return (List<Tarefa>) tarefaRepo.findAll();
    }

    @GetMapping("tarefas/{id}")
    public Tarefa getOne(@PathVariable Long id){
        return tarefaRepo.findById(id).get();
    }

    @PutMapping("tarefas/{id}")
    public Tarefa put(@RequestBody Tarefa tarefa, @PathVariable Long id){
        Tarefa resposta = tarefaRepo.findById(id).get();
        resposta.setDescricao(tarefa.getDescricao());
        resposta.setConcluido(true);
        resposta.setConcluido(false);

        return tarefaRepo.save(resposta);
    }

    @DeleteMapping("tarefas/{id}")
    public void delete(@PathVariable Long id){
        tarefaRepo.deleteById(id);
    }
}
