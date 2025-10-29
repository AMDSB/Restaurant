import { Routes } from '@angular/router';
import { CommandeComponent } from './commande/commande.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
// import { HomeComponent } from './home/home.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: '', component: HeaderComponent },
  { path: 'commande', component: CommandeComponent },
  // { path: 'contact', component: ContactComponent },
  { path: '**', redirectTo: '' } // Redirection si route inconnue
];
