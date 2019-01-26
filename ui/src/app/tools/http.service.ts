import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpResponse } from '../dto/http-response';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  baseUrl = "/api/";
  constructor(private http: HttpClient) { }
  get(url: string): Observable<HttpResponse> {
    return this.http.get<HttpResponse>(this.baseUrl + url);
  }
  post(url: string, body: any): Observable<HttpResponse> {
    return this.http.post<HttpResponse>(this.baseUrl + url, body);
  }
  delete(url: string, params: any): Observable<HttpResponse> {
    return this.http.delete<HttpResponse>(this.baseUrl + url, {"params": params});
  }
}
