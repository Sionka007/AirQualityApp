import { Injectable, inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { HardcodeAuthenticationServiceService } from './hardcode-authentication-service.service';

@Injectable({
  providedIn: 'root',
})
export class RouteGuardService {
  static isRouteGuardService(): any[] | undefined {
    throw new Error('Method not implemented.');
  }
  constructor(
    private hardCodeAuthenticateService: HardcodeAuthenticationServiceService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.hardCodeAuthenticateService.isUserLoggedIn()) {
      return true;
    }
    this.router.navigate(['login']);

    return false;
  }

  isRouteGuardService: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean => {
    return inject(RouteGuardService).canActivate(route, state);
  };
}
