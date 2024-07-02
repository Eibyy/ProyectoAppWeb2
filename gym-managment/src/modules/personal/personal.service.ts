import { Injectable } from '@nestjs/common';
import { Personal } from "./personal.entity";
import { InjectRepository } from "@nestjs/typeorm";
import { Repository } from "typeorm";
import { CreatePersonalInput } from './dto/create-personal.input';


@Injectable()
export class PersonalService {


    constructor(@InjectRepository(Personal) private personalRepository:Repository<Personal>){}

    async findAll(): Promise<Personal[]>{
        const personal = await this.personalRepository.find()
        return personal
    }

    createPersonal(personal:CreatePersonalInput): Promise<Personal>{
        const newPersonal = this.personalRepository.create(personal)
        return this.personalRepository.save(newPersonal)
    }

    async findPersonalByID(id:number):Promise<Personal>{
        const personal = this.personalRepository.findOne({
            where: {
                id
            }
        })
        return personal
    }
}
