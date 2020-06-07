import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, FormArray, Validators } from '@angular/forms';
import { ClientService } from '../../../services/client.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent implements OnInit {

  private form = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    username: ['', Validators.required],
    password: ['', Validators.required],
    dateOfBirth: [null, Validators.required],
    jmbg: ['', Validators.required],
    accountNumber: [null, Validators.required],
    monthlyIncome: [null, Validators.required],
    monthlyOutcome: [null, Validators.required]
  });

  constructor(private fb: FormBuilder, private clientService : ClientService) { }

  ngOnInit(): void {
  }

  addClient() {
    let dateOfBirth: Date = new Date(this.form.get('dateOfBirth').value);
    let client = {
      firstName: this.form.value.firstName,
      lastName: this.form.value.lastName,
      jmbg: this.form.value.jmbg,
      account: {
        accountNumber : this.form.value.accountNumber,
        balance : 0
      },
      dateOfBirth : dateOfBirth.getTime(),
      monthlyIncome : this.form.value.monthlyIncome,
      monthlyOutcome : this.form.value.monthlyOutcome,
      rewardPoints: 0,
      username: this.form.value.username,
      password  : this.form.value.password
    }

    this.clientService.addNewClient(client).subscribe(
      (result) =>{
        console.log(result);
      }
    )
  }

}
