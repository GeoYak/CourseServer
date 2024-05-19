package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import server.entity.PortfolioEntity;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий Портфелей
 */
@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {


    /**
     * Найти все портфели по названию портфеля, которые содержат подстроку и связанны с текущим пользователем
     * @param login логин пользователя
     * @param subName название портфеля
     * @return список портфелей
     */
    @Query(value = "SELECT  p.portfolio_id,  p.portfolio_name, u.login , u.user_id " +
            "FROM users u " +
            "JOIN portfolios p ON u.user_id = p.user_id " +
            "WHERE u.login = ?1 and p.portfolio_name = ?2 ", nativeQuery = true)
    List<PortfolioEntity> findPortfolioEntitiesByPortfolioNameContains(String login, String subName);


    /**
     * Найти все портфели, отсортированные по названию портфеля в порядке возрастания
     *
     * @return список портфелей
     */
    List<PortfolioEntity> findAllByOrderByPortfolioName();

    /**
     * Найти все портфели, отсортированные по названию портфеля в порядке убывания
     *
     * @return список портфелей
     */
    List<PortfolioEntity> findAllByOrderByPortfolioNameDesc();
    /**
     * Найти все портфели по id
     * @param id id
     * @return список портфелей
     */
    @Query(value = "SELECT  p.portfolio_id,  p.portfolio_name, u.user_id " +
            "FROM users u " +
            "JOIN portfolios p ON u.user_id = p.user_id " +
            "WHERE p.portfolio_id = ?1 ", nativeQuery = true)
    List<PortfolioEntity> findByPortfolioId(Long id);

}
