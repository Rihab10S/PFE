// src/app/auth/auth.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';  // Required for *ngIf
import { FormsModule } from '@angular/forms';   // Required for ngModel and ngForm
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    LoginComponent,
  ],
  imports: [
    CommonModule,  // Required for *ngIf
    FormsModule,   // Required for ngModel and ngForm
  ],
  exports: [
    LoginComponent,  // Export LoginComponent if you plan to use it outside this module
  ],
})
export class AuthModule {}
