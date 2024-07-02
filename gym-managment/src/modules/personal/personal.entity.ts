import { ObjectType, Field, Int, Float } from "@nestjs/graphql";

@ObjectType()
export class Personal{
    @Field((type)=>Int)
    id: number

    @Field()
    nombre: string

    @Field((type)=>Int)
    edad: number

    @Field()
    direccion: string

    @Field((type)=>Float)
    salario: number

    @Field()
    estado: string
    
    @Field((type)=>Float)
    rol_id: number
}