import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RegisterComponent} from "../register/register.component";

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent implements OnInit {
  fg: any
  infoMatched = false

  constructor(private userService: UserService,
              private toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.fg = new FormGroup({
      userId: new FormControl("", [Validators.required, Validators.pattern("[0-9]+")]),
      mobile: new FormControl("", [Validators.required, Validators.pattern("[0-9+]+")]),
      password: new FormControl("", [Validators.required, Validators.pattern("")]),
    })
  }

  forgot() {
    this.fg.markAllAsTouched();
    if (this.fg.get('userId').valid && this.fg.get('mobile').valid ) {
      this.forgotUser();
    }else {
      this.toastr.info("Form is not valid !")
    }

  }

  private forgotUser() {
    this.userService.forgotUser(this.fg).subscribe(res => {
      console.log(res);
      if (res.statusCode == 200) {
        this.infoMatched = true

        this.toastr.success(res.message)

      }else {
        this.toastr.error(res.message)
      }
    },error => {
      this.toastr.error(error.error.message)

    })
  }

  ifError(con: string) {
    return this.fg.get(con).invalid && this.fg.get(con).touched;
  }


  update() {
    this.fg.markAllAsTouched();
    if (this.fg.valid) {
      this.updateUser();
    }else {
      this.toastr.info("Form is not valid !")
    }

  }

  private updateUser() {
    this.userService.updateUser(this.fg).subscribe(res => {
      console.log(res);
      if (res.statusCode == 200) {
        this.toastr.success(res.message)
        this.router.navigateByUrl("/")
      }else {
        this.toastr.error(res.message)
      }
    },error => {
      this.toastr.error(error.error.message)

    })
  }
}
