
import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule, Router } from '@angular/router';

import { BankComponent } from '../pages/bank/bank.component';
import { RoleGuard } from '../guards/role.service';
import { BankClientComponent } from '../pages/bank-client/bank-client.component';
import { ClientsComponent } from '../pages/clients/clients.component';
import { CreditRequestComponent } from '../pages/credit-request/credit-request.component';
import { AccountsComponent } from '../pages/accounts/accounts.component';

const routes:  Routes = [
    {
        path: 'bank',
        component: BankComponent,
        data: { expectedRoles: 'ROLE_ADMIN'},
        canActivate: [RoleGuard],
        children: [
          {
            path: '',
            component: ClientsComponent,
            outlet: "main"
          }
        ]
      },
      {
        path: 'bank-client',
        component: BankClientComponent,
        data: { expectedRoles: 'ROLE_CLIENT'},
        canActivate: [RoleGuard],
        children: [
          {
            path: '',
            component: CreditRequestComponent,
            outlet: "main"
          },
          {
            path: 'accounts',
            component: AccountsComponent,
            outlet: "main"
          }
        ]
      }
]
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
export class ComponentsRoutingModule { }