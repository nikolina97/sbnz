import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {
	private headers = new HttpHeaders({ 'Content-Type': 'application/json',
										'Access-Control-Allow-Origin': '*',
										'Access-Control-Allow-Credentials': 'true'});
	constructor(
		private http: HttpClient
	) { }

	login(auth: any): Observable<any> {
		let loginUrl =  "http://localhost:8080/auth/login";
		return this.http.post(loginUrl, {username: auth.username, password: auth.password}, {headers: this.headers, responseType: 'text'});
	}

	logout(): Observable<any> {
		let logoutUrl = "http://localhost:8080/auth/logout";
		return this.http.get(logoutUrl, {headers: this.headers, responseType: 'text'});
	}

	isLoggedIn(): boolean {
		if (localStorage.getItem('user')) {
				return true;
		}
		return false;
	}

	register(user: any): Observable<any> {
		let registrationUrl =  "http://localhost:8080/auth/registration";
		return this.http.post(registrationUrl, user, {headers: this.headers, responseType: 'json'});
	}

	confirmRegistration(token: string): Observable<any> {
		let confirmationUrl =  "http://localhost:8080/auth/confirm/" + token;
		return this.http.post(confirmationUrl, {headers: this.headers, responseType: 'json'});
	}
}
