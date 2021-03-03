import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { Users } from '../model/users';

@Injectable()
export class AuthService {
  loggedIn = false;
  users = new Users();
  
  constructor(private router: Router, private httpClient: HttpClient) { }
  
  login(usrn: string, pasw: string) {
    this.users.username = usrn;
    this.users.password = pasw;
    this.users.islogin = true;

    this.httpClient.post(environment.baseUrl + 'loginjson', this.users).pipe(map( data => data ))
    .subscribe((data : any) => {
      console.log("ini " + data.user.islogin);
      this.loggedIn = data.user.islogin;
      this.router.navigate(["/listmember"]);
    });
  }

  logout() {
    this.users.islogin = false;
    this.httpClient.post(environment.baseUrl + 'logoutjson', this.users).subscribe();
    this.users = new Users();
    this.loggedIn = false;
    this.router.navigate(["/"]);
  }

  isAuthenticated() {
    const promise = new Promise(
      (resolve, reject) => { resolve(this.loggedIn) }
    );
    return promise;
  }
}
