import { Resolver, Mutation, Arg, FieldResolver, Root } from 'type-graphql';
import { Personal } from '../models/personalModel';
import { getRepository, Repository } from 'typeorm';

@Resolver(Personal)
export class PersonalResolver {
  private personalRepository: Repository<Personal> = getRepository(Personal);

  @FieldResolver(() => [Personal])
  async personals(): Promise<Personal[]> {
    return await this.personalRepository.find({ relations: ["Roles"] });
  }

  @Mutation(() => Personal)
  async createPersonal(
    @Arg('Nombre') Nombre: string,
    @Arg('Apellido') Apellido: string,
    @Arg('Fecha_Nacimiento', { nullable: true }) Fecha_Nacimiento: Date,
    @Arg('Genero', { nullable: true }) Genero: string,
    @Arg('Telefono', { nullable: true }) Telefono: string,
    @Arg('Email', { nullable: true }) Email: string,
    @Arg('Direccion', { nullable: true }) Direccion: string,
    @Arg('Fecha_Contratacion', { nullable: true }) Fecha_Contratacion: Date,
    @Arg('ID_Rol', { nullable: true }) ID_Rol: number
  ): Promise<Personal> {
    const personal = this.personalRepository.create({
      Nombre,
      Apellido,
      Fecha_Nacimiento,
      Genero: Genero as any,
      Telefono,
      Email,
      Direccion,
      Fecha_Contratacion,
      ID_Rol
    });
    return await this.personalRepository.save(personal);
  }
}
