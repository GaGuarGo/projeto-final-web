import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ClientComponent } from './client/client.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MascaraTelefoneDirective } from './masks/mascara-telefone.directive';

import localePt from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { PesquisaPipe } from './pipes/pesquisa.pipe';
import { CepMaskDirective } from './masks/mascara-cep.directive';


registerLocaleData(localePt);


@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    MascaraTelefoneDirective,
    CepMaskDirective,
    PesquisaPipe,

  ],
  imports: [
    BrowserModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [{
    provide: LOCALE_ID, useValue: 'pt'
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
