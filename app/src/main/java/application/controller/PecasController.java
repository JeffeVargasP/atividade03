package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Pecas;
import application.repository.PecasRepository;

@RestController
@RequestMapping("/pecas")
public class PecasController {
    @Autowired
    private PecasRepository pecasRepo;

    @GetMapping
    public Iterable<Pecas> getAll() {
        return pecasRepo.findAll();
    }

    @PostMapping
    public Pecas post(@RequestBody Pecas pecas) {
        return pecasRepo.save(pecas);
    }

    @GetMapping("/{id}")
    public Pecas getOne(@PathVariable long id) {
        Optional<Pecas> resultado = pecasRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Peça não encontrada"
            );
        }
        return resultado.get();
    }

    @PutMapping("/{id}")
    public Pecas put(@PathVariable long id, @RequestBody Pecas novosDados){
        Optional<Pecas> resultado = pecasRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Peça não encontrada"
            );
        }

        resultado.get().setTitulo(novosDados.getTitulo());
        resultado.get().setPreco(novosDados.getPreco());

        return pecasRepo.save(resultado.get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!pecasRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Peça não encontrada"
            );
        }

        pecasRepo.deleteById(id);
    }
}