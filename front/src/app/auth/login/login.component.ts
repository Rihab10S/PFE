import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [CommonModule, FormsModule]
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';

  // Utilisateur simulé (à remplacer par vos propres valeurs si nécessaire)
  predefinedUser = { username: 'testuser', password: 'password123' };

  constructor(private router: Router) {}

  onLogin() {
    console.log('Login button clicked');
    
    // Vérification des informations d'identification
    if (this.username === this.predefinedUser.username && this.password === this.predefinedUser.password) {
      // Si les identifiants sont corrects, on navigue vers la page d'accueil
      console.log('Login successful');
      this.router.navigate(['/home']);
    } else {
      // Sinon, on affiche un message d'erreur
      console.error('Invalid credentials');
      this.errorMessage = 'Nom d’utilisateur ou mot de passe incorrect.';
    }
  }
}
