package application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Tarefa;
import application.repositories.TarefaRepository;

@RestController
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @PostMapping("/tarefas")
    public Tarefa post(@RequestBody Tarefa tarefa){
        if(tarefa.getDescricao() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O valor do campo descricao não pode ser nulo");
        }
        return tarefaRepo.save(tarefa);
    }

    @GetMapping("/tarefas")
    public List<Tarefa> getAll(){
        return (List<Tarefa>) tarefaRepo.findAll();
    }

    @GetMapping("tarefas/{id}")
    public Tarefa getOne(@PathVariable Long id){
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        if(resultado.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        return tarefaRepo.findById(id).get();
    }

    @PutMapping("tarefas/{id}")
    public Tarefa put(@RequestBody Tarefa tarefa, @PathVariable Long id){
        Tarefa resposta = tarefaRepo.findById(id).get();
        resposta.setDescricao(tarefa.getDescricao());
        resposta.setConcluido(tarefa.getConcluido());

        return tarefaRepo.save(resposta);
    }

    @PatchMapping("tarefas/{id}")
    public Tarefa patch(@RequestBody Tarefa tarefa, @PathVariable Long id){
        Tarefa resposta = tarefaRepo.findById(id).get();
        if(tarefa.getDescricao() != null){
            resposta.setDescricao(tarefa.getDescricao());
        }
        resposta.setConcluido(tarefa.getConcluido());
        return tarefaRepo.save(resposta);
    }

    @DeleteMapping("tarefas/{id}")
    public void delete(@PathVariable Long id){
        tarefaRepo.deleteById(id);
    }
}
