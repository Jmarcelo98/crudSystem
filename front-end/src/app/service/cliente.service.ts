import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from '../models/cliente';

const URL = 'http://localhost:8080/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpClient: HttpClient) { }

  buscarTodos() {
    return this.httpClient.get<Cliente[]>(URL);
  }


}
