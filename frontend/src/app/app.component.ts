import {Component, OnInit} from '@angular/core';
import {PhoneService} from "./services/phone.service";
import {CustomerPhone} from "./interfaces/customer-phone";
import {Vars} from "./classes/vars";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'phonevalidation';
  phones: CustomerPhone[] = [];
  filteredObject: CustomerPhone = {};
  phoneStateEnum = Vars.EnumPhoneStateStatus();
  countryEnum = Vars.EnumCountry();
  displayedColumns: string[] = ['countryName', 'countryCode', 'state', 'phoneNumber'];

  constructor(private phoneService: PhoneService) {
  }
  ngOnInit(): void {
    this.phoneService.doGetPhones();
    this.phoneService.getPhones()
      .subscribe(res => {
        if(res !== null && res !== undefined){
          this.phones = res;
          console.log(this.phones)
        }
      });
  }
  filterChange(value: string, key: string){
    console.log(this.filteredObject)
    switch (key) {
      case 'countryName':
        this.filteredObject.countryName = value;
        break;
      case 'state':
        value === 'valid' ? this.filteredObject.state = true : this.filteredObject.state = false;
        break;
    }

    this.phoneService.doGetPhonesWithFilters(this.filteredObject);
    this.phoneService.getPhonesWithFilters()
      .subscribe(res => {
        if(res !== null && res !== undefined){
          this.phones = res;
          console.log(this.phones)
        }
      });
  }
}
