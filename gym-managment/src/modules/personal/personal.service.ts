import { Injectable } from '@nestjs/common';
import { Personal } from "./personal.entity";


@Injectable()
export class PersonalService {

    findAll(): Personal[]{
        return [{
            id:1,
            nombre: "Bryan",
            edad: 13,
            direccion: "San agustin",
            salario: 300,
            estado: "Activo",
            rol_id: 1
        }]
    }

}
