import { Authenticar } from './../models/authenticar';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { error } from 'protractor';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly API = `${environment.API}auth`

  constructor(private httpClient: HttpClient) { }

  login(username: String, password: String) {
    return this.httpClient.get<Authenticar>(`${this.API}/${username}/${password}`);
  }

}
