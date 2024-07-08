import { HttpService } from '@nestjs/axios';
import { Injectable,  } from '@nestjs/common';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';



@Injectable()
export class GymService {
  constructor(private httpService: HttpService) {}

  // Ejemplo de método para obtener personal desde el microservicio en Python-Flask
  getStaff(): Observable<any> {
    return this.httpService.get('http://localhost:5000/api/staff').pipe(
      map(response => response)
    );
  }

  // Ejemplo de método para obtener clases desde el microservicio en Java-Spring
  getClasses(): Observable<any> {
    return this.httpService.get('http://localhost:8080/api/classes').pipe(
      map(response => response)
    );
  }
}
