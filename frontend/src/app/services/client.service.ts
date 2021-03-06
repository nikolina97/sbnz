import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private creditUrl = 'http://localhost:8080/clients/';

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private http: HttpClient) { }

  getAllClients() {
    const url = `${this.creditUrl}all`;
    return this.http.get<any[]>(url, { headers: this.headers });
  }

  addNewClient(client: any) {
    const url = `${this.creditUrl}addNew`;
    return this.http.post<any[]>(url, client, { headers: this.headers });
  }

  updateClient(clientDto : any) {
    const url = `${this.creditUrl}update`;
    return this.http.post<any>(url, clientDto, { headers: this.headers });
  }

  removeClient(client : any) {
    const url = `${this.creditUrl}remove`;
    return this.http.post<any>(url, client, { headers: this.headers }); 
  }

  getAccount(){
    const url = `${this.creditUrl}account`;
    return this.http.get<any>(url, { headers: this.headers }); 
  }
}
