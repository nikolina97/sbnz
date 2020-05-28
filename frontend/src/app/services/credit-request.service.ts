import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CreditRequestService {

  private creditUrl = 'http://localhost:8080/credit-request/';

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private http: HttpClient) { }

  checkBasic(basicInfo : any) {
    const url = `${this.creditUrl}basic`;
    return this.http.post(url, basicInfo, { headers: this.headers });
  }

  checkWarrantly(cr : any) {
    const url = `${this.creditUrl}warrantly-fulfillment`;
    return this.http.post(url, cr, { headers: this.headers });
  }

  sendRequest(contract : any) {
    const url = `${this.creditUrl}create-contract`;
    return this.http.post(url, contract, { headers: this.headers });
  }
}
