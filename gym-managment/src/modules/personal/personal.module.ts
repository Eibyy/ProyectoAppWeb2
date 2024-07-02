import { Module } from '@nestjs/common';
import { PersonalService } from './personal.service';
import { PersonalResolver } from './personal.resolver';
import { TypeOrmModule } from "@nestjs/typeorm";
import { Personal } from './personal.entity';

@Module({
  imports:[TypeOrmModule.forFeature([Personal])],
  providers: [PersonalService, PersonalResolver]
})
export class PersonalModule {}
