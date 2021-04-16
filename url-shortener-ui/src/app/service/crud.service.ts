import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { GlobalVariable } from 'src/app/global';

import { Link } from './Link';

const BASE_URL = GlobalVariable.BASE_API_URL+"links";

@Injectable({
  providedIn: 'root'
})

export class CrudService {

  // Http Header
  httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Add
  addLink(data: Link): Observable<any> {
    return this.http.post(BASE_URL, data)
      .pipe(
        catchError(this.handleError)
      )
  }

  // Get all links
  getLinks(): Observable<any> {
    return this.http.get(BASE_URL, {headers: this.httpHeaders});
  }

  // Get single object
  getLink(shortLink:any): Observable<any> {
    let API_URL = `${BASE_URL}link/${shortLink}`;
    return this.http.get(API_URL, { headers: this.httpHeaders })
      .pipe(map((res: any) => {
          return res || {}
        }),
        catchError(this.handleError)
      )
  }

  // Update
  updateLink(id:any, data:any): Observable<any> {
    let API_URL = `${BASE_URL}/update/${id}`;
    return this.http.put(API_URL, data, { headers: this.httpHeaders })
      .pipe(
        catchError(this.handleError)
      )
  }

  // Delete
  deleteLink(id:any): Observable<any> {
    let API_URL = `${BASE_URL}/delete/${id}`;
    return this.http.delete(API_URL, { headers: this.httpHeaders}).pipe(
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