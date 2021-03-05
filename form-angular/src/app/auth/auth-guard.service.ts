import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router
 } from '@angular/router';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';


@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
  Observable<boolean> | Promise<boolean> | boolean {
    
    return this.authService.isAuthenticated()
    .then((authenticated) => {
      if (authenticated == true) {
        return true;
      } else {
        console.log("Access Denied...");
        // alert("Login gagal!");
        this.router.navigate(['/']);
        return false;
      }
    });
    
  }

}