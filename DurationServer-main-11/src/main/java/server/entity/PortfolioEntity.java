package server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность Портфеля
 * <p>
 * Поля:
 * <p>
 * id - уникальный идентификатор
 * <p>
 * portfolioName - название портфеля
 * <p>
 * user - объект пользователя
 * <p>
 * composition - множество объектов композиции
 */
@Entity
@Setter
@Getter
@Table(name = "portfolios")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PortfolioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolio_id;

    @Column(nullable = false)
    private String portfolioName;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<CompositionEntity> composition;
}
