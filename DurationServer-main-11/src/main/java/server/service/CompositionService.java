package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import server.dto.BondToPortfolioDTO;
import server.entity.BondEntity;
import server.entity.CompositionEntity;
import server.entity.PortfolioEntity;
import server.entity.UserEntity;
import server.repository.BondRepository;
import server.repository.CompositionRepository;
import server.repository.PortfolioRepository;
import server.repository.UserRepository;

import java.util.List;
/**
 * Сервис Композиций
 */
@Service
public class CompositionService {
    UserRepository userRepo;
    BondRepository bondRepo;
    PortfolioRepository portfolioRepo;
    private final CompositionRepository compositionRepo;

    @Autowired
    public CompositionService(CompositionRepository compositionRepo, PortfolioRepository portfolioRepo,BondRepository bondRepo, UserRepository userRepo) {
        this.compositionRepo = compositionRepo;
        this.portfolioRepo = portfolioRepo;
        this.bondRepo = bondRepo;
        this.userRepo = userRepo;
    }

    /**
     * Найти все композиции пользователя
     *
     * @param login логин
     * @return список композиции
     */
    @Transactional(readOnly = true)
    public List<CompositionEntity> getAll(String login) {
            return compositionRepo.findAllByLogin(login);

    }
    /**
     * Найти все композиции пользователя по названию портфеля
     *
     * @param login логин
     * @param subName название портфолио
     * @return список композиции
     */
    @Transactional(readOnly = true)
    public List<CompositionEntity> getAllByPortfolioName(String login, String subName) {
        return compositionRepo.findAllByLoginAndLikePortfolioName(login, subName);
    }

    /**
     * Добавить композицию в БД, также создание портфеля по названию, если оно не существует
     *
     * @param bondToPortfolio dto для получения всей информации для композиции
     * @return добавление композиции
     */
    @Transactional
    public CompositionEntity addComposition(BondToPortfolioDTO bondToPortfolio){

        CompositionEntity addcomposition = new CompositionEntity();

        BondEntity existingBond = bondRepo.findByBondId(bondToPortfolio.getBond_id()).get(0);
        UserEntity existingUser = userRepo.findUserEntityByLogin(bondToPortfolio.getLogin()).get(0);
        PortfolioEntity portfolio;
        if(bondToPortfolio.getPortfolio_id() == null){
            portfolio = new PortfolioEntity();
            portfolio.setUser(existingUser);
            portfolio.setPortfolioName(bondToPortfolio.getPortfolioName());
        }else {
            portfolio = portfolioRepo.findByPortfolioId(bondToPortfolio.getPortfolio_id()).get(0);
        }
        portfolioRepo.save(portfolio);
        addcomposition.setPortfolio(portfolio);
        addcomposition.setBond(existingBond);
        addcomposition.setBondShare(bondToPortfolio.getBondShare());

        return compositionRepo.save(addcomposition);
    }

    /**
     * Удалить композицию из БД пользователя по названию портфеля
     *
     * @param login логин
     * @param name названию портфолио
     */
    @Transactional
    public void deleteCompByPortfolioName(String login,String name) {
        List<CompositionEntity> delComp= compositionRepo.findAllByLoginAndPortfolioName(login, name);
        compositionRepo.deleteAll(delComp);
    }
}