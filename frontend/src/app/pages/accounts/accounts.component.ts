import { Component, OnInit } from '@angular/core';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

  account : any;

  constructor(private clientService : ClientService) { }

  ngOnInit(): void {
    this.clientService.getAccount().subscribe(
      (result) => {
        this.account = result;
      },
      (error) => {
        alert(error.error);
      }
    )
  }

}
