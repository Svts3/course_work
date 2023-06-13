package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.entity.Barometer;

public interface BarometerRepository extends JpaRepository<Barometer, Long> {
}
