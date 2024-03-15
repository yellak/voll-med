package med.voll.api.service;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AgendaDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository repository;

    public Consulta agendar(DadosAgendamentoConsulta dados) {
        var medico = obterMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).orElseThrow(() -> new ValidacaoException("Id do paciente informado não existe."));
        var consulta = new Consulta(null, medico, paciente, dados.data(), false, null);
        return repository.save(consulta);
    }

    public Medico obterMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return medicoAleatorio(dados);
        }
        return medicoRepository.findById(dados.idMedico()).orElseThrow(() -> new ValidacaoException("Id do Médico informado não existe"));
    }

    private Medico medicoAleatorio(DadosAgendamentoConsulta dados) {
        if (dados.especialidade() == null) {
            throw new ValidacaoException("A especialidade é obrigatória quando não se escolhe o médico.");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public Consulta cancelar(DadosCancelamentoConsulta dados) {
        var consulta = repository.findByMedicoAndPacienteAndData(new Medico(dados.idMedico()), new Paciente(dados.idPaciente()), dados.data());
        if (!podeCancelar(consulta)) {
            throw new ValidacaoException("Só é possível cancelar uma consulta com pelo menos 24h de antecedência.");
        }
        consulta.setCancelada(true);
        consulta.setMotivoCancelamento(dados.motivoCancelamento());
        repository.save(consulta);
        return consulta;
    }

    private boolean podeCancelar(Consulta consulta) {
        var firstDate = new Date();
        var secondDate = java.sql.Timestamp.valueOf(consulta.getData());
        long timeDifference = secondDate.getTime() - firstDate.getTime();
        long hoursDifference = timeDifference / (60 * 60 * 1000); // Convert milliseconds to hours
        return hoursDifference >= 24;
    }
}
