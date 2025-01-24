package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorTempoAntecedencia implements ValidadorAgendamentoConsultas {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var horarioAgora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(horarioAgora, dados.data()).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("A consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }

}
