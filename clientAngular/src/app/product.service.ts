import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Product } from './products/product';
import { tap, catchError } from 'rxjs/operators';

const apiUrl = 'http://localhost:8080/api/products';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(apiUrl)
      .pipe(
        tap(_ => this.log('fetched Products')),
        catchError(this.handleError('getProducts', []))
      );
  }

  addProducts(data:Product): Observable<Product> {
    return this.http.post<Product>(apiUrl, data, httpOptions)
      .pipe(
        tap((pr :Product) => console.log(`add Products w/ id=${pr._id}`)),
        catchError(this.handleError<any>('addArticle'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }
}
