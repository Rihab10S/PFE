import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { routes } from './app.routes'; 

const routes: Routes = [
  { path: '', component: LoginComponent },  // Make sure this is pointing to LoginComponent
  { path: 'home', component: HomeComponent },
  { path: '**', redirectTo: '' }  // Redirect all unknown paths to login
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
