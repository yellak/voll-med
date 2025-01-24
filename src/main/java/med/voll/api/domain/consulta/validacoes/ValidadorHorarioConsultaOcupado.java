package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorHorarioConsultaOcupado implements ValidadorAgendamentoConsultas {

    @Autowired
    ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }
        var existeConsultaNoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (existeConsultaNoHorario) {
            throw new ValidacaoException("Já existe uma consulta marcada neste horário com este médico.");
        }
    }

}
