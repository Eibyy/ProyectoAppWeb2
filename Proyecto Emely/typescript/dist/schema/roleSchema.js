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
exports.RoleResolver = void 0;
const type_graphql_1 = require("type-graphql");
const roleModel_1 = require("../models/roleModel");
const typeorm_1 = require("typeorm");
let RoleResolver = class RoleResolver {
    constructor() {
        this.roleRepository = (0, typeorm_1.getRepository)(roleModel_1.Roles);
    }
    async roles() {
        return await this.roleRepository.find();
    }
    async createRole(Nombre_Rol, Descripcion) {
        const role = this.roleRepository.create({ Nombre_Rol, Descripcion });
        return await this.roleRepository.save(role);
    }
};
__decorate([
    (0, type_graphql_1.Query)(() => [roleModel_1.Roles]),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], RoleResolver.prototype, "roles", null);
__decorate([
    (0, type_graphql_1.Mutation)(() => roleModel_1.Roles),
    __param(0, (0, type_graphql_1.Arg)('Nombre_Rol')),
    __param(1, (0, type_graphql_1.Arg)('Descripcion', { nullable: true })),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, String]),
    __metadata("design:returntype", Promise)
], RoleResolver.prototype, "createRole", null);
RoleResolver = __decorate([
    (0, type_graphql_1.Resolver)()
], RoleResolver);
exports.RoleResolver = RoleResolver;
