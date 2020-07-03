import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  private creditUrl = 'http://localhost:8080/reports/';

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private http: HttpClient) { }
  
  countByType() {
    const url = `${this.creditUrl}countByType`;
    return this.http.get(url, { headers: this.headers });
  }

  largestLoan() {
    const url = `${this.creditUrl}largestLoan`;
    return this.http.get(url, { headers: this.headers });
  }

  smallestLoan() {
    const url = `${this.creditUrl}smallestLoan`;
    return this.http.get(url, { headers: this.headers });
  }

  mostActiveClients() {
    const url = `${this.creditUrl}mostActiveClients`;
    return this.http.get(url, { headers: this.headers });
  }
}
