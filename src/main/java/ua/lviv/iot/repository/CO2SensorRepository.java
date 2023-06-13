package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.entity.CO2Sensor;
@Repository
public interface CO2SensorRepository extends JpaRepository<CO2Sensor, Long> {
}
