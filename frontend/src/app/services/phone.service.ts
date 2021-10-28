import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from '../../environments/environment';
import {BehaviorSubject} from "rxjs";
import {CustomerPhone} from "../interfaces/customer-phone";

@Injectable({
  providedIn: 'root'
})
export class PhoneService {
  phones: BehaviorSubject<CustomerPhone[]> = new BehaviorSubject<CustomerPhone[]>([]);

  constructor(private http: HttpClient) { }

  doGetPhones() {
    this.http.get<CustomerPhone[]>(`${environment.apiUrl}/phones`)
      .subscribe(c => {
        if (c !== null && c !== undefined) {
          this.phones.next(c);
        }
      });
  }

  getPhones() {
    return this.phones.asObservable();
  }

  doGetPhonesWithFilters(filteredObject: CustomerPhone) {
    this.http.post<CustomerPhone[]>(`${environment.apiUrl}/phones`, filteredObject)
      .subscribe(c => {
        if (c !== null && c !== undefined) {
          this.phones.next(c);
        }
      });
  }

  getPhonesWithFilters() {
    return this.phones.asObservable();
  }
}
