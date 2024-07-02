import { Injectable } from '@nestjs/common';
import { Clase } from "./clase.entity";
import { InjectRepository } from "@nestjs/typeorm";
import { Repository } from "typeorm";
import { CreateClaseInput } from './dto/create-clase.input';


@Injectable()
export class ClaseService {


    constructor(@InjectRepository(Clase) private claseRepository:Repository<Clase>){}

    async findAll(): Promise<Clase[]>{
        const clase = await this.claseRepository.find()
        return clase
    }

    createClase(clase:CreateClaseInput): Promise<Clase>{
        const newPersonal = this.claseRepository.create(clase)
        return this.claseRepository.save(newPersonal)
    }

    async findClaseByID(id:number):Promise<Clase>{
        const clase = this.claseRepository.findOne({
            where: {
                id
            }
        })
        return clase
    }
}
