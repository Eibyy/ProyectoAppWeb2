import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios';
import { GymService } from './clientes.service';
import { GymController } from './clientes.controller';

@Module({
  imports: [HttpModule],
  providers: [GymService],
  controllers: [GymController],
})
export class ClientesModule {}
