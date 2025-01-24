package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.service.AgendaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consultas")
public class ConsultasController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var consulta = agenda.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
