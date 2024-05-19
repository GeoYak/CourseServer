package server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * Сущность Композиции
 * <p>
 * Поля:
 * <p>
 * id - уникальный идентификатор
 * <p>
 * bondShare - доля облигации
 * <p>
 * portfolio - объект портфеля
 * <p>
 * bond - объект облигации
 */
@Entity
@Table(name = "composition")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CompositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long composition_id;

    @Column(nullable = false)
    private Double bondShare;


    @ManyToOne
    @JoinColumn(name = "portfolio_id", referencedColumnName = "portfolio_id")
    private PortfolioEntity portfolio;

    @ManyToOne
    @JoinColumn(name = "bond_id", referencedColumnName = "bond_id")
    private BondEntity bond;
}