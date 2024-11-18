import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProductsViewComponent } from './products-view/products-view.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { NotFoundComponent } from './not-found/not-found.component';

export enum ROUTE_SEGMENTS {
  HOME = "home",
  PRODUCTS = "products",
  CONTACT = "contact",
  ABOUT = "about"
}

export const ROUTES: Routes = [
  {
    path: '',
    redirectTo: ROUTE_SEGMENTS.HOME,
    pathMatch: 'full',
  },
  {
    path: ROUTE_SEGMENTS.HOME,
    component: HomeComponent,
  },
  {
    path: ROUTE_SEGMENTS.PRODUCTS,
    component: ProductsViewComponent,
  },
  {
    path: ROUTE_SEGMENTS.CONTACT,
    component: ContactComponent,
  },
  {
    path: ROUTE_SEGMENTS.ABOUT,
    component: AboutComponent,
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];
