import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { ProductsViewComponent } from './products-view/products-view.component';
import { PRODUCT_ROUTES } from './products-view/products.routes';
import { CartComponent } from './cart/cart.component';

export enum ROUTER_TOKENS {
  HOME = 'home',
  SHOP = 'shop',
  CONTACT = 'contact',
  ABOUT = 'about',
  CHECKOUT = 'checkout',
  CART = 'cart',
}

export const ROUTES: Routes = [
  {
    path: '',
    redirectTo: ROUTER_TOKENS.HOME,
    pathMatch: 'full',
  },
  {
    path: ROUTER_TOKENS.HOME,
    component: HomeComponent,
  },
  {
    path: `${ROUTER_TOKENS.SHOP}/:categoryId`,

    // If we kept this, we wouldn't be lazy loading, the component would be loaded instantly by the router instead.
    // So we move this to PRODUCT_ROUTES from products.routes as child routes.
    //component: ProductsViewComponent,

    loadChildren: () => import('./products-view/products.routes').then(m => m.PRODUCT_ROUTES),

    // We should see something lazy loaded like:
    //    http://localhost:4200/src_app_products-view_products-view_component_ts-src_app_products-view_products_routes_ts-src-98f286.js
    //
    // Note: Try `ng build` to see how each code split is built, it will be listed.
  },
  {
    path: ROUTER_TOKENS.CONTACT,
    // For a standalone component, we are already "telling" the router which component to load.
    // So there's no need to add a child route. You should still see the lazy loading of:
    //    http://localhost:4200/src_app_contact_contact_component_ts.js
    loadComponent: () => import('./contact/contact.component').then(m => m.ContactComponent)
  },
  {
    path: ROUTER_TOKENS.ABOUT,
    loadChildren: () => import('./about/about.module').then(m => m.AboutModule),
  },
  {
    path: ROUTER_TOKENS.CHECKOUT,
    outlet: ROUTER_TOKENS.CART,
    component: CartComponent,
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];
