import { Module } from '@nestjs/common';
import { ClaseService } from './clase.service';
import { ClaseResolver } from './clase.resolver';

@Module({
  providers: [ClaseService, ClaseResolver]
})
export class ClaseModule {}
