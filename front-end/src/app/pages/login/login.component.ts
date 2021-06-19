import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { Router } from '@angular/router'
import { Global } from 'src/app/global'
import { Authenticar } from 'src/app/models/authenticar'
import { LoginService } from 'src/app/service/login.service'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup

  authenticar: Array<Authenticar>

  notFound = false;

  submitted = false;

  constructor(private loginService: LoginService, private formBuilder: FormBuilder, private router: Router, private global: Global) { }

  ngOnInit(): void {

    this.loginForm = this.formBuilder.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]]
    })
  }


  get f() {
    return this.loginForm.controls
  }

  login() {

    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    this.loginService.login(this.loginForm.get('username').value, this.loginForm.get('password').value).subscribe(
      login => {
        this.notFound = false
        this.global.setarLocalStorage("tipo", login.type)
        window.location.href="/"
      }, error => {
        this.notFound = true
      })
  }



}
