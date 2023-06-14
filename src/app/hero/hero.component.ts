import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { interval } from 'rxjs';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit {
  temperatureAndHumidity: any[] = [];
  pressure: any[] = [];
  co2:any[] = [];
  searchDate: string = '';
  sortOrder: string = 'asc'; // Track the sort order

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.getTemperatures();
    this.getPressure();
    this.getCO2();
    
    // Restore search date after refresh
    this.searchDate = localStorage.getItem('searchDate') || '';

    // Fetch temperatures every 10 seconds
    interval(10000).subscribe(() => {
      this.getTemperatures();
      this.getPressure();
      this.getCO2();
    });
  }

  getTemperatures() {
    this.http.get('http://localhost:8080/dht11/')
      .subscribe((response: any) => {
        this.temperatureAndHumidity = response;
        this.temperatureAndHumidity.sort((a, b) => b.dateTime.localeCompare(a.dateTime));
      });
  }

  getPressure() {
    this.http.get('http://localhost:8080/barometer/')
      .subscribe((response: any) => {
        this.pressure = response;
        this.pressure.sort((a, b) => b.dateTime.localeCompare(a.dateTime));
      });
  }

  getCO2() {
    this.http.get('http://localhost:8080/co2/')
      .subscribe((response: any) => {
        this.co2 = response;
        this.co2.sort((a, b) => b.dateTime.localeCompare(a.dateTime));
      });
  }

  searchByDate() {
    if (!this.searchDate) {
      this.getTemperatures();
      this.getPressure();
      this.getCO2();
    } else {
      const filteredTemperatureAndHumidity = this.temperatureAndHumidity.filter(item => {
        const date = item.dateTime.split('T')[0]; // Extract the date part
        return date === this.searchDate;
      });
      const filteredPressure = this.pressure.filter(item => {
        const date = item.dateTime.split('T')[0]; // Extract the date part
        return date === this.searchDate;
      });
      const filteredCO2 = this.co2.filter(item => {
        const date = item.dateTime.split('T')[0]; // Extract the date part
        return date === this.searchDate;
      });
      this.temperatureAndHumidity = filteredTemperatureAndHumidity;
      this.pressure = filteredPressure;
      this.co2 = filteredCO2;
    }
  }

  sortSensors() {
    if (this.sortOrder === 'asc') {
      this.temperatureAndHumidity.sort((a, b) => {
        // Sort by date
        const dateComparison = a.dateTime.localeCompare(b.dateTime);
        if (dateComparison !== 0) {
          return dateComparison;
        }
        // Sort by time
        return a.dateTime.split('T')[1].localeCompare(b.dateTime.split('T')[1]);
      });
      this.pressure.sort((a, b) => {
        // Sort by date
        const dateComparison = a.dateTime.localeCompare(b.dateTime);
        if (dateComparison !== 0) {
          return dateComparison;
        }
        // Sort by time
        return a.dateTime.split('T')[1].localeCompare(b.dateTime.split('T')[1]);
      });
      this.co2.sort((a, b) => {
        // Sort by date
        const dateComparison = a.dateTime.localeCompare(b.dateTime);
        if (dateComparison !== 0) {
          return dateComparison;
        }
        // Sort by time
        return a.dateTime.split('T')[1].localeCompare(b.dateTime.split('T')[1]);
      });
      this.sortOrder = 'desc'; // Update the sort order to descending
    } else {
      this.temperatureAndHumidity.sort((a, b) => {
        // Sort by date (reversed)
        const dateComparison = b.dateTime.localeCompare(a.dateTime);
        if (dateComparison !== 0) {
          return dateComparison;
        }
        // Sort by time (reversed)
        return b.dateTime.split('T')[1].localeCompare(a.dateTime.split('T')[1]);
      });
      this.pressure.sort((a, b) => {
        // Sort by date (reversed)
        const dateComparison = b.dateTime.localeCompare(a.dateTime);
        if (dateComparison !== 0) {
          return dateComparison;
        }
        // Sort by time (reversed)
        return b.dateTime.split('T')[1].localeCompare(a.dateTime.split('T')[1]);
      });
      this.co2.sort((a, b) => {
        // Sort by date (reversed)
        const dateComparison = b.dateTime.localeCompare(a.dateTime);
        if (dateComparison !== 0) {
          return dateComparison;
        }
        // Sort by time (reversed)
        return b.dateTime.split('T')[1].localeCompare(a.dateTime.split('T')[1]);
      });
      this.sortOrder = 'asc'; // Update the sort order to ascending
    }
  }

  resetSearch() {
    this.searchDate = ''; // Clear the search date
    localStorage.removeItem('searchDate'); // Remove search date from local storage
    this.getTemperatures();
    this.getPressure();
    this.getCO2(); // Fetch all temperatures
  }

  onSearchDateChange() {
    localStorage.setItem('searchDate', this.searchDate); // Store search date in local storage
  }
}
