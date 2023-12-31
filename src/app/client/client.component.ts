import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientService } from '../client.service';
import { Client } from '../model/client';
import { PesquisaPipe } from '../pipes/pesquisa.pipe';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'],
  providers: [PesquisaPipe]
})
export class ClientComponent {

  clients: Client[] = [];
  filteredClients: Client[] = [];
  selectedClient: Client = {} as Client;
  formGroupClient: FormGroup;
  isEditing: Boolean = false;
  submited: Boolean = false;
  search: string = "";


  ngOnInit(): void {
    this.getClients();

  }

  constructor(private service: ClientService, private formBuilder: FormBuilder, private pesquisaPipe: PesquisaPipe) {


    this.formGroupClient = formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(5)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required,]],
      address: ['', [Validators.required, Validators.minLength(5)]],
      city: ['', [Validators.required, Validators.minLength(2)]],
      cep: ['', [Validators.required, Validators.maxLength(9)]],
      state: ['', [Validators.required, Validators.maxLength(2)]],

    });

    

  }

  getClients(): void {
    this.service.getClients().subscribe({
      next: (clients) => {
        this.clients = clients
        this.filteredClients = this.clients;
      }
    });
  }

  limparPesquisa(): void {

    // this.filteredClients = [];
    this.clients = [];
    this.search = "";
    this.getClients();

  }


  pesquisar() {
    this.clients = this.pesquisaPipe.transform(this.clients, this.search);
  }

  save() {

    this.submited = true;

    console.log(this.formGroupClient.valid);

    if (this.formGroupClient.valid) {

      if (this.isEditing == false) {


        let client = this.formGroupClient.value;

        this.service.save(client).subscribe({
          next: client => {
            this.clients.push(client);
            this.formGroupClient.reset();
            this.submited = false;
          }
        });

      } else {


        this.selectedClient.name = this.formGroupClient.get("name")?.value;
        this.selectedClient.email = this.formGroupClient.get("email")?.value;
        this.selectedClient.phone = this.formGroupClient.get("phone")?.value;
        this.selectedClient.address = this.formGroupClient.get("address")?.value;
        this.selectedClient.city = this.formGroupClient.get("city")?.value;
        this.selectedClient.cep = this.formGroupClient.get("cep")?.value;
        this.selectedClient.state = this.formGroupClient.get("state")?.value;


        this.service.update(this.selectedClient).subscribe({
          next: () => {
            this.formGroupClient.reset();
            this.isEditing = false;
            this.submited = false;
          }
        });
      }
    }
  }

  remove(client: Client) {

    this.service.delete(client).subscribe({
      next: () => {
        this.clients = this.clients.filter((c) => c !== client);
        this.filteredClients = this.clients;
      }
    });

  }


  alterar(client: Client) {

    this.isEditing = true;

    this.selectedClient = client;

    this.formGroupClient.setValue({
      name: client.name,
      email: client.email,
      phone: client.phone,
      address: client.address,
      city: client.city,
      cep: client.cep,
      state: client.state,
      search: "",
    });
  }

  get name(): any {
    return this.formGroupClient.get("name");
  }
  get email(): any {
    return this.formGroupClient.get("email");
  }
  get phone(): any {
    return this.formGroupClient.get("phone");
  }
  get address(): any {
    return this.formGroupClient.get("address");
  }
  get city(): any {
    return this.formGroupClient.get("city");
  }

  get cep(): any {
    return this.formGroupClient.get("cep");
  }
  get state(): any {
    return this.formGroupClient.get("state");
  }
}
