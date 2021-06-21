import { ClienteService } from 'src/app/service/cliente.service'
import { ViaCepService } from './../../service/via-cep.service'
import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']

})


export class CadastroComponent implements OnInit {

  cepInvalido: boolean
  pegandoCep: string
  pegandoEmail: string
  pegandoCpf: string

  cadastroForm: FormGroup
  /* telefones: FormArray
 */
  emailNaoPermitido: any
  cpfNaoPermitido: boolean

  telefoneInvalido: boolean = false;

  submitted = false

  logado: boolean = false
  permitido: boolean = false

  constructor(private formBuilder: FormBuilder, private viaCepService: ViaCepService, private clienteService: ClienteService) { }

  ngOnInit(): void {

    var restagarValor = window.localStorage.getItem("tipo")

    if (restagarValor == null) {
      this.logado = false
    } else {
      this.logado = true
    }

    if (restagarValor == '2') {
      this.permitido = false
    } else {
      this.permitido = true
    }


    this.cadastroForm = this.formBuilder.group({
      nome: [null, [Validators.required, Validators.minLength(3)]],
      email: [null, [Validators.required, Validators.email]],
      celular: [null],
      comercial: [null],
      residencial: [null],
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
    return this.cadastroForm.controls
  }

  /*   addItem(): void {
      this.telefones = this.cadastroForm.get('telefones') as FormArray;
      this.telefones.push(this.criarItem());
    }

    criarItem(): FormGroup {
      return this.formBuilder.group({
        telefone: [null, [Validators.required]]
      });
    }

    removerItem(i:number) {
      this.telefones.removeAt(i)
    } */

  buscarEndereco(): void {

    this.viaCepService.buscarEndereco(this.pegandoCep).subscribe(cep => {

      if (cep.logradouro == null || cep.localidade == null) {
        this.cepInvalido = true
        return
      } else {
        this.cepInvalido = false
      }

      this.cadastroForm.controls['logradouro'].setValue(cep.logradouro)
      this.cadastroForm.controls['bairro'].setValue(cep.bairro)
      this.cadastroForm.controls['cidade'].setValue(cep.localidade)
      this.cadastroForm.controls['uf'].setValue(cep.uf)

    }, error => {
      console.log(error)
    })
  }


  cadastrar(): void {

    this.submitted = true

    if (this.cadastroForm.get('celular').value == null && this.cadastroForm.get('comercial').value == null && this.cadastroForm.get('residencial').value == null) {
      this.telefoneInvalido = true;
    } else {
      this.telefoneInvalido = false;
    }

    if (this.cadastroForm.invalid) {
      return
    }

    if (this.cepInvalido == true) {
      return
    }

     if (this.cpfNaoPermitido == false && this.emailNaoPermitido == false && this.telefoneInvalido == false) {
      this.clienteService.cadastrar(this.cadastroForm.value).subscribe(
        data => {
          alert("Cliente cadastrado com sucesso")
          window.location.reload()
        }, error => {
          console.log(error)
        }
      )
    }

  }

  procurarPeloEmail() {
    this.clienteService.procurarEmail(this.pegandoEmail).subscribe(
      sucesso => {
        this.emailNaoPermitido = true
      }, error => {
        this.emailNaoPermitido = false
      }
    )
  }

  procurarPeloCpf() {
    this.clienteService.procurarCpf(this.pegandoCpf).subscribe(
      sucesso => {
        this.cpfNaoPermitido = true
      }, error => {
        this.cpfNaoPermitido = false
      }
    )
  }

}
