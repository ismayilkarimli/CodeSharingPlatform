package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Code {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private UUID id;

    @JsonProperty(access = Access.READ_ONLY)
    @Setter(AccessLevel.NONE)
    private String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

    @JsonProperty(required = true)
    private String code;

    @JsonProperty(value = "views", required = true)
    @Column(name = "views")
    private Integer viewsLeft;

    @JsonProperty(value = "time", required = true)
    @Column(name = "time")
    private Long timeLeft;

    @JsonIgnore
    private boolean restricted;

    @JsonIgnore
    private boolean viewRestricted;

    @JsonIgnore
    private boolean timeRestricted;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Long creationTimestamp = System.currentTimeMillis();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Code code = (Code) o;

        return Objects.equals(id, code.id);
    }

    @Override
    public int hashCode() {
        return 362552861;
    }
}
