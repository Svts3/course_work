package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.entity.TemperatureAndHumiditySensor;

@Repository
public interface TemperatureAndHumiditySensorRepository extends JpaRepository<TemperatureAndHumiditySensor, Long> {

}
