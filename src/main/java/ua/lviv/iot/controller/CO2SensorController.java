package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.entity.CO2Sensor;
import ua.lviv.iot.service.CO2SensorService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/co2")
public class CO2SensorController {
    @Autowired
    private CO2SensorService co2SensorService;


    @GetMapping("/")
    public List<CO2Sensor> findAll(){
        return co2SensorService.findAll();
    }

    @PostMapping("/")
    public CO2Sensor saveInfo(@RequestBody CO2Sensor info){
        return co2SensorService.saveInfo(info);
    }





}
