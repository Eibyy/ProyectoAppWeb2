import { ObjectType, Field, Int, Float } from "@nestjs/graphql";
import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
@ObjectType()
export class Clase{
    @PrimaryGeneratedColumn()
    @Field((type)=>Int)
    id: number

    @Column()
    @Field()
    nombre: string

    @Column()
    @Field()
    horario: string

    @Column()
    @Field()
    descripcion: string

    @Column()
    @Field((type)=>Int)
    personal_id: number

    @Column()
    @Field()
    estado: string

}