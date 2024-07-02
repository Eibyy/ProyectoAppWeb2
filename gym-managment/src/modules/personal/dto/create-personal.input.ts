import { Field, Float, InputType, Int } from "@nestjs/graphql"
import { IsNotEmpty } from "class-validator"

@InputType()
export class CreatePersonalInput{

    @IsNotEmpty()
    @Field()
    nombre: string

    @IsNotEmpty()
    @Field((type)=>Int)
    edad: number

    @IsNotEmpty()
    @Field()
    direccion: string


    @IsNotEmpty()
    @Field((type)=>Float)
    salario: number


    @IsNotEmpty()
    @Field()
    estado: string
    

    @IsNotEmpty()
    @Field((type)=>Int)
    rol_id: number
}