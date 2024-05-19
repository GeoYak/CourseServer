package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.entity.BondEntity;
import server.service.BondService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс контроллер Облигаций
 */
@RestController
@RequestMapping("/bonds")
public class BondController {

    private final BondService bondService;

    @Autowired
    public BondController(BondService bondService) {
        this.bondService = bondService;
    }


    /**
     * Получить информацию о всех облигациях и отсортировать их
     *
     * @param filter вид сортировки
     * @return список облигаций
     */
    @GetMapping("/all")
    public ResponseEntity<List<BondEntity>> getBonds(@RequestParam(name = "filter") boolean filter) {
        List<BondEntity> bonds = bondService.getAllBonds(filter);
        return new ResponseEntity<>(bonds, HttpStatus.OK);
    }


    /**
     * Получить информацию о всех облигациях по названию облигации и отсортировать их
     *
     * @param bondName название облигации
     * @param filter      вид сортировки
     * @return список облигаций
     */
    @GetMapping("/like")
    public ResponseEntity<List<BondEntity>> getBondsByName(@RequestParam(name = "name") String bondName,
                                                           @RequestParam(name = "filter") boolean filter) {
        List<BondEntity> bonds = bondService.getBondsByName(bondName, filter);
        return new ResponseEntity<>(bonds, HttpStatus.OK);
    }
    /**
     * Добавление облигации в БД
     *
     * @param bond сущность облигации
     * @return map и HTTP статус "OK"
     */

    @PostMapping("/add")
    public ResponseEntity<?> addBondToTable(@RequestBody BondEntity bond) {
        Map<String, Object> map = new HashMap<>();
        bondService.addBond(bond);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * Удаление облигации
     * @return HTTP статус "OK"
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBondByBondName(@RequestParam(name = "name") String bondName) {
        bondService.deleteByBondName(bondName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}