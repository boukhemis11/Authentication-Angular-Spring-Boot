import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = false;
  redirectUrl: string;
  jwt:string;

  apiUrl = 'http://localhost:8080/api/auth/';

  constructor(private http: HttpClient) { }

  islogin(){
    if (localStorage.getItem('token')){
      return this.isLoggedIn = true;
    }else {
      return this.isLoggedIn = false;
    }
  }

  login(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'login', data)
      .pipe(
        tap(_ => this.isLoggedIn= true),
        catchError(this.handleError('login', []))
      );
  }
  
  register(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'register', data)
      .pipe(
        tap(_ => this.log('login')),
        catchError(this.handleError('login', []))
      );
  }
  
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);
  
      return of(result as T);
    };
  }

  parseJwt() {
    let jwtHelper = new JwtHelperService();
    let objJwt = jwtHelper.decodeToken(this.jwt);
  
  }

  loadToken() {
    this.jwt= localStorage.getItem('token');
    this.parseJwt();
  }
  
  private log(message: string) {
    console.log(message);
  }
}
