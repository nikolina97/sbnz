import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { ComponentsModule } from './components/components.module';
import { Interceptor } from './interceptors/intercept.service';

import { AppComponent } from './app.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app.routing';
import { LoginComponent } from './pages/login/login.component';
import { BankComponent } from './pages/bank/bank.component';
import { BankClientComponent } from './pages/bank-client/bank-client.component';
import { ContractComponent } from './pages/contract/contract.component';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    RouterModule,
    AppRoutingModule,
    ReactiveFormsModule,
    ComponentsModule,
    ToastrModule.forRoot({
      timeOut: 400000,
      positionClass: 'toast-top-center',
    })
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    BankComponent,
    BankClientComponent,
    ContractComponent
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
