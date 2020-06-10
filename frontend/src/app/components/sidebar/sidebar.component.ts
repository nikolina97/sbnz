import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  
  clientRole = false;
  adminRole = false;
  

  constructor(private router: Router, private toastr: ToastrService, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.checkUserRole();
  }

  checkUserRole(){
    const item = localStorage.getItem('user');
    if (!item) {
      this.toastr.warning("Niste prijavljeni");
      this.router.navigate(['/login']);
      return;
    }
    else {
      const jwt: JwtHelperService = new JwtHelperService();
      const info = jwt.decodeToken(item);
      console.log(info);
      if (info.role == "ROLE_CLIENT") {
        this.clientRole = true;
        return;
      }
      else{
        this.adminRole = true;
      }
    }
  }

  logout() {
    this.authenticationService.logout().subscribe(
      result => {
        localStorage.removeItem('user');
        this.router.navigate(['login']);
      },
      error => {
        alert(error.error);
      }
    );
  }

}
