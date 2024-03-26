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

import application.model.Compromisso;
import application.repositories.CompromissoRepository;

@RestController
public class CompromissoController {
    @Autowired
    private CompromissoRepository compromissoRepo;
    
    @PostMapping("/compromissos")
    public Compromisso post(@RequestBody Compromisso compromisso){
        return compromissoRepo.save(compromisso);
    }

    @GetMapping("/compromissos")
    public List<Compromisso> getAll(){
        return (List<Compromisso>) compromissoRepo.findAll();
    }

    @GetMapping("/compromissos/{id}")
    public Compromisso getOne(@PathVariable Long id){
        return compromissoRepo.findById(id).get();
    }

    @PutMapping("/compromissos/{id}")
    public Compromisso put(@RequestBody Compromisso compromisso, @PathVariable Long id){
        Compromisso resposta = compromissoRepo.findById(id).get();
        resposta.setDescricao(compromisso.getDescricao());
        resposta.setDataInicio(compromisso.getDataInicio());
        resposta.setDataFim(compromisso.getDataFim());
        resposta.setHoraInicio(compromisso.getHoraInicio());
        resposta.setHoraFim(compromisso.getHoraFim());

        return compromissoRepo.save(resposta);
    }

    @DeleteMapping("compromissos/{id}")
    public void delete(@PathVariable Long id){
        compromissoRepo.deleteById(id);
    }
}
