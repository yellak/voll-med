package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.DadosPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    public void cadastrarPaciente(@RequestBody @Valid DadosPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(sort = {"nome"}, size = 10) Pageable page) {
        return repository.findAll(page).map(DadosListagemPaciente::new);
    }

}
