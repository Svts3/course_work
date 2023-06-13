package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.entity.CO2Sensor;
import ua.lviv.iot.repository.CO2SensorRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CO2SensorService {
    @Autowired
    private CO2SensorRepository co2SensorRepository;



    public List<CO2Sensor>findAll(){
        return co2SensorRepository.findAll();
    }
    public CO2Sensor saveInfo(CO2Sensor info){
        info.setDateTime(LocalDateTime.parse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        return co2SensorRepository.save(info);
    }


}
