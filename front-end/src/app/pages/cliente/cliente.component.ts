import { Cliente } from './../../models/cliente';
import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/service/cliente.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  constructor(private clienteService: ClienteService) { }

  cliente: Array<Cliente> = []

  ngOnInit(): void {
  }

  buscarTodos() {
    this.clienteService.buscarTodos().subscribe(
      cli => {
        this.cliente = cli;
        console.log(this.cliente);
      }, error => {
        console.log(error);

      }
    )
  }

}
