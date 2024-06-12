import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from '../../app.constansts';

export class HelloWorldBeam {
  constructor(public message: string) {}
}

@Injectable({
  providedIn: 'root',
})
export class WelcomeDataService {
  constructor(private http: HttpClient) {}

  executeHelloWorlWithPathVariable(name: string) {
    return this.http.get<HelloWorldBeam>(`${API_URL}/hello-world/Szymon`);
  }
}
