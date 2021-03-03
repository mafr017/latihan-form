import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MemberComponent } from './member/member.component';
import { AddMemberComponent } from './member/add-member/add-member.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './auth/auth.service';
import { AuthGuardService } from './auth/auth-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MemberComponent,
    AddMemberComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    AuthService,
    AuthGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
