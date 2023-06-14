import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { interval } from 'rxjs';
import { Chart } from 'chart.js';

interface Reading {
  value: any;
  pressure: number;
  temperature: number;
  humidity: number;
}

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent implements OnInit {
  info: Reading[] = [];
  pressureReading: Reading[] = [];
  co2Reading:Reading[] = [];
  temperatures: number[] = [];
  humidities: number[] = [];
  pressures: number[] = [];
  co2: number[] = [];
  averageTemperature: number = 0;
  averageHumidity: number = 0;
  averagePressure: number = 0;
  averageCo2: number = 0;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getReadingsForTemperatureAndHumidity();
    this.getReadingsForPressure();
    this.getReadingsForCO2();

    interval(5000).subscribe(() => {
      this.getReadingsForTemperatureAndHumidity();
      this.getReadingsForPressure();
      this.getReadingsForCO2();
    });
  }

  getReadingsForTemperatureAndHumidity() {
    this.http.get('http://localhost:8080/dht11/').subscribe((response: any) => {
      this.info = response;
      this.extractReadings();
      this.calculateAverages();
    });
  }

  getReadingsForPressure() {
    this.http.get('http://localhost:8080/barometer/').subscribe((response: any) => {
      this.pressureReading = response;
      this.extractReadingsForPressure();
      this.calculateAverages();
    });
  }
  getReadingsForCO2() {
    this.http.get('http://localhost:8080/co2/').subscribe((response: any) => {
      this.co2Reading = response;
      this.extractReadingsForCO2();
      this.calculateAverages();
    });
  }

  extractReadingsForPressure() {
    this.pressures = this.pressureReading.map((item) => item.value);
    console.log('Pressure:');
    console.log(this.pressures);
  }
  extractReadingsForCO2() {
    this.co2 = this.co2Reading.map((item) => item.value);
    console.log('Pressure:');
    console.log(this.pressures);
  }

  extractReadings() {
    this.temperatures = this.info.map((item) => item.temperature);
    this.humidities = this.info.map((item) => item.humidity);
  }

  calculateAverages() {
    this.calculateAverageTemperature();
    this.calculateAverageHumidity();
    this.calculateAveragePressure();
    this.calculateAverageCO2();
  }

  calculateAverageTemperature() {
    const sum = this.temperatures.reduce((acc, value) => acc + value, 0);
    this.averageTemperature = sum / this.temperatures.length;
    this.averageTemperature = +this.averageTemperature.toFixed(2);
  }

  calculateAverageHumidity() {
    const sum = this.humidities.reduce((acc, value) => acc + value, 0);
    this.averageHumidity = sum / this.humidities.length;
    this.averageHumidity = +this.averageHumidity.toFixed(2);
  }

  calculateAveragePressure() {
    const sum = this.pressures.reduce((acc, value) => acc + value, 0);
    this.averagePressure = sum / this.pressures.length;
    this.averagePressure = +this.averagePressure.toFixed(2);
  }
  calculateAverageCO2() {
    const sum = this.co2.reduce((acc, value) => acc + value, 0);
    this.averageCo2 = sum / this.co2.length;
    this.averageCo2 = +this.averageCo2.toFixed(2);
  }
}
