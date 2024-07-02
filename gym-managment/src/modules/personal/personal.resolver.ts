import { Mutation, Resolver, Args, Int } from '@nestjs/graphql';
import { PersonalService } from './personal.service';
import { Query } from '@nestjs/graphql';
import { Personal } from './personal.entity';
import { CreatePersonalInput } from './dto/create-personal.input';

@Resolver()
export class PersonalResolver {

    constructor(private personalService: PersonalService){}

    @Query((returns) =>[Personal])
    personal(){
        return this.personalService.findAll()
    }

    @Mutation((returns) =>Personal)
    createPersonal(@Args('personalInput') personalInput:CreatePersonalInput){
        return this.personalService.createPersonal(personalInput)
    }

    @Query((returns) =>Personal)
    onePersonal(@Args('id', {type:()=>Int})id:number){
        return this.personalService.findPersonalByID(id)
    }
}
