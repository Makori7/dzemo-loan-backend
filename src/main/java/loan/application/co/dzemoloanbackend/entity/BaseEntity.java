package loan.application.co.dzemoloanbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "created_on",updatable = false)
    public LocalDateTime createdOn;
    @Column(name = "softDelete", columnDefinition = "int(1) default 0")
    public boolean softDelete;

    @PrePersist
    public void addData() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZoneId zoneId = ZoneId.of("Africa/Nairobi");
        ZonedDateTime Nairobi = zonedDateTime.withZoneSameInstant(zoneId);
        this.createdOn = Nairobi.toLocalDateTime();
        this.softDelete = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
