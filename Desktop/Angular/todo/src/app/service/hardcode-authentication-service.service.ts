import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class HardcodeAuthenticationServiceService {
  constructor() {}

  authenticate(username: string, password: string) {
    console.log('before ' + this.isUserLoggedIn());

    if (username === 'Szymon' && password === '123') {
      sessionStorage.setItem('authenticateUser', username);
      console.log('after ' + this.isUserLoggedIn());
      return true;
    }
    return false;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('authenticateUser');

    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem('authenticateUser');
  }
}
