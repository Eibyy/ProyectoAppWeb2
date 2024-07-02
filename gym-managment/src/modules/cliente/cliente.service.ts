import { Injectable } from '@nestjs/common';
import { Cliente } from "./cliente.entity";
import { InjectRepository } from "@nestjs/typeorm";
import { Repository } from "typeorm";
import { CreateClienteInput } from './dto/create-cliente.input';


@Injectable()
export class ClienteService {


    constructor(@InjectRepository(Cliente) private clienteRepository:Repository<Cliente>){}

    async findAll(): Promise<Cliente[]>{
        const cliente = await this.clienteRepository.find()
        return cliente
    }

    createPersonal(cliente:CreateClienteInput): Promise<Cliente>{
        const newRol = this.clienteRepository.create(cliente)
        return this.clienteRepository.save(newRol)
    }

    async findPersonalByID(id:number):Promise<Cliente>{
        const cliente = this.clienteRepository.findOne({
            where: {
                id
            }
        })
        return cliente
    }
}