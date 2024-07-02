import { Module } from '@nestjs/common';
import { ClienteService } from './cliente.service';
import { ClienteResolver } from './cliente.resolver';
import { TypeOrmModule } from "@nestjs/typeorm";
import { Cliente } from './cliente.entity';


@Module({
  imports:[TypeOrmModule.forFeature([Cliente])],
  providers: [ClienteService, ClienteResolver]
})
export class ClienteModule {}
