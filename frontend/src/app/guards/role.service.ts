import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, CanActivate } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from './../services/authentication.service';

@Injectable({
	providedIn: 'root'
})
export class RoleGuard implements CanActivate {

	constructor(
		public auth: AuthenticationService,
		public router: Router
	) { }

	canActivate(route: ActivatedRouteSnapshot): boolean {
		const expectedRoles: string = route.data.expectedRoles;
		const token = localStorage.getItem('user');
		const jwt: JwtHelperService = new JwtHelperService();

		if (!token) {
			this.router.navigate(['/login']);
			return false;
		}

		const info = jwt.decodeToken(token);
		console.log(route)
		const roles: string[] = expectedRoles.split('|', 2);
		console.log(info.role);
		if (roles.indexOf(info.role) === -1) {
			// this.router.navigate(['/login']);
			// return false;
		}
		return true;
	}
}