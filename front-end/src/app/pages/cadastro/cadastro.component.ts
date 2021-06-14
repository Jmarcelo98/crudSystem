import { Cliente } from './../../models/cliente';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']

})


export class CadastroComponent implements OnInit {

  private cliente: Cliente

  constructor() { }

  cadastroForm: FormGroup = new FormGroup({
    nome: new FormControl('', Validators.required),
    cpf: new FormControl('', Validators.required),
    cep: new FormControl('', Validators.required),

  });


  ngOnInit(): void {
  }



}
