import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8083/auth/signin'; // URL de ton backend pour la connexion

  constructor(private http: HttpClient) {}

  // signIn méthode avec le token en paramètre
  signIn(username: string, password: string, token: string): Observable<any> {
    // Créer les en-têtes avec le token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}` // Ajoute le token dans l'en-tête Authorization
    });

    const body = {
      username: username,
      password: password
    };

    // Envoie la requête HTTP avec les en-têtes
    return this.http.post(this.apiUrl, body, { headers });
  }
}
