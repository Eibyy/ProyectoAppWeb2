import { Field, Float, InputType, Int } from "@nestjs/graphql"
import { IsNotEmpty } from "class-validator"


@InputType()
export class CreateRolInput{

    @IsNotEmpty()
    @Field()
    nombre: string


    @IsNotEmpty()
    @Field()
    description: string


    @IsNotEmpty()
    @Field()
    estado: string

}