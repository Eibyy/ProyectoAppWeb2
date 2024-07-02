import { ObjectType, Field, Int, Float } from "@nestjs/graphql";
import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
@ObjectType()
export class Cliente{
    @PrimaryGeneratedColumn()
    @Field((type)=>Int)
    id: number

    @Column()
    @Field()
    nombre: string

    @Column()
    @Field((type)=>Int)
    telefono: number

    @Column()
    @Field()
    description: string

    @Column()
    @Field()
    email: string


    @Column()
    @Field()
    estado: string

}