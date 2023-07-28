import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppAdminModule } from './app-admin/app-admin.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: 
  [
    AppComponent
  ],

  
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppAdminModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }