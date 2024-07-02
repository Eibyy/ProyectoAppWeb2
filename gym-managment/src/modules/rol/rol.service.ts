import { Injectable } from '@nestjs/common';
import { Rol } from "./rol.entity";
import { InjectRepository } from "@nestjs/typeorm";
import { Repository } from "typeorm";
import { CreateRolInput } from './dto/create-rol.input';


@Injectable()
export class RolService {


    constructor(@InjectRepository(Rol) private rolRepository:Repository<Rol>){}

    async findAll(): Promise<Rol[]>{
        const rol = await this.rolRepository.find()
        return rol
    }

    createPersonal(rol:CreateRolInput): Promise<Rol>{
        const newRol = this.rolRepository.create(rol)
        return this.rolRepository.save(newRol)
    }

    async findPersonalByID(id:number):Promise<Rol>{
        const rol = this.rolRepository.findOne({
            where: {
                id
            }
        })
        return rol
    }
}
