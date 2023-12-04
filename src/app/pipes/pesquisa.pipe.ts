import { Pipe, PipeTransform } from '@angular/core';
import { Client } from '../model/client';

@Pipe({
  name: 'pesquisa'
})
export class PesquisaPipe implements PipeTransform {

  transform(clients: Client[], searchText: string): Client[] {
    if (searchText.trim() === '') {
      return clients;
    }

    searchText = searchText.toLowerCase();

    return clients.filter((client) => {

      const searchedClient = client.name;
      return searchedClient.toLowerCase().includes(searchText);


    });

  }

}
