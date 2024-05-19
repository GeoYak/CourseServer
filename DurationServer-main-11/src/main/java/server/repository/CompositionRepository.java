package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.entity.BondEntity;
import server.entity.CompositionEntity;

import java.util.List;

/**
 * Репозиторий Композиции
 */
@Repository
public interface CompositionRepository extends JpaRepository<CompositionEntity, Long> {
    /**
     * Найти все композиции пользователя
     * @param login логин
     * @return список композиций пользователя
     */
    @Query(value = "SELECT  p.portfolio_id, c.composition_id, b.bond_id , b.bond_name, p.portfolio_name, c.bond_share " +
            "FROM users u " +
            "JOIN portfolios p ON u.user_id = p.user_id " +
            "JOIN composition c ON p.portfolio_id = c.portfolio_id " +
            "JOIN bonds b ON c.bond_id = b.bond_id " +
            "WHERE u.login = ?1", nativeQuery = true)
    List<CompositionEntity> findAllByLogin(String login);
    /**
     * Найти все композиции пользователя по названию портфеля
     * @param login логин
     * @param subName название портфеля
     * @return список композиций пользователя
     */
    @Query(value = "SELECT  p.portfolio_id, c.composition_id, b.bond_id , b.bond_name, p.portfolio_name, c.bond_share, b.bond_duration " +
            "FROM users u " +
            "JOIN portfolios p ON u.user_id = p.user_id " +
            "JOIN composition c ON p.portfolio_id = c.portfolio_id " +
            "JOIN bonds b ON c.bond_id = b.bond_id " +
            "WHERE u.login = ?1 and p.portfolio_name like %?2% ", nativeQuery = true)
    List<CompositionEntity> findAllByLoginAndLikePortfolioName(String login, String subName);

    /**
     * Найти все композиции пользователя по названию портфеля
     * @param login логин
     * @param subName название портфеля
     * @return список композиций пользователя
     */
    @Query(value = "SELECT  p.portfolio_id, c.composition_id, b.bond_id , b.bond_name, p.portfolio_name, c.bond_share, b.bond_duration " +
            "FROM users u " +
            "JOIN portfolios p ON u.user_id = p.user_id " +
            "JOIN composition c ON p.portfolio_id = c.portfolio_id " +
            "JOIN bonds b ON c.bond_id = b.bond_id " +
            "WHERE u.login = ?1 and p.portfolio_name = ?2 ", nativeQuery = true)
    List<CompositionEntity> findAllByLoginAndPortfolioName(String login, String subName);
}