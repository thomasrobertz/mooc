import { Component } from '@angular/core';
import { Category } from '../models/pie';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { RouterLink, RouterLinkActive } from '@angular/router';

import { ROUTE_SEGMENTS } from '../app.routes';

@Component({
  standalone: true,
  imports: [
    MatMenuModule,
    MatButtonModule,
    RouterLink,
    RouterLinkActive,
  ],
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  readonly Category = Category;
  readonly ROUTE_SEGMENTS = ROUTE_SEGMENTS;

/* We can remove this code. It was for handling the click events from the categories.
 But now we want to use path parameter categoryId to drive state change.

 We will use the Router for this (It will become Single Source Of Truth for the state):
  -It is already a globally available service
  -It is already concerned with routing
  -It can drive state changes
  -It already accepts parameters and allows to consume them

So all we need to do is add some form of storage to the Router, effectively
making the routerLink parameters (like categoryId) available globally.

  constructor(
    private readonly pieService: PieService
  ){}

  changeCategory(category: Category){
    this.pieService.setSelectedCategory(category);
  }
*/

}
