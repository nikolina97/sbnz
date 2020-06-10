import { Component, OnInit,Input, Output, EventEmitter } from '@angular/core';
import { CreditRequestService } from '../../services/credit-request.service';
import { ToastrService } from 'ngx-toastr';
import { ContractService } from 'src/app/services/contract.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css']
})
export class ContractComponent implements OnInit {
  

  @Input()
  contract;

  showRequest = true;
  showContract = false;

  constructor(private creditRequestService : CreditRequestService, private toastr: ToastrService, 
    private contractService : ContractService, private router : Router) { }

  ngOnInit(): void {
  }

  sendRequest() {
    this.creditRequestService.sendRequest(this.contract).subscribe(
      (result) => {
        console.log(result);
        this.contract = result['contract'];
        this.showRequest = false;
        this.showContract = true;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  addContract(){
    this.contractService.addContract(this.contract).subscribe(
      (result) => {
        console.log(result);
        if (result == true) {
          window.location.reload();
        }
      }
    )
  }

  back(){
    window.location.reload();
  }
}
