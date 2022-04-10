import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  fg: any
  constructor() { }

  ngOnInit(): void {
    this.fg = new FormGroup({
      userId: new FormControl([Validators.required]),
      mobile: new FormControl([Validators.required]),
      password: new FormControl([Validators.required])
    })
  }
  register() {
    this.fg.markAllAsTouched();

  }

  ifError(con: string) {
    return this.fg.get(con).invalid && this.fg.get(con).touched;
  }
}
