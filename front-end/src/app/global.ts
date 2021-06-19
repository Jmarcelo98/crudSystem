export class Global {

  global: Global

  constructor() { }

  setarLocalStorage(key: string, value: number) {
    window.localStorage.setItem(key, JSON.stringify(value))
  }

  removerLocalStorage() {
    window.localStorage.removeItem("tipo");
    window.location.reload()
  }



}
