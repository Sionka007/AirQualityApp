import { Component } from '@angular/core';
import { HardcodeAuthenticationServiceService } from '../service/hardcode-authentication-service.service';

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css',
})
export class LogoutComponent {
  constructor(
    private hardcodeAuthenticateService: HardcodeAuthenticationServiceService
  ) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.hardcodeAuthenticateService.logout();
  }
}
