import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from './model/client';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  baseUrl: string = "http://localhost:8080/clients";
  clients: Client[] = [];

  constructor(private http: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseUrl);
  }

  save(cliente: Client): Observable<Client> {
    return this.http.post<Client>(this.baseUrl, cliente);
  }


  delete(cliente: Client): Observable<void> {

    let url = `${this.baseUrl}/${cliente.id}`;

    return this.http.delete<void>(url);


  }

  update(cliente: Client): Observable<Client> {

    let url = `${this.baseUrl}/${cliente.id}`;

    return this.http.put<Client>(url, cliente);
  }
}
