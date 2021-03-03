import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  title = 'form-angular';

  constructor(public authService: AuthService) { }

  onLogout() {
    this.authService.logout();
  }
}
