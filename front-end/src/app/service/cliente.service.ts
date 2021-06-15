import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Cliente } from '../models/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private readonly API = `${environment.API}cliente`
  constructor(private httpClient: HttpClient) { }

  buscarTodos() {
    return this.httpClient.get<Cliente[]>(this.API);
  }

  cadastrar(cliente: Cliente) {
    return this.httpClient.post(this.API, cliente)
  }

}
