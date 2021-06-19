import { ViaCepService } from './../../service/via-cep.service';
import { Cliente } from './../../models/cliente'
import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ClienteService } from 'src/app/service/cliente.service'
import { Global } from 'src/app/global';


@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  editForm: FormGroup;
  submitted = false;

  cepInvalido: boolean

  pegandoCep: string

  cliente: Array<Cliente>

  searchText: string;

  admin: boolean;

  logado: boolean = false

  constructor(private formBuilder: FormBuilder, private clienteService: ClienteService, private viaCepService: ViaCepService, private global: Global) { }

  ngOnInit(): void {

    var restagarValor = window.localStorage.getItem("tipo")

    if (restagarValor == null) {
      this.logado = false
    } else {
      this.logado = true
    }

    if (restagarValor == "2") {
      this.admin = false
    }

    if (restagarValor == "1") {
      this.admin = true
    }


    this.clienteService.buscarTodos().subscribe(
      cli => {
        this.cliente = cli;
      }, error => {
        console.log(error)
      }
    )

    this.editForm = this.formBuilder.group({
      nome: [null],
      email: [null, [Validators.required, Validators.email]],
      telefones: [null, [Validators.required]],
      cpf: [null],
      cep: [null, [Validators.required, Validators.minLength(8)]],
      logradouro: [null, Validators.required],
      bairro: [null, Validators.required],
      cidade: [null, Validators.required],
      uf: [null, Validators.required],
      complemento: [null],
    })

  }

  excluirCliente(cliente: Cliente) {

    this.clienteService.excluir(cliente.id).subscribe(cli => {
      alert("Cliente excluido com sucesso")
      this.ngOnInit();
    }, error => {
      console.log("error")
    })

  }

  carregarInfoModal(cliente: Cliente) {

    this.editForm = this.formBuilder.group({
      id: [cliente.id],
      nome: [cliente.nome],
      email: [cliente.email, [Validators.required, Validators.email]],
      telefones: [cliente.telefones, [Validators.required]],
      cpf: [cliente.cpf],
      cep: [cliente.cep, [Validators.required, Validators.minLength(8)]],
      logradouro: [cliente.logradouro, Validators.required],
      bairro: [cliente.bairro, Validators.required],
      cidade: [cliente.cidade, Validators.required],
      uf: [cliente.uf, Validators.required],
      complemento: [cliente.complemento],
    })
  }

  atualizar(client: Cliente) {

    this.submitted = true;

    if (this.editForm.invalid) {
      return;
    }

    this.clienteService.atualizar(client.id, client).subscribe(
      att => {
        alert("Cliente atualizado com sucesso")
        this.ngOnInit();
      }, error => {
        console.log("error");
      }
    )

  }

  buscarEndereco(): void {

    this.viaCepService.buscarEndereco(this.pegandoCep).subscribe(cep => {
      if (cep.logradouro == null || cep.localidade == null) {
        this.cepInvalido = true;
        return;
      } else {
        this.cepInvalido = false;
      }
      this.editForm.controls['logradouro'].setValue(cep.logradouro);
      this.editForm.controls['bairro'].setValue(cep.bairro);
      this.editForm.controls['cidade'].setValue(cep.localidade);
      this.editForm.controls['uf'].setValue(cep.uf);

    }, error => {
      console.log(error);
    })
  }


  get f() {
    return this.editForm.controls;
  }
}
