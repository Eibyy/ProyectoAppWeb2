import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from 'typeorm';
import { ObjectType, Field, ID } from 'type-graphql'; 
import { Personal } from './personalModel';

@Entity()
@ObjectType() 
export class Roles {
  @PrimaryGeneratedColumn()
  @Field(() => ID)
  ID_Rol!: number;

  @Column()
  @Field() 
  Nombre_Rol!: string;

  @Column({ nullable: true })
  @Field({ nullable: true })
  Descripcion?: string;

  @OneToMany(() => Personal, personal => personal.Roles, { nullable: true })
  @Field(() => [Personal], { nullable: true }) 
  personal?: Personal[];
}
