import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AddMemberComponent } from './member/add-member/add-member.component';
import { MemberComponent } from './member/member.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/addmember', pathMatch: 'full' },
  { path: 'listmember', component: MemberComponent },
  { path: 'addmember', component: AddMemberComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
