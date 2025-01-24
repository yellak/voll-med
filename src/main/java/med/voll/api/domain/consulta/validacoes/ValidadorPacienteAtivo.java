package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsultas {

    @Autowired
    PacienteRepository repository;
    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteAtivo = repository.ativoById(dados.idPaciente());

        if (!pacienteAtivo) {
            throw new ValidacaoException("O paciente está desativado.");
        }
    }
}
