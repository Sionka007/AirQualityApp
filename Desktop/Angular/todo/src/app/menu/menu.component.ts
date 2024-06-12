import { Component, SimpleChanges } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { HardcodeAuthenticationServiceService } from '../service/hardcode-authentication-service.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink, NgIf],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
})
export class MenuComponent {
  // isUserLoggedIn: boolean = false;
  constructor(
    public hardcodeAuthenticateService: HardcodeAuthenticationServiceService
  ) {}

  ngOnInit(): void {
    // this.isUserLoggedIn = this.hardcodeAuthenticateService.isUserLoggedIn();
  }
}
