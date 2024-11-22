import { Component, Input, inject } from '@angular/core';
import { DetailViewComponent } from './detail-view/detail-view.component';
import { SideMenuComponent } from './side-menu/side-menu.component';

import { PieService } from '../services/pie.service';

@Component({
  standalone: true,
  imports: [
    DetailViewComponent,
    SideMenuComponent,
  ],
  selector: 'app-products-view',
  templateUrl: './products-view.component.html',
  styleUrls: ['./products-view.component.css']
})
export class ProductsViewComponent {
  /*
    We added the categoryId parameter to the route. E.g.:

        <button mat-menu-item
            [routerLink]="[ROUTE_SEGMENTS.PRODUCTS, Category.SEASONAL_PIE]"

    So ProductsViewComponent is where we'll "land". That's why we need to consume the
    path parameter here.
  */

  // Consume the categoryId path parameter and set category. Not the name must match (categoryId).
  @Input() set categoryId(val: string) {
    this.pieService.setSelectedCategory(val);
  }

  // Moved here from header.component
  private readonly pieService = inject(PieService);
}
