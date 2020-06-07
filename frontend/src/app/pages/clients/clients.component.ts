import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  clients : any[] = [];

  constructor(private clientService : ClientService, private router : Router) { }

  ngOnInit(): void {

    this.clientService.getAllClients().subscribe(
      (result) => {
       this.clients = result;
       console.log(result);
        
      }
    )

  }

  addNewClient() {
    this.router.navigateByUrl("bank/(main:new-client)");
  }

}
