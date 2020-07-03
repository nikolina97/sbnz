import { Component, OnInit } from '@angular/core';
import { ReportService } from 'src/app/services/report.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  creditCount : any[];
  largestCredits : any[];
  smallestCredits : any[];
  activeClients : any[];

  table1 : boolean = true;
  table2 : boolean = false;
  table3 : boolean = false;
  table4 : boolean = false;

  constructor(private reportService : ReportService) { }

  ngOnInit(): void {
    this.getCreditCount();
  }

  getCreditCount(){
    this.reportService.countByType().subscribe(
      (result) => {
        this.creditCount = result['list'];
      }
    )  
  }

  getLargestCredits() {
    this.reportService.largestLoan().subscribe(
      (result) => {
        this.largestCredits = result['contracts'];
      }
    )
  }

  getSmallestCredits(){
    this.reportService.smallestLoan().subscribe(
      (result) => {
        this.smallestCredits = result['contracts'];
      }
    )
  }

  getMostActiveClients(){
    this.reportService.mostActiveClients().subscribe(
      (result) => {
        this.activeClients = result['clients'];
      }
    )
  }

  table1Method(){
    this.table1 = true;
    this.table2 = false;
    this.table3 = false;
    this.table4 = false;
    this.getCreditCount();
  }

  table2Method(){
    this.table1 = false;
    this.table2 = true;
    this.table3 = false;
    this.table4 = false;
    this.getLargestCredits();
  }

  table3Method(){
    this.table1 = false;
    this.table2 = false;
    this.table3 = true;
    this.table4 = false;
    this.getSmallestCredits();
  }

  table4Method(){
    this.table1 = false;
    this.table2 = false;
    this.table3 = false;
    this.table4 = true;
    this.getMostActiveClients();
  }

}
