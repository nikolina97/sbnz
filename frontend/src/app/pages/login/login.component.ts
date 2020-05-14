import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	form: FormGroup;

	constructor(
		private fb: FormBuilder,
		private router: Router,
		private authenticationService: AuthenticationService,
		private toastr: ToastrService
	) {
		this.form = this.fb.group({
			username: [null, Validators.required],
			password: [null, Validators.required]
		});
	}

	ngOnInit() {
	}

	submit() {
		const auth: any = {};
		auth.username = this.form.value.username;
		auth.password = this.form.value.password;
		this.authenticationService.login(auth).subscribe(
			result => {
				this.toastr.success('Login successfull!');
				localStorage.setItem('user', result);
				this.router.navigate(['bank']);
			},
			error => {
				alert("Invalid username or password");
				this.toastr.error(error.error);
			}
		);
	}
}