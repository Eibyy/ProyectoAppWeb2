import { Resolver } from '@nestjs/graphql';
import { PersonalService } from './personal.service';
import { Query } from '@nestjs/graphql';
import { Personal } from './personal.entity';

@Resolver()
export class PersonalResolver {

    constructor(private personalService: PersonalService){}

    @Query((returns) =>[Personal])
    personal(){
        return this.personalService.findAll()
    }

}
