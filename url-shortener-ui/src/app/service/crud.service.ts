import { Injectable } from '@angular/core';
import { Link } from './Link';
import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class CrudService {

  // API
  REST_API: string = 'http://localhost:8080/';

  // Http Header
  httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private httpClient: HttpClient) { }

  // Add
  AddLink(data: Link): Observable<any> {
    let API_URL = `${this.REST_API}/shorten-link`;
    return this.httpClient.post(API_URL, data)
      .pipe(
        catchError(this.handleError)
      )
  }

  // Get all objects
  GetLinks() {
    return this.httpClient.get(`${this.REST_API}`+'/links');
  }

  // Get single object
  GetLink(shortLink:any): Observable<any> {
    let API_URL = `${this.REST_API}/link/${shortLink}`;
    return this.httpClient.get(API_URL, { headers: this.httpHeaders })
      .pipe(map((res: any) => {
          return res || {}
        }),
        catchError(this.handleError)
      )
  }

  // Update
  updateLink(id:any, data:any): Observable<any> {
    let API_URL = `${this.REST_API}/update/${id}`;
    return this.httpClient.put(API_URL, data, { headers: this.httpHeaders })
      .pipe(
        catchError(this.handleError)
      )
  }

  // Delete
  deleteLink(id:any): Observable<any> {
    let API_URL = `${this.REST_API}/delete/${id}`;
    return this.httpClient.delete(API_URL, { headers: this.httpHeaders}).pipe(
        catchError(this.handleError)
      )
  }


  // Error 
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Handle client error
      errorMessage = error.error.message;
    } else {
      // Handle server error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }

}