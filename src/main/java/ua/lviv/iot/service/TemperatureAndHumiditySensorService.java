package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.entity.TemperatureAndHumiditySensor;
import ua.lviv.iot.exception.TemperatureAndHumiditySensorNotFoundException;
import ua.lviv.iot.repository.TemperatureAndHumiditySensorRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Service
public class TemperatureAndHumiditySensorService {

    private TemperatureAndHumiditySensorRepository temperatureAndHumiditySensorRepository;
    @Autowired
    public TemperatureAndHumiditySensorService(TemperatureAndHumiditySensorRepository temperatureAndHumiditySensorRepository){
        this.temperatureAndHumiditySensorRepository = temperatureAndHumiditySensorRepository;
    }



    public TemperatureAndHumiditySensor saveInfo(TemperatureAndHumiditySensor info){
        info.setDateTime(LocalDateTime.parse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        return temperatureAndHumiditySensorRepository.save(info);
    }

    public TemperatureAndHumiditySensor findById(Long id) throws TemperatureAndHumiditySensorNotFoundException {
        return temperatureAndHumiditySensorRepository.findById(id).orElseThrow(()->new TemperatureAndHumiditySensorNotFoundException("Temperature and Humidity sensor with id "+id+" was not found!"));
    }
    public TemperatureAndHumiditySensor update(Long id, TemperatureAndHumiditySensor info) throws TemperatureAndHumiditySensorNotFoundException {
        TemperatureAndHumiditySensor temperatureAndHumiditySensor = findById(id);
        temperatureAndHumiditySensor.setId(id);
        temperatureAndHumiditySensor.setTemperature(info.getTemperature());
        temperatureAndHumiditySensor.setHumidity(info.getHumidity());
        return temperatureAndHumiditySensorRepository.save(temperatureAndHumiditySensor);
    }

    public TemperatureAndHumiditySensor deleteByid(Long id) throws TemperatureAndHumiditySensorNotFoundException {
        TemperatureAndHumiditySensor temperatureAndHumiditySensor = findById(id);
        temperatureAndHumiditySensorRepository.deleteById(id);
        return temperatureAndHumiditySensor;
    }


    public List<TemperatureAndHumiditySensor> findAll() {
        return temperatureAndHumiditySensorRepository.findAll();
    }
}
