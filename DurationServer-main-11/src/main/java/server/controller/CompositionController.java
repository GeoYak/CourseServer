package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.BondToPortfolioDTO;
import server.entity.CompositionEntity;
import server.service.CompositionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс контроллер Композиций
 */
@RestController
@RequestMapping("/compositions")
public class CompositionController {

    private final CompositionService compositionService;

    @Autowired
    public CompositionController(CompositionService compositionService) {
        this.compositionService = compositionService;
    }


    /**
     * Получить информацию о всех композициях пользователя
     *
     * @param login логин
     * @return список композиций
     */

    @GetMapping("/all")
    public ResponseEntity<List<CompositionEntity>> getCompositions(@RequestParam(name = "login") String login) {
        List<CompositionEntity> compositions = compositionService.getAll(login);
        return new ResponseEntity<>(compositions, HttpStatus.OK);
    }
    /**
     * Получить информацию о всех композициях пользователя по названию портфеля
     *
     * @param login логин
     * @param portfolioName название портфолио
     * @return список композиций
     */
    @GetMapping("/like")
    public ResponseEntity<List<CompositionEntity>> getCompositionsByName(@RequestParam(name = "login") String login,
                                                                  @RequestParam(name = "name") String portfolioName
                                                                  ) {

        List<CompositionEntity> compositions = compositionService.getAllByPortfolioName(login, portfolioName);
        return new ResponseEntity<>(compositions, HttpStatus.OK);
    }

    /**
     * Добавление в композицию облигации в БД или создание новой композиции, если не существует, по названию портфеля
     *
     * @param bondToPortfolio DTO для получения всей информации для композиции
     * @return map и HTTP статус "OK"
     */

    @PostMapping("/add")
    public ResponseEntity<?> addBondToPortfolio(@RequestBody BondToPortfolioDTO bondToPortfolio) {
        Map<String, Object> map = new HashMap<>();
        compositionService.addComposition(bondToPortfolio);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * Удаление композиции пользователя по названию портфеля
     *
     * @param login логин
     * @param portfolioName название портфолио
     * @return HTTP статус "OK"
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCompositionByPortfolioName(@RequestParam(name = "login") String login,
                                                              @RequestParam(name = "name") String portfolioName) {
        compositionService.deleteCompByPortfolioName(login, portfolioName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}