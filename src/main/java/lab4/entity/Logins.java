package lab4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Logins {
    @Id
    public long id;
    public LocalDateTime accessdate;
    public String application;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "userid", nullable = false)
    public Users userid;

    @Override
    public String toString() {
        return "Logins{" +
                "id=" + id +
                ", accessdate=" + accessdate +
                ", application='" + application + '\'' +
                ", users=" + userid +
                '}';
    }
}
