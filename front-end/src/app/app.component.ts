import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Global } from './global';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'front-end';

  tipoLogado: number = 0

  logado: boolean = false

  resgatarValor: string;

  constructor(private global: Global, public router: Router) { }

  ngOnInit(): void {

    this.resgatarValor = window.localStorage.getItem("tipo")

    this.tipoLogado = parseInt(this.resgatarValor)

    if (this.tipoLogado == 1) {
      this.logado = true
    } else if (this.tipoLogado == 2) {
      this.tipoLogado = 2
      this.logado = true
    }
  }

  logout() {
    this.global.removerLocalStorage()
    this.router.navigateByUrl("/")
  }

}
