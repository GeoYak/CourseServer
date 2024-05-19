package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.dto.UserRegistrationDTO;
import server.entity.BondEntity;
import server.entity.UserEntity;
import server.exceptions.UserAlreadyExistException;
import server.repository.BondRepository;
import server.utils.StringToHashUtil;

import java.util.List;

/**
 * Сервис Облигаций
 */
@Service
public class BondService {
    private final BondRepository bondRepo;

    @Autowired
    public BondService(BondRepository bondRepo) {
        this.bondRepo = bondRepo;
    }

    /**
     * Найти все облигации и отсортировать их
     *
     * @param filter вид сортировки
     * @return список облигаций
     */
    @Transactional(readOnly = true)
    public List<BondEntity> getAllBonds(boolean filter) {
        if (filter) {
            return bondRepo.findAllByOrderByBondNameDesc();
        } else {
            return bondRepo.findAllByOrderByBondName();
        }
    }

    /**
     * Найти все облигации по названию и отсортировать их
     *
     * @param subName название облигации
     * @param filter вид сортировки
     * @return список облигаций
     */
    @Transactional(readOnly = true)
    public List<BondEntity> getBondsByName(String subName, boolean filter) {
        if (filter) {
            return bondRepo.findBondEntitiesByBondNameContainsOrderByBondNameDesc(subName);
        } else {
            return bondRepo.findBondEntitiesByBondNameContains(subName);
        }
    }
    /**
     * Добавить облигацию в БД
     *
     * @param bond сущность облигации
     * @return добавление облигации
     */
    @Transactional
    public BondEntity addBond(BondEntity bond){
        BondEntity addBond = new BondEntity();
        addBond.setBondName(bond.getBondName());
        addBond.setCoupon_payment_date(bond.getCoupon_payment_date());
        addBond.setNominal(bond.getNominal());
        addBond.setCoupon_size(bond.getCoupon_size());
        addBond.setNumber_of_coupon_periods(bond.getNumber_of_coupon_periods());
        addBond.setBond_duration(bond.getBond_duration());
        addBond.setMaturity(bond.getMaturity());

        return bondRepo.save(addBond);
    }
    /**
     * Удалить облигацию из БД по названию облигации
     *
     * @param subName названию портфолио
     */
    @Transactional
    public void deleteByBondName(String subName) {
        bondRepo.deleteByBondName(subName);
    }
}
