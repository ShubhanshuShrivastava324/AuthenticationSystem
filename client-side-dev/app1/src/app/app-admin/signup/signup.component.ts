import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { AdminServiceService } from '../admin-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  signupForm : FormGroup;

  constructor(formBuilder: FormBuilder, private service:AdminServiceService)
  {
    this.signupForm = formBuilder.group({
      
      firstName: new FormControl(),
      lastName: new FormControl(),
      email: new FormControl(),
      password: new FormControl()
    })
  }
  signup()
  {
    this.service.signup(this.signupForm.value).subscribe
    (
      r1 => {
        console.log(r1);
      }
    )
  }
}
