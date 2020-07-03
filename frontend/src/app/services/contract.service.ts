import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  private creditUrl = 'http://localhost:8080/contracts/';

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private http: HttpClient) { }

  addContract(contract : any) {
    const url = `${this.creditUrl}addContract`;
    return this.http.post(url, contract, { headers: this.headers });
  }

  getAll() {
    const url = `${this.creditUrl}all`;
    return this.http.get<any[]>(url, { headers: this.headers });

  }

  stop(credit){
    const url = `${this.creditUrl}stop`;
    return this.http.post(url, credit, { headers: this.headers });
  }
}
