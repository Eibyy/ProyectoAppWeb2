import { ObjectType, Field, Int, Float } from "@nestjs/graphql";
import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
@ObjectType()
export class Rol{
    @PrimaryGeneratedColumn()
    @Field((type)=>Int)
    id: number

    @Column()
    @Field()
    nombre: string

    @Column()
    @Field()
    description: string


    @Column()
    @Field()
    estado: string

}