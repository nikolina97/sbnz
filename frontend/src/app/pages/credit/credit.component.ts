import { Component, OnInit } from '@angular/core';
import { ContractService } from 'src/app/services/contract.service';

@Component({
  selector: 'app-credit',
  templateUrl: './credit.component.html',
  styleUrls: ['./credit.component.css']
})
export class CreditComponent implements OnInit {

  credits : any[] = [];
  credit : any;

  constructor(private contractService : ContractService) { }

  ngOnInit(): void {

    this.findAll();
  }

  findAll(){
    this.contractService.getAll().subscribe(
      (result) => {
       this.credits = result;
       console.log(result);
      }
    )
  }

  stop(credit) {
    this.contractService.stop(credit).subscribe(
      (result) => {
        window.location.reload();
      }
    )
  }

}
