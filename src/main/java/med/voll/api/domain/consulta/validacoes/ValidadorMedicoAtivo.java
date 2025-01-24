package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsultas {

    @Autowired
    MedicoRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoAtivo = repository.ativoById(dados.idMedico());
        if (!medicoAtivo) {
            throw new ValidacaoException("O médico agendado não está mais ativo na clínica.");
        }
    }

}
