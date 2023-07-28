import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  constructor(private httpClient: HttpClient) { }
  
  signup(appUser: any)
  {
    return this.httpClient.post<any>('http://localhost:9090/admin/signup',appUser)
  }

  login(appUser: any)
  {
    return this.httpClient.post<any>('http://localhost:9090/admin/login',appUser)
  }
  changePassword(appUser: any)
  {
    return this.httpClient.post<any>('http://localhost:9090/admin/changePassword',appUser)
  }
  sendMailOnForgottenPassword(appUser: any)
  {
    return this.httpClient.post<any>
    ('http://localhost:9090/admin/sendMailOnForgottenPassword', appUser);
  }
}
