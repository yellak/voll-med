package med.voll.api.service;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validarAgendamento(Consulta consulta) {

    }

    public Medico obterMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return medicoAleatorio();
        }
        return medicoRepository.findById(dados.idMedico()).orElseThrow(() -> new ValidacaoException("Id do Médico informado não existe"));
    }

    private Medico medicoAleatorio() {
        return new Medico();
    }

}
