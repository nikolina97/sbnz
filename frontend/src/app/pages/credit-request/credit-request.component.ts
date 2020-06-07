import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CreditRequestService } from '../../services/credit-request.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-credit-request',
  templateUrl: './credit-request.component.html',
  styleUrls: ['./credit-request.component.css']
})
export class CreditRequestComponent implements OnInit {

  private form = this.fb.group ({
    sum : [null, Validators.required],
    months : [null, Validators.required],
    creditType : [null, Validators.required],
    guaranteeType : [null, Validators.required]
})

private formGuarantee = this.fb.group ({
  zone : [null, Validators.required],
  realEstateType : [null, Validators.required],
  inputSquareFootage : [null, Validators.required]
})

basicDisplayed = true;
guaranteeDisplayed = false;
fullRequest  = false;

realEstates : any[] = []
creditRequest : any;
contract : any;

constructor(private fb: FormBuilder, private creditRequestService : CreditRequestService, private toastr: ToastrService) { }

ngOnInit(): void {
  console.log("Basic ", this.basicDisplayed);
  console.log("Guarantee ", this.guaranteeDisplayed);

}

submit(){
  this.creditRequest = {
    sumOfMoney : this.form.value.sum,
    monthlyPaymentPeriod : this.form.value.months,
    creditType : this.form.value.creditType
  }
  console.log(this.creditRequest);
  this.creditRequestService.checkBasic(this.creditRequest).subscribe(
    (result) => {
      alert(result['description']);
      if (result['accepted'] == true) {
        this.basicDisplayed = false;
        console.log(this.form.value.guaranteeType);
        if (this.form.value.guaranteeType == 'nekretnina') {
          this.guaranteeDisplayed = true;
        }
        // window.location.reload();
      }
      
    },
    (error) => {
      console.log(error)
      alert(error.error)
    }
  )
}

submitGuarantee() {
  var realEstate = {
    zone : this.formGuarantee.value.zone,
    realEstateType : this.formGuarantee.value.realEstateType,
    squareFootage : this.formGuarantee.value.inputSquareFootage,
    price : 0.0
  }
  this.realEstates.push(realEstate);
  this.formGuarantee.reset();
}

checkWarrantly() {
  var warrantly = {
    guarantor : null,
    insurance : false,
    realEstate : this.realEstates
  }
  this.creditRequest.warrantly = warrantly;
  console.log("Request ", this.creditRequest);
  this.creditRequestService.checkWarrantly(this.creditRequest).subscribe(
    (result) => {
      console.log(result);
      alert(result['description']);
      this.contract = result['contract'];
      this.guaranteeDisplayed = false;
      this.fullRequest = true;
    },
    (error) => {
      console.log(error);
    }
  )
}

}
