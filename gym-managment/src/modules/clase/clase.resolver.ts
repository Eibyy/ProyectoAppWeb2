import { Mutation, Resolver, Args, Int } from '@nestjs/graphql';
import { ClaseService } from './clase.service';
import { Query } from '@nestjs/graphql';
import { Clase } from './clase.entity';
import { CreateClaseInput } from './dto/create-clase.input';

@Resolver()
export class PersonalResolver {

    constructor(private claseService: ClaseService){}

    @Query((returns) =>[Clase])
    clase(){
        return this.claseService.findAll()
    }

    @Mutation((returns) =>Clase)
    createClase(@Args('claseInput') claseInput:CreateClaseInput){
        return this.claseService.createClase(claseInput)
    }

    @Query((returns) =>Clase)
    oneClase(@Args('id', {type:()=>Int})id:number){
        return this.claseService.findClaseByID(id)
    }
}
