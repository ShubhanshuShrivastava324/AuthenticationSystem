import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { AdminServiceService } from '../admin-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  login: FormGroup;
  forgottenPasswordEnabled: boolean = false;
  forgottenPasswordForm: FormGroup;
loginForm: FormGroup<any> | undefined;

  constructor(formBuilder: FormBuilder,private service:AdminServiceService)
  {
    this.login = formBuilder.group({
      email: new FormControl(),
      password: new FormControl()
  
    });
    this.forgottenPasswordForm = formBuilder.group({
      email: new FormControl()
    });
  }
  authenticate()
  {
    this.service.login(this.login.value).subscribe(
      r1=>{
        console.log(r1);
        if(r1.status)
        {
          console.log(this.login.value["email"]);
          sessionStorage.setItem("email",this.login.value["email"]);
        }
      }
    )
  }
  sendMailOnForgottenPassword()
  {
    this.service.sendMailOnForgottenPassword(this.forgottenPasswordForm.value).subscribe(
      r1=>{
        console.log(r1);
      }
    )
  }


forgottenPassword()
{
  this.forgottenPasswordEnabled = !this.forgottenPasswordEnabled;
}

}
  // login()
  // {
  //   this.service.signup(this.loginForm.value).subscribe(
  //     r1 =>{
  //       console.log(r1);
  //     }
  //   )
  // }