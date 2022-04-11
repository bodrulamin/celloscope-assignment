import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  fg: any

  constructor(private userService: UserService, private router: Router, private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.fg = new FormGroup({
      userId: new FormControl("", [Validators.required, Validators.pattern("[0-9]+")]),
      password: new FormControl("", [Validators.required, Validators.pattern("")])
    })
  }

  login() {
    this.fg.markAllAsTouched();
    if (this.fg.valid) {
      this.loginUser();
    } else {
      this.toastr.info("Form is not valid")
    }

  }

  private loginUser() {
    this.userService.loginUser(this.fg).subscribe(res => {
      console.log(res);
      if (res.statusCode == 200) {
        this.toastr.success(res.message)
        this.router.navigateByUrl("/welcome")
      } else {
        this.toastr.error(res.message)
      }
    }, error => {
      this.toastr.error(error.error.message)
    })
  }

  ifError(con: string) {
    return this.fg.get(con).invalid && this.fg.get(con).touched;
  }
}
