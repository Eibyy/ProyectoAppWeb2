"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.PersonalResolver = void 0;
const type_graphql_1 = require("type-graphql");
const personalModel_1 = require("../models/personalModel");
const typeorm_1 = require("typeorm");
let PersonalResolver = class PersonalResolver {
    constructor() {
        this.personalRepository = (0, typeorm_1.getRepository)(personalModel_1.Personal);
    }
    async personals() {
        return await this.personalRepository.find({ relations: ["Roles"] });
    }
    async createPersonal(Nombre, Apellido, Fecha_Nacimiento, Genero, Telefono, Email, Direccion, Fecha_Contratacion, ID_Rol) {
        const personal = this.personalRepository.create({
            Nombre,
            Apellido,
            Fecha_Nacimiento,
            Genero: Genero,
            Telefono,
            Email,
            Direccion,
            Fecha_Contratacion,
            ID_Rol
        });
        return await this.personalRepository.save(personal);
    }
};
__decorate([
    (0, type_graphql_1.Query)(() => [personalModel_1.Personal]),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], PersonalResolver.prototype, "personals", null);
__decorate([
    (0, type_graphql_1.Mutation)(() => personalModel_1.Personal),
    __param(0, (0, type_graphql_1.Arg)('Nombre')),
    __param(1, (0, type_graphql_1.Arg)('Apellido')),
    __param(2, (0, type_graphql_1.Arg)('Fecha_Nacimiento', { nullable: true })),
    __param(3, (0, type_graphql_1.Arg)('Genero', { nullable: true })),
    __param(4, (0, type_graphql_1.Arg)('Telefono', { nullable: true })),
    __param(5, (0, type_graphql_1.Arg)('Email', { nullable: true })),
    __param(6, (0, type_graphql_1.Arg)('Direccion', { nullable: true })),
    __param(7, (0, type_graphql_1.Arg)('Fecha_Contratacion', { nullable: true })),
    __param(8, (0, type_graphql_1.Arg)('ID_Rol', { nullable: true })),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, String, Date, String, String, String, String, Date, Number]),
    __metadata("design:returntype", Promise)
], PersonalResolver.prototype, "createPersonal", null);
PersonalResolver = __decorate([
    (0, type_graphql_1.Resolver)()
], PersonalResolver);
exports.PersonalResolver = PersonalResolver;
