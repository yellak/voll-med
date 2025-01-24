package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var foraDoHorarioFuncionamentoClinica = dataConsulta.getHour() < 7 || dataConsulta.getHour() > 18;
        if (domingo || foraDoHorarioFuncionamentoClinica) {
            throw new ValidacaoException("Consulta fora do período de funcionamento da clínica");
        }
    }

}
