import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Endereco } from '../models/endereco';

@Injectable({
  providedIn: 'root'
})
export class ViaCepService {

  private readonly API = `${environment.API}api`
  constructor(private httpClient: HttpClient) { }

  buscarEndereco(cep: string) {
    return this.httpClient.get<Endereco>(`${this.API}/${cep}`);
  }

}
