package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    private boolean cancelada;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_cancelamento")
    private MotivoCancelamentoConsulta motivoCancelamento;

    public Long getIdMedico() {
        if (medico != null) {
            return getMedico().getId();
        }
        return null;
    }

    public Long getIdPaciente() {
        if (paciente != null) {
            return getPaciente().getId();
        }
        return null;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public void setMotivoCancelamento(MotivoCancelamentoConsulta motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}
