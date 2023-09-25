package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(

        Long idMedico,

        Long idPaciente,

        LocalDateTime data,

        Especialidade especialidade

) {
}
