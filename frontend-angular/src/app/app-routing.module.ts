import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ForgotComponent} from "./components/forgot/forgot.component";
import {WelcomeComponent} from "./components/welcome/welcome.component";

const routes: Routes = [
  {path: "" , component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "welcome", component: WelcomeComponent},
  {path: "forgot", component: ForgotComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
