import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  fg: any

  constructor(private userService: UserService,
              private toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.fg = new FormGroup({
      userId: new FormControl("", [Validators.required, Validators.pattern("[0-9]+")]),
      mobile: new FormControl("", [Validators.required, Validators.pattern("[0-9+]+")]),
      password: new FormControl("", [Validators.required, Validators.pattern("")])
    })
  }

  register() {
    this.fg.markAllAsTouched();
    if (this.fg.valid) {
      this.registerUser();
    }else {
      this.toastr.info("Form is not valid !")
    }

  }

  private registerUser() {
    this.userService.registerUser(this.fg).subscribe(res => {
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

  ifError(con: string) {
    return this.fg.get(con).invalid && this.fg.get(con).touched;
  }
}
