Here we add routing to standalone components.
When angular configures an app for routing, it adds a app.config.ts
In it we can add a provideRouter()

Since app component is standalone, we need to import RouterOutlet in it,
in order to use <router-outloet/>.
