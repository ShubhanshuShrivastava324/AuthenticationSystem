import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './app-admin/signup/signup.component';
import { LoginComponent } from './app-admin/login/login.component';

const routes: Routes = 
[
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
