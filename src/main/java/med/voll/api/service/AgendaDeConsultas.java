package med.voll.api.service;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        var consulta = new Consulta(null, medico, paciente, dados.data());
        //return repository.save(consulta);
        return null;
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

}
