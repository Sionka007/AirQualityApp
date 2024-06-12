import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HardcodeAuthenticationServiceService } from '../service/hardcode-authentication-service.service';
import { BasicAuthenticationServiceService } from '../service/basic-authentication-service.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  username = 'Szymon';
  password = '';
  errorMessage = 'Invalid Credential';

  invalidLogin = false;

  //Dependency injection, Router: login page ---> welcome page

  constructor(
    private router: Router,
    public hardocodeAuthenticateService: HardcodeAuthenticationServiceService,
    public basicAuthenticateService: BasicAuthenticationServiceService
  ) {}

  handleLogin() {
    // console.log(this.username);
    if (
      this.hardocodeAuthenticateService.authenticate(
        this.username,
        this.password
      )
    ) {
      this.router.navigate(['welcome', this.username]);
      this.invalidLogin = false;
    } else {
      this.invalidLogin = true;
    }
  }

  handleJwtAuthLogin() {
    this.basicAuthenticateService
      .executeJWTAuthenticationService(this.username, this.password)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.router.navigate(['welcome', this.username]);
          this.invalidLogin = false;
        },
        error: (error) => {
          console.log(error);
          this.invalidLogin = true;
        },
      });
  }
}
