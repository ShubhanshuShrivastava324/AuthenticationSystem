import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app1';

  isLoggedIn()
  {
    var status = false;
    if(sessionStorage.getItem("emial"))
    {
      status = true;
    }
    return status;
  }
}
