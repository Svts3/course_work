package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.entity.Barometer;
import ua.lviv.iot.repository.BarometerRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BarometerService {
    @Autowired
    private BarometerRepository barometerRepository;



    public Barometer saveInfo(Barometer info){
        info.setDateTime(LocalDateTime.parse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        return barometerRepository.save(info);
    }

    public List<Barometer> findAll(){
        return barometerRepository.findAll();
    }



}
