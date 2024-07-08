import { Controller, Get } from '@nestjs/common';
import { GymService } from './clientes.service';
import { Observable } from 'rxjs';

@Controller('gym')
export class GymController {
  constructor(private gymService: GymService) {}

  @Get('staff')
  getStaff(): Observable<any> {
    return this.gymService.getStaff();
  }

  @Get('classes')
  getClasses(): Observable<any> {
    return this.gymService.getClasses();
  }
}
