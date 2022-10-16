package com.powerldger.code.api;

import com.powerldger.code.dto.BatterieDto;
import com.powerldger.code.model.Batteries;
import com.powerldger.code.repository.BatteriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    BatteriesRepository batteriesRepository;

    @PostMapping("/saveListBatteries")
    public ResponseEntity createTutorial(@RequestBody List<Batteries> batteries) {
        try {
            batteries.stream().forEach(b -> batteriesRepository
                    .save(b));
            return new ResponseEntity<>(batteries, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByPostCodeRange")
    public ResponseEntity<BatterieDto> getByRangeWatt(@RequestParam(required = false) int start, int end ) {
        try {
            List<Batteries> batteries = new ArrayList<>();

            BatterieDto response = new BatterieDto();
            batteriesRepository.findWithRangePostCode(start,end).forEach(batteries::add);

            if (batteries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                response.setBatteries(batteries.stream()
                        .sorted(Comparator.comparing(Batteries::getName)).collect(Collectors.toList()));
                OptionalDouble average = batteries
                        .stream()
                        .mapToDouble(a -> a.getWatt())
                        .average();
                Double sum = batteries
                        .stream()
                        .mapToDouble(a -> a.getWatt())
                        .sum();
                response.setAverage(average.getAsDouble());
                response.setTotal(sum);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
