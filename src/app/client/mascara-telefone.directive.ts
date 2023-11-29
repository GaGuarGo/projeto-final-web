import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appMascaraTelefone]'
})
export class MascaraTelefoneDirective {

  constructor(private el: ElementRef) { }

  @HostListener('input', ['$event']) onInput(event: any) {
    const valorInput = event.target.value;
    const valorComMascara = this.aplicarMascara(valorInput);
    event.target.value = valorComMascara;
  }

  aplicarMascara(valor: string): string {
    valor = valor.replace(/\D/g, ''); // Remove todos os caracteres não numéricos
    if (valor.length <= 10) {
      return this.formatarTelefone(valor);
    } else {
      return valor.substring(0, 11); // Limita o tamanho máximo a 10 dígitos
    }
  }

  formatarTelefone(valor: string): string {
    const match = valor.match(/^(\d{0,2})(\d{0,4})(\d{0,4})$/);
    if (match) {
      const partes = match.slice(1).filter(Boolean);
      return partes.join('-'); // Formata como "XX-XXXX-XXXX"
    }
    return valor;
  }
}
