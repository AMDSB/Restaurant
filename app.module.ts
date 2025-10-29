import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- Import nécessaire pour [(ngModel)]
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CommandeComponent } from './commande/commande.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';

// Définition des routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'commandes', component: CommandeComponent }
];

@NgModule({
  
  imports: [
    BrowserModule,
    FormsModule, // <-- Indispensable pour ngModel
    RouterModule.forRoot(routes),
    // AppRoutingModule, // ✅ obligatoire pour le routage
    AppComponent,
    
  ],
  exports: [RouterModule]
})
export class AppModule { }
