import { NgModule } from '@angular/core';
import { AboutComponent } from './about.component';
import { RouterModule } from '@angular/router';

/*
When loading the page, in network/all you should see the js chunk being loaded lazily
(http://localhost:4200/src_app_about_about_module_ts.js)
*/

// About is not standalone so we add the lazy child route for the router here.
const ABOUT_ROUTES = [
  {
    path: '', // An empty string as segment is not consumed by the router. But it will load the component of course.
    component: AboutComponent
  }
];

@NgModule({
  declarations: [AboutComponent],
  imports: [RouterModule.forChild(ABOUT_ROUTES)] // forChild since this is a child component.
})
export class AboutModule {}
