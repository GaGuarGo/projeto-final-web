import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[cepMask]'
})
export class CepMaskDirective {

  constructor(private el: ElementRef) {}

  @HostListener('input', ['$event'])
  onInput(event: any) {
    const input = event.target;
    let value = input.value.replace(/\D/g, ''); // Remove todos os caracteres não numéricos

    // Limita o tamanho do texto para 9 caracteres
    if (value.length > 8) {
      value = value.slice(0, 8);
    }

    if (value.length <= 5) {
      input.value = value.replace(/^(\d{0,5})/, '$1');
    } else {
      input.value = value.replace(/^(\d{0,5})(\d{0,3})/, '$1-$2');
    }
  }
}
