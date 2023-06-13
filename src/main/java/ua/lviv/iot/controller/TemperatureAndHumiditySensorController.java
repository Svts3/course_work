package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.entity.TemperatureAndHumiditySensor;
import ua.lviv.iot.exception.TemperatureAndHumiditySensorNotFoundException;
import ua.lviv.iot.service.TemperatureAndHumiditySensorService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dht11")
public class TemperatureAndHumiditySensorController {

    private TemperatureAndHumiditySensorService temperatureAndHumiditySensorService;
    @Autowired
    public TemperatureAndHumiditySensorController(TemperatureAndHumiditySensorService temperatureAndHumiditySensorService){
        this.temperatureAndHumiditySensorService = temperatureAndHumiditySensorService;
    }
    @GetMapping("/")
    public List<TemperatureAndHumiditySensor> findAll(){
        return temperatureAndHumiditySensorService.findAll();
    }
    @GetMapping("/{id}")
    public TemperatureAndHumiditySensor findById(@PathVariable("id")Long id) throws TemperatureAndHumiditySensorNotFoundException {
        return temperatureAndHumiditySensorService.findById(id);
    }
    @PostMapping("/")
    public TemperatureAndHumiditySensor saveInfo(@RequestBody TemperatureAndHumiditySensor info){
        return temperatureAndHumiditySensorService.saveInfo(info);
    }
    @PutMapping("/{id}")
    public TemperatureAndHumiditySensor updateInfo(@PathVariable("id")Long id, @RequestBody TemperatureAndHumiditySensor info) throws TemperatureAndHumiditySensorNotFoundException {
        return temperatureAndHumiditySensorService.update(id, info);
    }
    @DeleteMapping("/{id}")
    public TemperatureAndHumiditySensor deleteById(@PathVariable("id")Long id) throws TemperatureAndHumiditySensorNotFoundException {
        return temperatureAndHumiditySensorService.deleteByid(id);
    }

}
