import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormGroup} from "@angular/forms";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {
  }

  host = "http://localhost:8080"
  userEndpoint = this.host+ "/api/v1/users"
  loginEndpoint = this.host+ "/login"
  forgotEndpoint = this.host+ "/forgot"

  header = {"Content-Type": "application/json"}

  registerUser(form: FormGroup): Observable<any> {
    return this.http.post(this.userEndpoint, JSON.stringify(form.value), {headers: this.header})
  }

  loginUser(form: FormGroup): Observable<any> {
    return this.http.post(this.loginEndpoint, JSON.stringify(form.value), {headers: this.header})
  }

  forgotUser(form: FormGroup): Observable<any> {
    return this.http.post(this.forgotEndpoint, JSON.stringify(form.value), {headers: this.header})
  }

  updateUser(form: FormGroup): Observable<any> {
    return this.http.put(this.userEndpoint, JSON.stringify(form.value), {headers: this.header})
  }
}
