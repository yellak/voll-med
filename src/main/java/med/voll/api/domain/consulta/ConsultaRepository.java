package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Consulta findByMedicoAndPacienteAndData(@NotNull Medico medico, @NotNull Paciente paciente, @NotNull LocalDateTime data);

    Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

}
