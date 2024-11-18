import { Component } from '@angular/core';
import { Category } from '../models/pie';
import { PieService } from '../services/pie.service';
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
    // Without this, everything would work but we would not see active link css applied (Silent Error)
    // Remember we are in a standalone component so everything has to be imported/declared.
    RouterLinkActive,
  ],
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  readonly Category = Category;

  /* Why this "redclaration" of ROUTE_SEGMENTS despite it already being imported above?
   In Angular, templates can only access properties and methods defined on the
   component instance. Imported constants or variables are not automatically available
    in the template's scope.
    So this is *Template context*, and this behavior is therefore consistent across both
    standalone and non-standalone components (In effect, the declaration is mandatory).
   */
  readonly ROUTE_SEGMENTS = ROUTE_SEGMENTS;

  constructor(
    private readonly pieService: PieService
  ){}

  changeCategory(category: Category){
    this.pieService.setSelectedCategory(category);
  }

}
