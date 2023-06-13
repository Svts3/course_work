package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.entity.Barometer;
import ua.lviv.iot.service.BarometerService;

import javax.swing.plaf.basic.BasicToggleButtonUI;
import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/barometer")
public class BarometerController {
    @Autowired
    private BarometerService barometerService;

    @PostMapping("/")
    public Barometer saveInfo(@RequestBody Barometer barometer){
        return barometerService.saveInfo(barometer);
    }

    @GetMapping("/")
    public List<Barometer> findAll(){
        return barometerService.findAll();
    }


}
