import { provideAnimations } from '@angular/platform-browser/animations';
import { ApplicationConfig } from '@angular/core';
import { PreloadAllModules, provideRouter, withComponentInputBinding, withPreloading } from '@angular/router';
import { ROUTES } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideAnimations(),
    provideRouter(
      ROUTES,
      withComponentInputBinding(),
      // We will see that after main.js bundle is loaded, the lazy loaded code is also loaded (see in dev tools in browser)
      withPreloading(PreloadAllModules)
    ),
  ]
};

