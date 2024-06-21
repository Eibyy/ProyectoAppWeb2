import { Entity, PrimaryGeneratedColumn, Column, ManyToOne } from 'typeorm';
import { ObjectType, Field, ID, Int, registerEnumType } from 'type-graphql'; // Importa los decoradores necesarios de type-graphql
import { Roles } from './roleModel';

export enum GeneroEnum {
  Masculino = 'Masculino',
  Femenino = 'Femenino',
  Otro = 'Otro'
}

registerEnumType(GeneroEnum, {
  name: 'GeneroEnum',
  description: 'Género del personal'
});

@Entity()
@ObjectType() 
export class Personal {
  @PrimaryGeneratedColumn()
  @Field(() => ID) 
  ID!: number;

  @Column()
  @Field() 
  Nombre!: string;

  @Column()
  @Field()
  Apellido!: string;

  @Column({ type: 'date', nullable: true })
  @Field(() => Date, { nullable: true }) 
  Fecha_Nacimiento?: Date;

  @Column({ type: 'enum', enum: GeneroEnum, nullable: true })
  @Field(() => GeneroEnum, { nullable: true })
  Genero?: GeneroEnum;

  @Column({ nullable: true })
  @Field({ nullable: true })
  Telefono?: string;

  @Column({ nullable: true })
  @Field({ nullable: true })
  Email?: string;

  @Column({ nullable: true })
  @Field({ nullable: true })
  Direccion?: string;

  @Column({ type: 'date', nullable: true })
  @Field(() => Date, { nullable: true })
  Fecha_Contratacion?: Date;

  @Column({ nullable: true })
  @Field(() => Int, { nullable: true }) 
  ID_Rol?: number;

  @ManyToOne(() => Roles, roles => roles.personal)
  @Field(() => Roles, { nullable: true }) 
  Roles?: Roles;
}
