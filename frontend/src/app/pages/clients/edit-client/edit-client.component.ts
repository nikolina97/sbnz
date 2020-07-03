import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ClientService } from '../../../services/client.service';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {

  @Input()
  client;

  constructor(private router : Router, private clientService : ClientService) { 
  }
  
  ngOnInit(): void {
    console.log(this.client);

  }

  addNewClient() {
    this.router.navigateByUrl("bank/(main:new-client)");
  }

  editClient(){
    let clientDTO = {
      clientId : this.client.id,
      account : this.client.account,
      monthlyIncome: this.client.monthlyIncome,
      monthlyOutcome : this.client.monthlyOutcome,
      rewardPoints : this.client.rewardPoints
    }
    this.clientService.updateClient(clientDTO).subscribe(
      (result) => {
        alert("Uspjesno izmjenjen klijent");
        this.client = result;
        window.location.reload();
      }
    )
  }

}
