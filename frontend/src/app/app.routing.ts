import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule, Router } from '@angular/router';

import {LoginGuard} from './guards/login.service'
import {LoginComponent} from './pages/login/login.component';
import { BankComponent } from './pages/bank/bank.component';
import { RoleGuard } from './guards/role.service';

const routes: Routes =[
  { path: '', redirectTo: '/bank', pathMatch: 'full' },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'bank',
    component: BankComponent,
    data: { expectedRoles: 'ROLE_CLIENT'},
    canActivate: [RoleGuard]
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
})
export class AppRoutingModule { }
