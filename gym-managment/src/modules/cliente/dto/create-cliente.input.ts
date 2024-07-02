import { Field, Float, InputType, Int } from "@nestjs/graphql"
import { IsNotEmpty } from "class-validator"

@InputType()
export class CreateClienteInput{

    @Field((type)=>Int)
    id: number

    @IsNotEmpty()
    @Field()
    nombre: string

    @IsNotEmpty()
    @Field((type)=>Int)
    telefono: number

    @IsNotEmpty()
    @Field()
    description: string

    @IsNotEmpty()
    @Field()
    email: string


    @IsNotEmpty()
    @Field()
    estado: string

}