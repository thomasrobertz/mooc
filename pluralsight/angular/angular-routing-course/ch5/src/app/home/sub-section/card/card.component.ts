import { Component, Input, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute, Router } from '@angular/router';
import { ROUTE_SEGMENTS } from 'src/app/app.routes';
import { Pie } from 'src/app/models/pie';
import { CartService } from 'src/app/services/cart.service';
import { PieService } from 'src/app/services/pie.service';

@Component({
  standalone: true,
  imports: [
    MatButtonModule,
  ],
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input() pie!: Pie;
  readonly cartService = inject(CartService);
  readonly router = inject(Router);
  readonly activatedRoute = inject(ActivatedRoute);

  selectPie(pie: Pie) {
    this.router.navigate([`../${ROUTE_SEGMENTS.PRODUCTS}`, pie.category], {
      relativeTo: this.activatedRoute,
    });
  }
}
