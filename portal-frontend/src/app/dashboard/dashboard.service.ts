import { Injectable } from '@angular/core';
import { Person } from './dashboard';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';


@Injectable()
export class DashboardService {
    constructor(private http: HttpClient) {}

    getAllPersonList(): Observable<Person[]> {
          return this.http.get<Person[]>('http://localhost:8081/portal/personlist').pipe(
          catchError(this.handleError)
          );
    }

    deletePerson(id): Observable<Person[]> {
          return this.http.get<Person[]>('http://localhost:8081/portal/delete-' + id).pipe(
          catchError(this.handleError)
          );
    }

    savePerson(person): Observable<Person[]> {
          return this.http.post<Person[]>('http://localhost:8081/portal/savePerson', person).pipe(
          catchError(this.handleError)
          );
    }

    private handleError(error: Response) {
        return Observable.throw(error);
    }
}
