import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  data = {
    title: 'angular-course-dd'
  }
  onLogoClick() {
    alert("You clicked")
  }
  onKeyUp(newTitle:string) {
    this.data.title = newTitle
  }
}
