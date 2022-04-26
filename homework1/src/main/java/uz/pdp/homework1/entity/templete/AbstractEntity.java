package uz.pdp.homework1.entity.templete;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.pdp.homework1.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapKeyColumn(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @MapKeyColumn(nullable = false, updatable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;


    /**
     * @JoinColumn  qachonki fieldlar yaratilgan class tipida bolsa ishlatilinadi
     */
    @JoinColumn(updatable = false)
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;


    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
}
