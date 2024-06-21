import { Resolver, Query, Mutation, Arg } from 'type-graphql';
import { Roles } from '../models/roleModel';
import { getRepository } from 'typeorm';

@Resolver()
export class RoleResolver {
  private roleRepository = getRepository(Roles);

  @Query(() => [Roles])
  async roles(): Promise<Roles[]> {
    return await this.roleRepository.find();
  }

  @Mutation(() => Roles)
  async createRole(
    @Arg('Nombre_Rol') Nombre_Rol: string,
    @Arg('Descripcion', { nullable: true }) Descripcion: string
  ): Promise<Roles> {
    const role = this.roleRepository.create({ Nombre_Rol, Descripcion });
    return await this.roleRepository.save(role);
  }
}
