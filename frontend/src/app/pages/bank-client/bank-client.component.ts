import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CreditRequestService } from '../../services/credit-request.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-bank-client',
  templateUrl: './bank-client.component.html',
  styleUrls: ['./bank-client.component.css']
})
export class BankClientComponent implements OnInit {


  constructor() { }

  ngOnInit(): void {
    

  }


}
