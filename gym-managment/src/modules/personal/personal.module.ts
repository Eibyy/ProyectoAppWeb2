import { Module } from '@nestjs/common';
import { PersonalService } from './personal.service';
import { PersonalResolver } from './personal.resolver';

@Module({
  providers: [PersonalService, PersonalResolver]
})
export class PersonalModule {}
