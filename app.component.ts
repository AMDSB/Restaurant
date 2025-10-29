import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';   // 👈 ajouter ça
// import { AppComponent } from './app.component';
import { CommandeComponent } from './commande/commande.component';
import { HeaderComponent } from './header/header.component';
import { RouterOutlet } from "@angular/router";
import { FooterComponent } from './footer/footer.component';


// import { RouterOutlet } from "@angular/router";  // 👈 importe ton header


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HeaderComponent, FooterComponent, RouterOutlet],  // 👈 ajoute le header ici
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Restaurant'; // juste une variable, pas obligatoire
}
