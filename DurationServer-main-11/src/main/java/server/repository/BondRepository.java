package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.entity.BondEntity;
import server.entity.PortfolioEntity;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий Облигации
 */
@Repository
public interface BondRepository extends JpaRepository<BondEntity, Long> {


    /**
     * Найти все облигации по названию облигации, которые содержат подстроку
     *
     * @param subName часть названия облигации
     * @return список облигации
     */

    List<BondEntity> findBondEntitiesByBondNameContains(String subName);


    /**
     * Найти все облигации по названию облигации, которые содержат подстроку и отсортированы по имени в порядке убывания
     *
     * @param subName часть названия облигации
     * @return список облигации
     */
    List<BondEntity> findBondEntitiesByBondNameContainsOrderByBondNameDesc(String subName);

    /**
     * Найти все облигации, отсортированные по имени облигации в порядке возрастания
     *
     * @return список облигации
     */
    List<BondEntity> findAllByOrderByBondName();

    /**
     * Найти все облигации, отсортированные по имени облигации в порядке убывания
     *
     * @return список облигации
     */
    List<BondEntity> findAllByOrderByBondNameDesc();

    /**
     * Найти все облигации по ID
     * @param id id
     * @return список облигации
     */
    @Query(value = "SELECT  * " +
            "FROM bonds b " +
            "WHERE b.bond_id = ?1 ", nativeQuery = true)
    List<BondEntity> findByBondId(Long id);

    /**
     * Удалить облигацию из БД по её названию
     *
     * @param bondName название облигации
     */
    void deleteByBondName(String bondName);
}
