import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { Router } from '@angular/router';
import { ComponentsRoutingModule } from 'src/app/components/components-routing.module';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  clients : any[] = [];
  client : any;
  editClient = false;

  constructor(private clientService : ClientService, private router : Router) { }

  ngOnInit(): void {

    this.editClient = false;
    this.findAll();

  }

  findAll(){
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

  edit(client) {
    this.client = client;
    this.editClient = true;
  }

  remove(client) {
    this.clientService.removeClient(client).subscribe(
      (result) => {
          this.findAll();
      }
    )
  }

}
