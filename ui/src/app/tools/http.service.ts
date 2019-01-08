import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  baseUrl = "/api/";
  constructor(private http: HttpClient) { }
  get(url: string) {
    return this.http.get(this.baseUrl + url);
  }
  post(url: string, body: any) {
    return this.http.post(this.baseUrl + url, body);
  }
  delete(url: string, params: any) {
    let options: any = {};
    options.params = params;
    return this.http.delete(this.baseUrl + url, options);
  }
}
