package server.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


/**
 * Сущность Облигаций
 * <p>
 * Поля:
 * <p>
 * id - уникальный идентификатор
 * <p>
 * bondName - название облигации
 * <p>
 * coupon_payment_date - даты выплат купонов
 * <p>
 * nominal - номинал
 * <p>
 * coupon_size - размер купона
 * <p>
 * number_of_coupon_periods - кол-во купонных периодов
 * <p>
 * bond_duration - дюрация облигации
 * <p>
 * maturity - срок погашения
 * <p>
 * composition - множество объектов композиции
 */

@Entity
@Setter
@Getter
@Table(name = "bonds")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BondEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bond_id;

    @Column(nullable = false, unique = true)
    private String bondName;

    @Column(nullable = false)
    private LocalDate coupon_payment_date;

    @Column(nullable = false)
    private Integer nominal;

    @Column(nullable = false)
    private Integer coupon_size;

    @Column(nullable = false)
    private Integer number_of_coupon_periods;

    @Column(nullable = false)
    private Integer bond_duration;

    @Column(nullable = false)
    private Integer maturity;

    @OneToMany
    @JoinTable
    private Set<CompositionEntity> composition;
}
