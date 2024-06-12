import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BasicAuthenticationServiceService } from '../basic-authentication-service.service';

@Injectable({
  providedIn: 'root',
})
export class HttpIntercepterService implements HttpInterceptor {
  constructor(
    private basicAuthenticationService: BasicAuthenticationServiceService
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    // let username = 'Szymon';
    // let password = '123';

    // let basicAuthHeaderString =
    //   'Basic ' + window.btoa(username + ':' + password);

    let basicAuthHeaderString =
      this.basicAuthenticationService.getAuthenticatedToken();
    let username = this.basicAuthenticationService.getAuthenticatedUser();

    if (basicAuthHeaderString && username) {
      request = request.clone({
        setHeaders: {
          Authorization: basicAuthHeaderString,
        },
      });
    }

    return next.handle(request);
  }
}
