import { Field, Float, InputType, Int } from "@nestjs/graphql"
import { IsNotEmpty } from "class-validator"

@InputType()
export class CreateClaseInput{

    @Field((type)=>Int)
    id: number

    @IsNotEmpty()
    @Field()
    nombre: string

    @IsNotEmpty()
    @Field()
    horario: string

    @IsNotEmpty()
    @Field()
    descripcion: string

    @IsNotEmpty()
    @Field((type)=>Int)
    personal_id: number

    @IsNotEmpty()
    @Field()
    estado: string

}