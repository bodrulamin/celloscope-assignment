import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private rout: Router, private toastr:ToastrService) { }

  ngOnInit(): void {
  }
  logout(){
   this.toastr.warning("Logged out successfuly !")
    this.rout.navigateByUrl("/")
  }

}
