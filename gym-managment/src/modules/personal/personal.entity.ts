import { ObjectType, Field, Int, Float } from "@nestjs/graphql";
import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
@ObjectType()
export class Personal{
    @PrimaryGeneratedColumn()
    @Field((type)=>Int)
    id: number

    @Column()
    @Field()
    nombre: string

    @Column()
    @Field((type)=>Int)
    edad: number

    @Column()
    @Field()
    direccion: string

    @Column()
    @Field((type)=>Float)
    salario: number

    @Column()
    @Field()
    estado: string
    
    @Column()
    @Field((type)=>Float)
    rol_id: number
}