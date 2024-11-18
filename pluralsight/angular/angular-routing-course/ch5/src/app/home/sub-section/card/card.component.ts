import { Component, Input, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { Pie } from 'src/app/models/pie';
import { CartService } from 'src/app/services/cart.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ROUTE_SEGMENTS } from 'src/app/app.routes';

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

  //readonly activatedRoute = inject(ActivatedRoute);

  // Programmatic navigation
  selectPie(pie: Pie) {
    // We don't need to redeclare ROUTE_SEGMENTS here, we're just using as a string for
    // programmatic navigation, not in a template.
    this.router.navigate([`../${ROUTE_SEGMENTS.PRODUCTS}`], /*{
      For future changes in route
      relativeTo: this.activatedRoute
    }*/);
  }
}
