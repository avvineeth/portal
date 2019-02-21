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

    person: Person;

    ngOnInit(): void {
        this.getPersonList();
        this.person = new Person();
    }

    constructor(private dashboardService: DashboardService) {}


    getPersonList(): void {
        this.dashboardService.getAllPersonList().
        subscribe((personData) => {this.persons = personData, console.log(personData); },
         (error) => {
            console.log(error);
        });
    }

     deletePerson(id): void {
        this.dashboardService.deletePerson(id).
        subscribe((personData) => {this.persons = personData, console.log(personData); },
         (error) => {
            console.log(error);
        });
    }

        savePerson(person): void {
            console.log(person.name);
            console.log(person.country);
            this.dashboardService.savePerson(person).
            subscribe((personData) => {this.persons = personData, console.log(personData); },
            (error) => {
            console.log(error);
            });
    }
}
