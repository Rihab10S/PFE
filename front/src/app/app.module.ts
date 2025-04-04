// src/app/app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';  // Import AuthModule

@NgModule({
  declarations: [
    AppComponent, // Don't declare LoginComponent here anymore
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,  // Ensure AuthModule is imported
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
