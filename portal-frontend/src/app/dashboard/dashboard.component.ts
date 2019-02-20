import { Component, OnInit } from '@angular/core';
import { Person } from './dashboard';
import { DashboardService } from './dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


    persons: Person[];
    ngOnInit(): void {
        this.getPersonList();
    }

    constructor(private dashboardService: DashboardService) {}


    getPersonList(): void {
        this.dashboardService.getAllPersonList().
        subscribe((personData) => {this.persons = personData, console.log(personData); },
         (error) => {
            console.log(error);
        });
    }
}
