import { Module } from '@nestjs/common';
import { RolService } from './rol.service';
import { RolResolver } from './rol.resolver';
import { Rol } from './rol.entity';
import { TypeOrmModule } from "@nestjs/typeorm";


@Module({
  imports:[TypeOrmModule.forFeature([Rol])],
  providers: [RolService, RolResolver]
})
export class RolModule {}
