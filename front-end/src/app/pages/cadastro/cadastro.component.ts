import { ClienteService } from 'src/app/service/cliente.service';
import { ViaCepService } from './../../service/via-cep.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,  Validators } from '@angular/forms';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']

})


export class CadastroComponent implements OnInit {

  cepInvalido: boolean
  pegandoCep: string

  cadastroForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private viaCepService: ViaCepService, private clienteService: ClienteService) { }

  ngOnInit(): void {

    this.cadastroForm = this.formBuilder.group({
      nome: [null, [Validators.required, Validators.minLength(3)]],
      email: [null, [Validators.required, Validators.email]],
      telefones: [null, [Validators.required]],
      cpf: [null, [Validators.required, Validators.minLength(14)]],
      cep: [null, [Validators.required, Validators.minLength(8)]],
      logradouro: [null, Validators.required],
      bairro: [null, Validators.required],
      cidade: [null, Validators.required],
      uf: [null, Validators.required],
      complemento: [null],
    })
  }



  get f() {
    return this.cadastroForm.controls;
  }



  buscarEndereco(): void {

    const stringFormatada: string = this.pegandoCep.replace("-", "")

    this.viaCepService.buscarEndereco(stringFormatada).subscribe(cep => {

      if (cep.logradouro == null || cep.localidade == null) {
        this.cepInvalido = true;
        return;

      }

        this.cadastroForm.controls['logradouro'].setValue(cep.logradouro);
        this.cadastroForm.controls['bairro'].setValue(cep.bairro);
        this.cadastroForm.controls['cidade'].setValue(cep.localidade);
        this.cadastroForm.controls['uf'].setValue(cep.uf);

    }, error => {
      console.log(error);
    })
  }


  cadastrar(): void {

    this.submitted = true;

    if (this.cadastroForm.invalid) {
      return;
    }

    this.clienteService.cadastrar(this.cadastroForm.value).subscribe(
      data => {
        alert("Cliente cadastrado com sucesso")
      }, error => {
        console.log("error");
      }
    )


    /*    const cadastrar = {
         nome: this.cadastroForm.get('nome').value,
         email: this.cadastroForm.get('email')?.value,
         cpf: this.cadastroForm.get('cpf')?.value,
         cep: this.cadastroForm.get('cep')?.value,
         logradouro: this.cadastroForm.get('logradouro')?.value,
         bairro: this.cadastroForm.get('bairro')?.value,
         cidade: this.cadastroForm.get('cidade')?.value,
         uf: this.cadastroForm.get('uf')?.value,
         complemento: this.cadastroForm.get('complemento')?.value
       }

       console.log(cadastrar);
    */

  }

}
