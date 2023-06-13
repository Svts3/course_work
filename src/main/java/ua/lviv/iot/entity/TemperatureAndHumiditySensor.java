package ua.lviv.iot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "temperature_and_humidity_sensor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureAndHumiditySensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long temperature;
    private Long humidity;
    @Column(name = "date_time")
    private LocalDateTime dateTime;


}
