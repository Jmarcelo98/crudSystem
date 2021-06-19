import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule}  from '@angular/common';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaginaNaoEncontradaComponent } from './pages/pagina-nao-encontrada/pagina-nao-encontrada.component';
import { ClienteComponent } from './pages/cliente/cliente.component';
import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { Global } from './global';
import { NaoLogadoComponent } from './components/nao-logado/nao-logado.component';
import { NaoAutorizadoComponent } from './components/nao-autorizado/nao-autorizado.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ClienteComponent,
    HomeComponent,
    PaginaNaoEncontradaComponent,
    CadastroComponent,
    NaoLogadoComponent,
    NaoAutorizadoComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule
  ],
  providers: [Global],
  bootstrap: [AppComponent]
})
export class AppModule { }
