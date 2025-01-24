package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaNoMesmoDia implements ValidadorAgendamentoConsultas {

    @Autowired
    private ConsultaRepository repository;
    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorarioDia = dados.data().withHour(7);
        var ultimoHorarioDia = dados.data().withHour(18);
        var existeConsultaNoHorario = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorarioDia, ultimoHorarioDia);
        if (existeConsultaNoHorario) {
            throw new ValidacaoException("Não é possível marcar duas consultas no mesmo dia e o paciente já possui uma consulta marcada no dia.");
        }
    }
}
