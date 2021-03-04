import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './auth/auth-guard.service';
import { AuthService } from './auth/auth.service';
import { LoginComponent } from './login/login.component';
import { AddMemberComponent } from './member/add-member/add-member.component';
import { MemberComponent } from './member/member.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'listmember', canActivate: [AuthGuardService], component: MemberComponent },
  { path: 'addmember', component: AddMemberComponent },
  { path: 'addmember/:id', component: AddMemberComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
    AuthGuardService,
    AuthService
  ]
})
export class AppRoutingModule { }
