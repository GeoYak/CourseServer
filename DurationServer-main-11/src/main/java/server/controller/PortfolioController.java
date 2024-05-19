package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.entity.PortfolioEntity;
import server.service.PortfolioService;

import java.util.List;

/**
 * Класс контроллер Портфелей
 */
@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    /**
     * Получить информацию о всех портфелях и отсортировать их
     *
     * @param filter вид сортировки
     * @return список портфелей
     */

    @GetMapping("/all")
    public ResponseEntity<List<PortfolioEntity>> getPortfolios(@RequestParam(name = "filter") boolean filter) {
        List<PortfolioEntity> portfolios = portfolioService.getAllPortfolios(filter);
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

    /**
     * Получить информацию о всех портфелях пользователя по названию портфеля
     *
     * @param login логин
     * @param portfolioName название портфолио
     * @return список портфелей
     */

    @GetMapping("/like")
    public ResponseEntity<List<PortfolioEntity>> getPortfoliosByName(@RequestParam(name = "login") String login,
                                                                     @RequestParam(name = "name") String portfolioName) {
        List<PortfolioEntity> portfolios = portfolioService.getPortfoliosByName(login, portfolioName);
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }
}
