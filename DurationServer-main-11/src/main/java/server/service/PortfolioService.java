package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.entity.PortfolioEntity;
import server.repository.PortfolioRepository;

import java.util.List;

/**
 * Сервис Портфелей
 */
@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepo;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepo) {
        this.portfolioRepo = portfolioRepo;
    }

    /**
     * Найти все портфели и отсортировать их
     *
     * @param filter вид сортировки
     * @return список портфелей
     */
    @Transactional(readOnly = true)
    public List<PortfolioEntity> getAllPortfolios(boolean filter) {
        if (filter) {
            return portfolioRepo.findAllByOrderByPortfolioNameDesc();
        } else {
            return portfolioRepo.findAllByOrderByPortfolioName();
        }
    }

    /**
     * Найти все портфели пользователя по названию портфеля
     *
     * @param login логин
     * @param subName часть имени
     * @return список портфелей
     */
    @Transactional(readOnly = true)
    public List<PortfolioEntity> getPortfoliosByName(String login, String subName) {
            return portfolioRepo.findPortfolioEntitiesByPortfolioNameContains(login,subName);

    }
}